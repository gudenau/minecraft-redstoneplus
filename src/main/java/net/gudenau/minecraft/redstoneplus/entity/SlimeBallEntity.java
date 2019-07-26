package net.gudenau.minecraft.redstoneplus.entity;

import io.netty.buffer.Unpooled;
import net.fabricmc.fabric.api.network.ServerSidePacketRegistry;
import net.gudenau.minecraft.redstoneplus.RedstonePlus;
import net.gudenau.minecraft.redstoneplus.api.IColoredSlime;
import net.gudenau.minecraft.redstoneplus.item.ColoredSlimeItem;
import net.minecraft.entity.*;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.network.Packet;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.PacketByteBuf;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RayTraceContext;
import net.minecraft.world.World;

public class SlimeBallEntity extends ThrownItemEntity{
    public SlimeBallEntity(EntityType<? extends SlimeBallEntity> type, World world) {
        super(type, world);
    }

    public SlimeBallEntity(World world) {
        super(RedstonePlus.Entities.slimeBallEntity, world);
    }

    public SlimeBallEntity(double x, double y, double z, World world) {
        super(RedstonePlus.Entities.slimeBallEntity, x, y, z, world);
    }

    public SlimeBallEntity(LivingEntity owner, World world) {
        super(RedstonePlus.Entities.slimeBallEntity, owner, world);
    }

    public SlimeBallEntity(World world, double x, double y, double z) {
        super(RedstonePlus.Entities.slimeBallEntity, x, y, z, world);
    }

    @Override
    protected void onCollision(HitResult hitResult){
        Vec3d velocity = getVelocity();
        if(hitResult.getType() == HitResult.Type.BLOCK){
            BlockHitResult result = world.rayTrace(new RayTraceContext(
                getPos(),
                getPos().add(velocity),
                RayTraceContext.ShapeType.COLLIDER,
                RayTraceContext.FluidHandling.NONE,
                this
            ));
            if(result.getType() == HitResult.Type.BLOCK){
                switch(result.getSide().getAxis()){
                    case X:
                        setVelocity(velocity.multiply(-0.5, 0.5, 0.5));
                        break;
                    case Y:
                        setVelocity(velocity.multiply(0.5, -0.5, 0.5));
                        break;
                    case Z:
                        setVelocity(velocity.multiply(0.5, 0.5, -0.5));
                        break;
                }

                if(!world.isClient){
                    if(getVelocity().length() < 0.05){
                        remove();
                        ItemEntity item = new ItemEntity(EntityType.ITEM, world);
                        item.setPosition(x, y, z);
                        item.setStack(getStack());
                        world.spawnEntity(item);
                    }

                    playSound(
                        SoundEvents.ENTITY_SLIME_SQUISH,
                        (float)Math.min(velocity.length(), 1),
                        ((random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F) / 0.8F
                    );
                }
            }
            return;
        }else if(!world.isClient && hitResult.getType() == HitResult.Type.ENTITY){
            EntityHitResult result = ProjectileUtil.getEntityCollision(
                world,
                this,
                getPos(),
                getPos().add(velocity),
                getBoundingBox().stretch(velocity).expand(1.0D),
                (entity)->entity instanceof LivingEntity
            );
            if(result != null){
                Entity entity = result.getEntity();
                if(entity instanceof IColoredSlime){
                    ((IColoredSlime)entity).setColor(((ColoredSlimeItem)getItem().getItem()).getColor());
                }else if(entity instanceof LivingEntity){
                    ((LivingEntity)entity).addPotionEffect(
                        new StatusEffectInstance(
                            StatusEffects.SLOWNESS,
                            200
                        )
                    );
                }
                playSound(
                    SoundEvents.ENTITY_SLIME_ATTACK,
                    (float)Math.min(velocity.length(), 1),
                    ((random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F) / 0.8F
                );
            }
        }
        remove();
    }

    @Override
    protected Item getDefaultItem(){
        return Items.SLIME_BALL;
    }

    @Override
    public Packet<?> createSpawnPacket() {
        PacketByteBuf buffer = new PacketByteBuf(Unpooled.buffer());
        toBuffer(buffer);
        return ServerSidePacketRegistry.INSTANCE.toPacket(
            RedstonePlus.Packets.spawnSlime,
            buffer
        );
    }

    private void toBuffer(PacketByteBuf buffer){
        buffer.writeVarInt(getEntityId());
        buffer.writeUuid(getUuid());
        buffer.writeDouble(x);
        buffer.writeDouble(y);
        buffer.writeDouble(z);
        buffer.writeByte(MathHelper.floor(pitch * 256.0F / 360.0F));
        buffer.writeByte(MathHelper.floor(yaw * 256.0F / 360.0F));
        Vec3d velocity = getVelocity();
        buffer.writeDouble(velocity.x);
        buffer.writeDouble(velocity.y);
        buffer.writeDouble(velocity.z);
    }

    public void fromBuffer(PacketByteBuf buffer){
        setEntityId(buffer.readVarInt());
        uuid = buffer.readUuid();
        setPosition(
            buffer.readDouble(),
            buffer.readDouble(),
            buffer.readDouble()
        );
        pitch = (buffer.readByte() * 360F) / 256;
        yaw = (buffer.readByte() * 360F) / 256;
        setVelocity(
            buffer.readDouble(),
            buffer.readDouble(),
            buffer.readDouble()
        );
    }
}

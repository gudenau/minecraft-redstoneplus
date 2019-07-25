package net.gudenau.minecraft.redstoneplus.entity;

import net.gudenau.minecraft.redstoneplus.RedstonePlus;
import net.gudenau.minecraft.redstoneplus.api.IColoredSlime;
import net.gudenau.minecraft.redstoneplus.item.ColoredSlimeItem;
import net.minecraft.client.network.packet.EntitySpawnS2CPacket;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.SlimeEntity;
import net.minecraft.entity.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.network.Packet;
import net.minecraft.util.DyeColor;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.List;

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

    @Override
    protected void onCollision(HitResult hitResult){
        if(!world.isClient){
            if(hitResult.getType() == HitResult.Type.ENTITY){
                Vec3d position = hitResult.getPos();
                List<Entity> entities = world.getEntities(SlimeEntity.class, new Box(
                    position.x - 0.1,
                    position.y - 0.1,
                    position.z - 0.1,
                    position.x + 0.1,
                    position.y + 0.1,
                    position.z + 0.1
                ));

                Item item = getItem().getItem();
                if(!(item instanceof ColoredSlimeItem)){
                    item = Items.SLIME_BALL;
                }
                DyeColor color = item == Items.SLIME_BALL ? null : ((ColoredSlimeItem)item).getColor();

                entities.stream()
                    .filter((entity)->entity instanceof IColoredSlime)
                    .map((entity)->(IColoredSlime)entity)
                    .forEach((slime)->slime.setColor(color));
            }
        }
    }

    @Override
    protected Item getDefaultItem(){
        return Items.SLIME_BALL;
    }

    @Override
    public Packet<?> createSpawnPacket() {
        return new EntitySpawnS2CPacket(this);
    }
}

package net.gudenau.minecraft.redstoneplus.mixin;

import net.gudenau.minecraft.redstoneplus.api.IColoredSlime;
import net.minecraft.entity.EntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnType;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.Monster;
import net.minecraft.entity.mob.SlimeEntity;
import net.minecraft.nbt.AbstractNumberTag;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import net.minecraft.world.IWorld;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.World;
import org.spongepowered.asm.lib.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(SlimeEntity.class)
public abstract class SlimeEntityMixin extends MobEntity implements Monster, IColoredSlime{
    private static final TrackedData<Integer> SLIME_COLOR = DataTracker.registerData(SlimeEntity.class, TrackedDataHandlerRegistry.INTEGER);
    private static final Identifier[] LOOT_TABLES = new Identifier[]{
        new Identifier("gud_redstoneplus", "entities/slime/white"),
        new Identifier("gud_redstoneplus", "entities/slime/orange"),
        new Identifier("gud_redstoneplus", "entities/slime/magenta"),
        new Identifier("gud_redstoneplus", "entities/slime/light_blue"),
        new Identifier("gud_redstoneplus", "entities/slime/yellow"),
        new Identifier("gud_redstoneplus", "entities/slime/lime"),
        new Identifier("gud_redstoneplus", "entities/slime/pink"),
        new Identifier("gud_redstoneplus", "entities/slime/gray"),
        new Identifier("gud_redstoneplus", "entities/slime/light_gray"),
        new Identifier("gud_redstoneplus", "entities/slime/cyan"),
        new Identifier("gud_redstoneplus", "entities/slime/purple"),
        new Identifier("gud_redstoneplus", "entities/slime/blue"),
        new Identifier("gud_redstoneplus", "entities/slime/brown"),
        new Identifier("gud_redstoneplus", "entities/slime/green"),
        new Identifier("gud_redstoneplus", "entities/slime/red"),
        new Identifier("gud_redstoneplus", "entities/slime/black")
    };

    @Shadow private int getSize(){ return 0; }

    private SlimeEntityMixin(EntityType<? extends MobEntity> a, World b){ super(a, b); }

    @Inject(
        method = "initDataTracker",
        at = @At("TAIL")
    )
    private void onInitDataTracker(CallbackInfo info){
        dataTracker.startTracking(SLIME_COLOR, -1);
    }

    @Inject(
        method = "writeCustomDataToTag",
        at = @At("TAIL")
    )
    private void onWriteCustomDataToTag(CompoundTag tag, CallbackInfo info){
        tag.putInt("gud_redstoneplus_color", dataTracker.get(SLIME_COLOR));
    }

    @Inject(
        method = "readCustomDataFromTag",
        at = @At("TAIL")
    )
    private void onReadCustomDataFromTag(CompoundTag tag, CallbackInfo info){
        Tag colorTag = tag.getTag("gud_redstoneplus_color");
        if(colorTag instanceof AbstractNumberTag){
            try{
                dataTracker.set(SLIME_COLOR, ((AbstractNumberTag)colorTag).getInt());
            }catch(ClassCastException ignored){}
        }
    }

    @Inject(
        method = "initialize",
        at = @At("HEAD")
    )
    private void onInitialize(
        IWorld world, LocalDifficulty difficulty, SpawnType spawnType,
        EntityData entityData, CompoundTag compoundTag,
        CallbackInfoReturnable<EntityData> info
    ){
        if(random.nextInt(8) == 0){
            setColor(DyeColor.values()[random.nextInt(16)]);
        }
    }

    /*
    @Inject(
        method = "remove",
        at = @At(
            value = "FIELD",
            target = "Lnet/minecraft/entity/mob/SlimeEntity;world:Lnet/minecraft/world/World;",
            opcode = Opcodes.GETFIELD,
            ordinal = 1
        ),
        locals = LocalCapture.PRINT
    )
    private void onRemove(CallbackInfo info){

    }
    */

    /**
     * @author gudenau
     * @reason I'm an idiot
     * */
    @Overwrite
    public void remove() {
        int int_1 = getSize();
        if (!this.world.isClient && int_1 > 1 && this.getHealth() <= 0.0F) {
            int int_2 = 2 + this.random.nextInt(3);

            for(int int_3 = 0; int_3 < int_2; ++int_3) {
                float float_1 = ((float)(int_3 % 2) - 0.5F) * (float)int_1 / 4.0F;
                float float_2 = ((float)(int_3 / 2) - 0.5F) * (float)int_1 / 4.0F;
                SlimeEntity slimeEntity_1 = (SlimeEntity)getType().create(world);
                if (hasCustomName()) {
                    slimeEntity_1.setCustomName(getCustomName());
                }

                if (isPersistent()) {
                    slimeEntity_1.setPersistent();
                }

                ((SlimeEntityMixin)((Object)slimeEntity_1)).setSize(int_1 / 2, true);
                slimeEntity_1.setPositionAndAngles(x + float_1, y + 0.5D, z + float_2, random.nextFloat() * 360.0F, 0.0F);
                ((IColoredSlime)slimeEntity_1).setColor(getColor());
                world.spawnEntity(slimeEntity_1);
            }
        }

        super.remove();
    }

    @Shadow
    protected abstract void setSize(int size, boolean updateStats);

    @Override
    public Identifier getLootTableId() {
        DyeColor color = getColor();
        if(color != null){
            return LOOT_TABLES[color.ordinal()];
        }

        return super.getLootTableId();
    }

    @Override
    public void setColor(DyeColor color){
        dataTracker.set(SLIME_COLOR, color == null ? -1 : color.ordinal());
    }

    @Override
    public DyeColor getColor(){
        int colorIndex = dataTracker.get(SLIME_COLOR);
        if(colorIndex < 0 || colorIndex >= 16){
            return null;
        }
        return DyeColor.values()[colorIndex];
    }
}

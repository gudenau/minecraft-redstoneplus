package net.gudenau.minecraft.redstoneplus.mixin;

import com.mojang.blaze3d.platform.GlStateManager;
import net.gudenau.minecraft.redstoneplus.api.IColoredSlime;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.SlimeEntityModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.SlimeEntity;
import net.minecraft.util.DyeColor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SlimeEntityModel.class)
public abstract class SlimeEntityModelMixin<T extends Entity> extends EntityModel<T>{
    @Inject(
        method = "render",
        at = @At("HEAD")
    )
    private void render(
        T entity, float float_1, float float_2, float float_3, float float_4, float float_5, float float_6,
        CallbackInfo info
    ){
        if(entity instanceof IColoredSlime){
            DyeColor color = ((IColoredSlime)entity).getColor();
            if(color != null){
                float[] rgb = color.getColorComponents();
                GlStateManager.color3f(
                    rgb[0],
                    rgb[1],
                    rgb[2]
                );
            }
        }
    }
}

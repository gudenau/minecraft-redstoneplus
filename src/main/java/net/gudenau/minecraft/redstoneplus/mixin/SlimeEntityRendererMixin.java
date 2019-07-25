package net.gudenau.minecraft.redstoneplus.mixin;

import com.mojang.blaze3d.platform.GlStateManager;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.gudenau.minecraft.redstoneplus.api.IColoredSlime;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.SlimeEntityRenderer;
import net.minecraft.client.render.entity.model.SlimeEntityModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.SlimeEntity;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Environment(EnvType.CLIENT)
@Mixin(SlimeEntityRenderer.class)
public abstract class SlimeEntityRendererMixin extends MobEntityRenderer<SlimeEntity, SlimeEntityModel<SlimeEntity>>{
    private static final Identifier COLORED_SKIN = new Identifier("gud_redstoneplus", "textures/entity/slime/slime.png");

    private SlimeEntityRendererMixin(EntityRenderDispatcher a, SlimeEntityModel<SlimeEntity> b, float c){
        super(a, b, c);
    }

    @Inject(
        method = "method_4116",
        at = @At("HEAD"),
        cancellable = true
    )
    private void onMethod_4116(SlimeEntity entity, CallbackInfoReturnable<Identifier> info){
        DyeColor color = ((IColoredSlime)entity).getColor();
        if(color != null){
            info.setReturnValue(COLORED_SKIN);
        }
    }
}

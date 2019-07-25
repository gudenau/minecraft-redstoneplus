package net.gudenau.minecraft.redstoneplus.entity.renderer;

import net.fabricmc.fabric.api.client.render.EntityRendererRegistry;
import net.gudenau.minecraft.redstoneplus.entity.SlimeBallEntity;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;

public class SlimeBallEntityRenderer extends FlyingItemEntityRenderer<SlimeBallEntity>{
    public SlimeBallEntityRenderer(EntityRenderDispatcher dispatcher, EntityRendererRegistry.Context context){
        super(dispatcher, context.getItemRenderer());
    }

    @Override
    public void render(SlimeBallEntity entity_1, double double_1, double double_2, double double_3, float float_1, float float_2){
        super.render(entity_1, double_1, double_2, double_3, float_1, float_2);
    }
}

package net.gudenau.minecraft.redstoneplus;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.render.EntityRendererRegistry;
import net.gudenau.minecraft.redstoneplus.entity.SlimeBallEntity;
import net.gudenau.minecraft.redstoneplus.entity.renderer.SlimeBallEntityRenderer;

@SuppressWarnings("unused")
@Environment(EnvType.CLIENT)
public class RedstonePlusClient implements ClientModInitializer{
    @Override
    public void onInitializeClient(){
        registerEntityRenderers();
    }

    private void registerEntityRenderers(){
        EntityRendererRegistry entityRendererRegistry = EntityRendererRegistry.INSTANCE;
        entityRendererRegistry.register(SlimeBallEntity.class, SlimeBallEntityRenderer::new);
    }
}

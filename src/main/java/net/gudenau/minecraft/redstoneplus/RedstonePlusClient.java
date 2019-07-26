package net.gudenau.minecraft.redstoneplus;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.render.EntityRendererRegistry;
import net.fabricmc.fabric.api.network.ClientSidePacketRegistry;
import net.gudenau.minecraft.redstoneplus.client.RedstoneWireColor;
import net.gudenau.minecraft.redstoneplus.entity.SlimeBallEntity;
import net.gudenau.minecraft.redstoneplus.entity.renderer.SlimeBallEntityRenderer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.DyeColor;
import net.minecraft.world.World;

@SuppressWarnings("unused")
@Environment(EnvType.CLIENT)
public class RedstonePlusClient implements ClientModInitializer{
    @Override
    public void onInitializeClient(){
        registerEntityRenderers();
        registerPackets();
    }

    private void registerEntityRenderers(){
        EntityRendererRegistry entityRendererRegistry = EntityRendererRegistry.INSTANCE;
        entityRendererRegistry.register(SlimeBallEntity.class, SlimeBallEntityRenderer::new);
    }

    private void registerPackets(){
        ClientSidePacketRegistry registry = ClientSidePacketRegistry.INSTANCE;
        registry.register(
            RedstonePlus.Packets.spawnSlime,
            (context, buffer)->{
                MinecraftClient client = MinecraftClient.getInstance();
                SlimeBallEntity entity = new SlimeBallEntity(client.world);
                entity.fromBuffer(buffer);
                if(client.isOnThread()){
                    spawnEntity(entity);
                }else{
                    client.execute(()->spawnEntity(entity));
                }
            }
        );
    }

    private void spawnEntity(SlimeBallEntity entity){
        MinecraftClient client = MinecraftClient.getInstance();
        ClientWorld world = client.world;
        if(world != null){
            world.addEntity(entity.getEntityId(), entity);
        }
    }

    public static void registerBlockColors(BlockColors blockColorMap){
        blockColorMap.register(new RedstoneWireColor(DyeColor.WHITE), RedstonePlus.Blocks.redstoneWireWhite);
        blockColorMap.register(new RedstoneWireColor(DyeColor.ORANGE), RedstonePlus.Blocks.redstoneWireOrange);
        blockColorMap.register(new RedstoneWireColor(DyeColor.MAGENTA), RedstonePlus.Blocks.redstoneWireMagenta);
        blockColorMap.register(new RedstoneWireColor(DyeColor.LIGHT_BLUE), RedstonePlus.Blocks.redstoneWireLightBlue);
        blockColorMap.register(new RedstoneWireColor(DyeColor.YELLOW), RedstonePlus.Blocks.redstoneWireYellow);
        blockColorMap.register(new RedstoneWireColor(DyeColor.LIME), RedstonePlus.Blocks.redstoneWireLime);
        blockColorMap.register(new RedstoneWireColor(DyeColor.PINK), RedstonePlus.Blocks.redstoneWirePink);
        blockColorMap.register(new RedstoneWireColor(DyeColor.GRAY), RedstonePlus.Blocks.redstoneWireGray);
        blockColorMap.register(new RedstoneWireColor(DyeColor.LIGHT_GRAY), RedstonePlus.Blocks.redstoneWireLightGray);
        blockColorMap.register(new RedstoneWireColor(DyeColor.CYAN), RedstonePlus.Blocks.redstoneWireCyan);
        blockColorMap.register(new RedstoneWireColor(DyeColor.PURPLE), RedstonePlus.Blocks.redstoneWirePurple);
        blockColorMap.register(new RedstoneWireColor(DyeColor.BLUE), RedstonePlus.Blocks.redstoneWireBlue);
        blockColorMap.register(new RedstoneWireColor(DyeColor.BROWN), RedstonePlus.Blocks.redstoneWireBrown);
        blockColorMap.register(new RedstoneWireColor(DyeColor.GREEN), RedstonePlus.Blocks.redstoneWireGreen);
        blockColorMap.register(new RedstoneWireColor(DyeColor.RED), RedstonePlus.Blocks.redstoneWireRed);
        blockColorMap.register(new RedstoneWireColor(DyeColor.BLACK), RedstonePlus.Blocks.redstoneWireBlack);
    }
}

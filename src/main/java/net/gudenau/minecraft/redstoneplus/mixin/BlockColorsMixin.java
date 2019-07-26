package net.gudenau.minecraft.redstoneplus.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.gudenau.minecraft.redstoneplus.RedstonePlusClient;
import net.minecraft.client.color.block.BlockColors;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Environment(EnvType.CLIENT)
@Mixin(BlockColors.class)
public class BlockColorsMixin{
    @Inject(
        method = "create",
        at = @At("TAIL"),
        locals = LocalCapture.CAPTURE_FAILHARD
    )
    private static void create(
        CallbackInfoReturnable<BlockColors> returnInfo,
        BlockColors blockColors
    ){
        RedstonePlusClient.registerBlockColors(blockColors);
    }
}

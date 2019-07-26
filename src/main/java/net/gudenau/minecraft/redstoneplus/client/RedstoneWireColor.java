package net.gudenau.minecraft.redstoneplus.client;

import net.minecraft.block.BlockState;
import net.minecraft.block.RedstoneWireBlock;
import net.minecraft.client.color.block.BlockColorProvider;
import net.minecraft.util.DyeColor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.ExtendedBlockView;

public class RedstoneWireColor implements BlockColorProvider{
    private final float red;
    private final float green;
    private final float blue;

    public RedstoneWireColor(DyeColor color){
        float[] rgb = color.getColorComponents();
        this.red   = rgb[0];
        this.green = rgb[1];
        this.blue  = rgb[2];
    }

    @Override
    public int getColor(BlockState state, ExtendedBlockView blockView, BlockPos position, int tintIndex){
        if(!(state.getBlock() instanceof RedstoneWireBlock)){
            return 0;
        }

        int powerLevel = state.get(RedstoneWireBlock.POWER);
        float powerPercentage = powerLevel / 15.0F;
        float colorIntensity = powerPercentage * 0.6F + 0.4F;
        if(powerLevel == 0){
            colorIntensity = 0.3F;
        }

        return 0xff000000 |
            MathHelper.clamp((int)(red   * colorIntensity * 255.0F), 0, 255) << 16 |
            MathHelper.clamp((int)(green * colorIntensity * 255.0F), 0, 255) << 8 |
            MathHelper.clamp((int)(blue  * colorIntensity * 255.0F), 0, 255);
    }
}

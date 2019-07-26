package net.gudenau.minecraft.redstoneplus.api;

import net.minecraft.block.RedstoneWireBlock;
import net.minecraft.util.DyeColor;

public interface IColoredRedstone{
    RedstoneWireBlock setColor(DyeColor color);
    DyeColor getColor();
}

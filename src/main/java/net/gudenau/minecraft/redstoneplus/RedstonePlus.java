package net.gudenau.minecraft.redstoneplus;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.entity.FabricEntityTypeBuilder;
import net.gudenau.minecraft.redstoneplus.entity.SlimeBallEntity;
import net.gudenau.minecraft.redstoneplus.item.ColoredSlimeItem;
import net.minecraft.block.Block;
import net.minecraft.block.RedstoneWireBlock;
import net.minecraft.block.SlimeBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCategory;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class RedstonePlus implements ModInitializer{
    @SuppressWarnings("WeakerAccess")
    public static class Blocks{
        public static SlimeBlock slimeBlockWhite;
        public static SlimeBlock slimeBlockOrange;
        public static SlimeBlock slimeBlockMagenta;
        public static SlimeBlock slimeBlockLightBlue;
        public static SlimeBlock slimeBlockYellow;
        public static SlimeBlock slimeBlockLime;
        public static SlimeBlock slimeBlockPink;
        public static SlimeBlock slimeBlockGray;
        public static SlimeBlock slimeBlockLightGray;
        public static SlimeBlock slimeBlockCyan;
        public static SlimeBlock slimeBlockPurple;
        public static SlimeBlock slimeBlockBlue;
        public static SlimeBlock slimeBlockBrown;
        public static SlimeBlock slimeBlockGreen;
        public static SlimeBlock slimeBlockRed;
        public static SlimeBlock slimeBlockBlack;

        public static RedstoneWireBlock redstoneWireWhite;
    }

    @SuppressWarnings("WeakerAccess")
    public static class Items{
        public static BlockItem slimeBlockWhiteItem;
        public static BlockItem slimeBlockOrangeItem;
        public static BlockItem slimeBlockMagentaItem;
        public static BlockItem slimeBlockLightBlueItem;
        public static BlockItem slimeBlockYellowItem;
        public static BlockItem slimeBlockLimeItem;
        public static BlockItem slimeBlockPinkItem;
        public static BlockItem slimeBlockGrayItem;
        public static BlockItem slimeBlockLightGrayItem;
        public static BlockItem slimeBlockCyanItem;
        public static BlockItem slimeBlockPurpleItem;
        public static BlockItem slimeBlockBlueItem;
        public static BlockItem slimeBlockBrownItem;
        public static BlockItem slimeBlockGreenItem;
        public static BlockItem slimeBlockRedItem;
        public static BlockItem slimeBlockBlackItem;

        public static ColoredSlimeItem slimeBallWhite;
        public static ColoredSlimeItem slimeBallOrange;
        public static ColoredSlimeItem slimeBallMagenta;
        public static ColoredSlimeItem slimeBallLightBlue;
        public static ColoredSlimeItem slimeBallYellow;
        public static ColoredSlimeItem slimeBallLime;
        public static ColoredSlimeItem slimeBallPink;
        public static ColoredSlimeItem slimeBallGray;
        public static ColoredSlimeItem slimeBallLightGray;
        public static ColoredSlimeItem slimeBallCyan;
        public static ColoredSlimeItem slimeBallPurple;
        public static ColoredSlimeItem slimeBallBlue;
        public static ColoredSlimeItem slimeBallBrown;
        public static ColoredSlimeItem slimeBallGreen;
        public static ColoredSlimeItem slimeBallRed;
        public static ColoredSlimeItem slimeBallBlack;
    }

    @SuppressWarnings("WeakerAccess")
    public static class Entities{
        public static EntityType<SlimeBallEntity> slimeBallEntity;
    }

    @Override
    public void onInitialize(){
        registerBlocks();
        registerItems();
        registerEntities();
    }

    private void registerBlocks(){
        Blocks.slimeBlockWhite     = register("slime_white",      new SlimeBlock(Block.Settings.copy(net.minecraft.block.Blocks.SLIME_BLOCK)));
        Blocks.slimeBlockOrange    = register("slime_orange",     new SlimeBlock(Block.Settings.copy(net.minecraft.block.Blocks.SLIME_BLOCK)));
        Blocks.slimeBlockMagenta   = register("slime_magenta",    new SlimeBlock(Block.Settings.copy(net.minecraft.block.Blocks.SLIME_BLOCK)));
        Blocks.slimeBlockLightBlue = register("slime_light_blue", new SlimeBlock(Block.Settings.copy(net.minecraft.block.Blocks.SLIME_BLOCK)));
        Blocks.slimeBlockYellow    = register("slime_yellow",     new SlimeBlock(Block.Settings.copy(net.minecraft.block.Blocks.SLIME_BLOCK)));
        Blocks.slimeBlockLime      = register("slime_lime",       new SlimeBlock(Block.Settings.copy(net.minecraft.block.Blocks.SLIME_BLOCK)));
        Blocks.slimeBlockPink      = register("slime_pink",       new SlimeBlock(Block.Settings.copy(net.minecraft.block.Blocks.SLIME_BLOCK)));
        Blocks.slimeBlockGray      = register("slime_gray",       new SlimeBlock(Block.Settings.copy(net.minecraft.block.Blocks.SLIME_BLOCK)));
        Blocks.slimeBlockLightGray = register("slime_light_gray", new SlimeBlock(Block.Settings.copy(net.minecraft.block.Blocks.SLIME_BLOCK)));
        Blocks.slimeBlockCyan      = register("slime_cyan",       new SlimeBlock(Block.Settings.copy(net.minecraft.block.Blocks.SLIME_BLOCK)));
        Blocks.slimeBlockPurple    = register("slime_purple",     new SlimeBlock(Block.Settings.copy(net.minecraft.block.Blocks.SLIME_BLOCK)));
        Blocks.slimeBlockBlue      = register("slime_blue",       new SlimeBlock(Block.Settings.copy(net.minecraft.block.Blocks.SLIME_BLOCK)));
        Blocks.slimeBlockBrown     = register("slime_brown",      new SlimeBlock(Block.Settings.copy(net.minecraft.block.Blocks.SLIME_BLOCK)));
        Blocks.slimeBlockGreen     = register("slime_green",      new SlimeBlock(Block.Settings.copy(net.minecraft.block.Blocks.SLIME_BLOCK)));
        Blocks.slimeBlockRed       = register("slime_red",        new SlimeBlock(Block.Settings.copy(net.minecraft.block.Blocks.SLIME_BLOCK)));
        Blocks.slimeBlockBlack     = register("slime_black",      new SlimeBlock(Block.Settings.copy(net.minecraft.block.Blocks.SLIME_BLOCK)));

        // Disabled, not finished
        //Blocks.redstoneWireWhite = register("redstone_wire_white", new RedstoneWireBlock(Block.Settings.copy(net.minecraft.block.Blocks.REDSTONE_WIRE)));
    }

    private void registerItems(){
        Items.slimeBlockWhiteItem     = register(Blocks.slimeBlockWhite,     ItemGroup.DECORATIONS);
        Items.slimeBlockOrangeItem    = register(Blocks.slimeBlockOrange,    ItemGroup.DECORATIONS);
        Items.slimeBlockMagentaItem   = register(Blocks.slimeBlockMagenta,   ItemGroup.DECORATIONS);
        Items.slimeBlockLightBlueItem = register(Blocks.slimeBlockLightBlue, ItemGroup.DECORATIONS);
        Items.slimeBlockYellowItem    = register(Blocks.slimeBlockYellow,    ItemGroup.DECORATIONS);
        Items.slimeBlockLimeItem      = register(Blocks.slimeBlockLime,      ItemGroup.DECORATIONS);
        Items.slimeBlockPinkItem      = register(Blocks.slimeBlockPink,      ItemGroup.DECORATIONS);
        Items.slimeBlockGrayItem      = register(Blocks.slimeBlockGray,      ItemGroup.DECORATIONS);
        Items.slimeBlockLightGrayItem = register(Blocks.slimeBlockLightGray, ItemGroup.DECORATIONS);
        Items.slimeBlockCyanItem      = register(Blocks.slimeBlockCyan,      ItemGroup.DECORATIONS);
        Items.slimeBlockPurpleItem    = register(Blocks.slimeBlockPurple,    ItemGroup.DECORATIONS);
        Items.slimeBlockBlueItem      = register(Blocks.slimeBlockBlue,      ItemGroup.DECORATIONS);
        Items.slimeBlockBrownItem     = register(Blocks.slimeBlockBrown,     ItemGroup.DECORATIONS);
        Items.slimeBlockGreenItem     = register(Blocks.slimeBlockGreen,     ItemGroup.DECORATIONS);
        Items.slimeBlockRedItem       = register(Blocks.slimeBlockRed,       ItemGroup.DECORATIONS);
        Items.slimeBlockBlackItem     = register(Blocks.slimeBlockBlack,     ItemGroup.DECORATIONS);

        Items.slimeBallWhite     = register("slime_ball_white",      new ColoredSlimeItem(DyeColor.WHITE,      new Item.Settings().group(ItemGroup.MISC)));
        Items.slimeBallOrange    = register("slime_ball_orange",     new ColoredSlimeItem(DyeColor.ORANGE,     new Item.Settings().group(ItemGroup.MISC)));
        Items.slimeBallMagenta   = register("slime_ball_magenta",    new ColoredSlimeItem(DyeColor.MAGENTA,    new Item.Settings().group(ItemGroup.MISC)));
        Items.slimeBallLightBlue = register("slime_ball_light_blue", new ColoredSlimeItem(DyeColor.LIGHT_BLUE, new Item.Settings().group(ItemGroup.MISC)));
        Items.slimeBallYellow    = register("slime_ball_yellow",     new ColoredSlimeItem(DyeColor.YELLOW,     new Item.Settings().group(ItemGroup.MISC)));
        Items.slimeBallLime      = register("slime_ball_lime",       new ColoredSlimeItem(DyeColor.LIME,       new Item.Settings().group(ItemGroup.MISC)));
        Items.slimeBallPink      = register("slime_ball_pink",       new ColoredSlimeItem(DyeColor.PINK,       new Item.Settings().group(ItemGroup.MISC)));
        Items.slimeBallGray      = register("slime_ball_gray",       new ColoredSlimeItem(DyeColor.GRAY,       new Item.Settings().group(ItemGroup.MISC)));
        Items.slimeBallLightGray = register("slime_ball_light_gray", new ColoredSlimeItem(DyeColor.LIGHT_GRAY, new Item.Settings().group(ItemGroup.MISC)));
        Items.slimeBallCyan      = register("slime_ball_cyan",       new ColoredSlimeItem(DyeColor.CYAN,       new Item.Settings().group(ItemGroup.MISC)));
        Items.slimeBallPurple    = register("slime_ball_purple",     new ColoredSlimeItem(DyeColor.PURPLE,     new Item.Settings().group(ItemGroup.MISC)));
        Items.slimeBallBlue      = register("slime_ball_blue",       new ColoredSlimeItem(DyeColor.BLUE,       new Item.Settings().group(ItemGroup.MISC)));
        Items.slimeBallBrown     = register("slime_ball_brown",      new ColoredSlimeItem(DyeColor.BROWN,      new Item.Settings().group(ItemGroup.MISC)));
        Items.slimeBallGreen     = register("slime_ball_green",      new ColoredSlimeItem(DyeColor.GREEN,      new Item.Settings().group(ItemGroup.MISC)));
        Items.slimeBallRed       = register("slime_ball_red",        new ColoredSlimeItem(DyeColor.RED,        new Item.Settings().group(ItemGroup.MISC)));
        Items.slimeBallBlack     = register("slime_ball_black",      new ColoredSlimeItem(DyeColor.BLACK,      new Item.Settings().group(ItemGroup.MISC)));
    }

    private void registerEntities(){
        Entities.slimeBallEntity = register(
            "slimeball",
            FabricEntityTypeBuilder.create(EntityCategory.MISC, (EntityType.EntityFactory<SlimeBallEntity>)SlimeBallEntity::new)
                .size(EntityDimensions.changing(0.25F, 0.25F))
                .trackable(5, 20, true)
        );
    }

    private <T extends Block> T register(String name, T block){
        return Registry.register(
            Registry.BLOCK,
            new Identifier("gud_redstoneplus", name),
            block
        );
    }

    private <T extends Item> T register(String name, T item){
        return Registry.register(
            Registry.ITEM,
            new Identifier("gud_redstoneplus", name),
            item
        );
    }

    private static BlockItem register(Block block, ItemGroup group){
        return Registry.register(
            Registry.ITEM,
            Registry.BLOCK.getId(block),
            new BlockItem(block, new Item.Settings().group(group))
        );
    }

    private <T extends Entity> EntityType<T> register(String name, FabricEntityTypeBuilder<T> type){
        return Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier("gud_redstoneplus", name),
            type.build()
        );
    }
}

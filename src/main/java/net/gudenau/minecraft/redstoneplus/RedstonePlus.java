package net.gudenau.minecraft.redstoneplus;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.entity.FabricEntityTypeBuilder;
import net.gudenau.minecraft.redstoneplus.api.IColoredRedstone;
import net.gudenau.minecraft.redstoneplus.entity.SlimeBallEntity;
import net.gudenau.minecraft.redstoneplus.item.ColoredSlimeItem;
import net.gudenau.minecraft.redstoneplus.recipe.RedstoneDyeRecipe;
import net.minecraft.block.Block;
import net.minecraft.block.RedstoneWireBlock;
import net.minecraft.block.SlimeBlock;
import net.minecraft.data.server.recipe.ComplexRecipeJsonFactory;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCategory;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.item.*;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.recipe.SpecialRecipeSerializer;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.function.Consumer;
import java.util.function.Function;

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
        public static RedstoneWireBlock redstoneWireOrange;
        public static RedstoneWireBlock redstoneWireMagenta;
        public static RedstoneWireBlock redstoneWireLightBlue;
        public static RedstoneWireBlock redstoneWireYellow;
        public static RedstoneWireBlock redstoneWireLime;
        public static RedstoneWireBlock redstoneWirePink;
        public static RedstoneWireBlock redstoneWireGray;
        public static RedstoneWireBlock redstoneWireLightGray;
        public static RedstoneWireBlock redstoneWireCyan;
        public static RedstoneWireBlock redstoneWirePurple;
        public static RedstoneWireBlock redstoneWireBlue;
        public static RedstoneWireBlock redstoneWireBrown;
        public static RedstoneWireBlock redstoneWireGreen;
        public static RedstoneWireBlock redstoneWireRed;
        public static RedstoneWireBlock redstoneWireBlack;
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

        public static AliasedBlockItem redstoneDustWhite;
        public static AliasedBlockItem redstoneDustOrange;
        public static AliasedBlockItem redstoneDustMagenta;
        public static AliasedBlockItem redstoneDustLightBlue;
        public static AliasedBlockItem redstoneDustYellow;
        public static AliasedBlockItem redstoneDustLime;
        public static AliasedBlockItem redstoneDustPink;
        public static AliasedBlockItem redstoneDustGray;
        public static AliasedBlockItem redstoneDustLightGray;
        public static AliasedBlockItem redstoneDustCyan;
        public static AliasedBlockItem redstoneDustPurple;
        public static AliasedBlockItem redstoneDustBlue;
        public static AliasedBlockItem redstoneDustBrown;
        public static AliasedBlockItem redstoneDustGreen;
        public static AliasedBlockItem redstoneDustRed;
        public static AliasedBlockItem redstoneDustBlack;
    }

    @SuppressWarnings("WeakerAccess")
    public static class Entities{
        public static EntityType<SlimeBallEntity> slimeBallEntity;
    }

    public static class Recipes{
        public static SpecialRecipeSerializer<RedstoneDyeRecipe> redstoneDye;
    }

    @Override
    public void onInitialize(){
        registerBlocks();
        registerItems();
        registerEntities();
        registerRecipes();
    }

    @SuppressWarnings("ConstantConditions")
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

        Blocks.redstoneWireWhite     = register("redstone_wire_white",      ((IColoredRedstone)new RedstoneWireBlock(Block.Settings.copy(net.minecraft.block.Blocks.REDSTONE_WIRE))).setColor(DyeColor.WHITE));
        Blocks.redstoneWireOrange    = register("redstone_wire_orange",     ((IColoredRedstone)new RedstoneWireBlock(Block.Settings.copy(net.minecraft.block.Blocks.REDSTONE_WIRE))).setColor(DyeColor.ORANGE));
        Blocks.redstoneWireMagenta   = register("redstone_wire_magenta",    ((IColoredRedstone)new RedstoneWireBlock(Block.Settings.copy(net.minecraft.block.Blocks.REDSTONE_WIRE))).setColor(DyeColor.MAGENTA));
        Blocks.redstoneWireLightBlue = register("redstone_wire_light_blue", ((IColoredRedstone)new RedstoneWireBlock(Block.Settings.copy(net.minecraft.block.Blocks.REDSTONE_WIRE))).setColor(DyeColor.LIGHT_BLUE));
        Blocks.redstoneWireYellow    = register("redstone_wire_yellow",     ((IColoredRedstone)new RedstoneWireBlock(Block.Settings.copy(net.minecraft.block.Blocks.REDSTONE_WIRE))).setColor(DyeColor.YELLOW));
        Blocks.redstoneWireLime      = register("redstone_wire_lime",       ((IColoredRedstone)new RedstoneWireBlock(Block.Settings.copy(net.minecraft.block.Blocks.REDSTONE_WIRE))).setColor(DyeColor.LIME));
        Blocks.redstoneWirePink      = register("redstone_wire_pink",       ((IColoredRedstone)new RedstoneWireBlock(Block.Settings.copy(net.minecraft.block.Blocks.REDSTONE_WIRE))).setColor(DyeColor.PINK));
        Blocks.redstoneWireGray      = register("redstone_wire_gray",       ((IColoredRedstone)new RedstoneWireBlock(Block.Settings.copy(net.minecraft.block.Blocks.REDSTONE_WIRE))).setColor(DyeColor.GRAY));
        Blocks.redstoneWireLightGray = register("redstone_wire_light_gray", ((IColoredRedstone)new RedstoneWireBlock(Block.Settings.copy(net.minecraft.block.Blocks.REDSTONE_WIRE))).setColor(DyeColor.LIGHT_GRAY));
        Blocks.redstoneWireCyan      = register("redstone_wire_cyan",       ((IColoredRedstone)new RedstoneWireBlock(Block.Settings.copy(net.minecraft.block.Blocks.REDSTONE_WIRE))).setColor(DyeColor.CYAN));
        Blocks.redstoneWirePurple    = register("redstone_wire_purple",     ((IColoredRedstone)new RedstoneWireBlock(Block.Settings.copy(net.minecraft.block.Blocks.REDSTONE_WIRE))).setColor(DyeColor.PURPLE));
        Blocks.redstoneWireBlue      = register("redstone_wire_blue",       ((IColoredRedstone)new RedstoneWireBlock(Block.Settings.copy(net.minecraft.block.Blocks.REDSTONE_WIRE))).setColor(DyeColor.BLUE));
        Blocks.redstoneWireBrown     = register("redstone_wire_brown",      ((IColoredRedstone)new RedstoneWireBlock(Block.Settings.copy(net.minecraft.block.Blocks.REDSTONE_WIRE))).setColor(DyeColor.BROWN));
        Blocks.redstoneWireGreen     = register("redstone_wire_green",      ((IColoredRedstone)new RedstoneWireBlock(Block.Settings.copy(net.minecraft.block.Blocks.REDSTONE_WIRE))).setColor(DyeColor.GREEN));
        Blocks.redstoneWireRed       = register("redstone_wire_red",        ((IColoredRedstone)new RedstoneWireBlock(Block.Settings.copy(net.minecraft.block.Blocks.REDSTONE_WIRE))).setColor(DyeColor.RED));
        Blocks.redstoneWireBlack     = register("redstone_wire_black",      ((IColoredRedstone)new RedstoneWireBlock(Block.Settings.copy(net.minecraft.block.Blocks.REDSTONE_WIRE))).setColor(DyeColor.BLACK));
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

        Items.redstoneDustWhite    = register("redstone_dust_white",      new AliasedBlockItem(Blocks.redstoneWireWhite, new Item.Settings().group(ItemGroup.REDSTONE)));
        Items.redstoneDustOrange   = register("redstone_dust_orange",     new AliasedBlockItem(Blocks.redstoneWireOrange, new Item.Settings().group(ItemGroup.REDSTONE)));
        Items.redstoneDustMagenta  = register("redstone_dust_magenta",    new AliasedBlockItem(Blocks.redstoneWireMagenta, new Item.Settings().group(ItemGroup.REDSTONE)));
        Items.redstoneDustLightBlue= register("redstone_dust_light_blue", new AliasedBlockItem(Blocks.redstoneWireLightBlue, new Item.Settings().group(ItemGroup.REDSTONE)));
        Items.redstoneDustYellow   = register("redstone_dust_yellow",     new AliasedBlockItem(Blocks.redstoneWireYellow, new Item.Settings().group(ItemGroup.REDSTONE)));
        Items.redstoneDustLime     = register("redstone_dust_lime",       new AliasedBlockItem(Blocks.redstoneWireLime, new Item.Settings().group(ItemGroup.REDSTONE)));
        Items.redstoneDustPink     = register("redstone_dust_pink",       new AliasedBlockItem(Blocks.redstoneWirePink, new Item.Settings().group(ItemGroup.REDSTONE)));
        Items.redstoneDustGray     = register("redstone_dust_gray",       new AliasedBlockItem(Blocks.redstoneWireGray, new Item.Settings().group(ItemGroup.REDSTONE)));
        Items.redstoneDustLightGray= register("redstone_dust_light_gray", new AliasedBlockItem(Blocks.redstoneWireLightGray, new Item.Settings().group(ItemGroup.REDSTONE)));
        Items.redstoneDustCyan     = register("redstone_dust_cyan",       new AliasedBlockItem(Blocks.redstoneWireCyan, new Item.Settings().group(ItemGroup.REDSTONE)));
        Items.redstoneDustPurple   = register("redstone_dust_purple",     new AliasedBlockItem(Blocks.redstoneWirePurple, new Item.Settings().group(ItemGroup.REDSTONE)));
        Items.redstoneDustBlue     = register("redstone_dust_blue",       new AliasedBlockItem(Blocks.redstoneWireBlue, new Item.Settings().group(ItemGroup.REDSTONE)));
        Items.redstoneDustBrown    = register("redstone_dust_brown",      new AliasedBlockItem(Blocks.redstoneWireBrown, new Item.Settings().group(ItemGroup.REDSTONE)));
        Items.redstoneDustGreen    = register("redstone_dust_green",      new AliasedBlockItem(Blocks.redstoneWireGreen, new Item.Settings().group(ItemGroup.REDSTONE)));
        Items.redstoneDustRed      = register("redstone_dust_red",        new AliasedBlockItem(Blocks.redstoneWireRed, new Item.Settings().group(ItemGroup.REDSTONE)));
        Items.redstoneDustBlack    = register("redstone_dust_black",      new AliasedBlockItem(Blocks.redstoneWireBlack, new Item.Settings().group(ItemGroup.REDSTONE)));
    }

    private void registerEntities(){
        Entities.slimeBallEntity = register(
            "slimeball",
            FabricEntityTypeBuilder.create(EntityCategory.MISC, (EntityType.EntityFactory<SlimeBallEntity>)SlimeBallEntity::new)
                .size(EntityDimensions.changing(0.25F, 0.25F))
                .trackable(5, 20, true)
        );
    }

    // At this point the Fabric people stopped being helpful so I just did whatever worked.
    // If you know a better way, please PR it.
    public void registerRecipes(){
        register(
            "crafting_special_dye_redstone"
        );
        register(
            "crafting_special_dye_redstone",
            new SpecialRecipeSerializer<>(RedstoneDyeRecipe::new)
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

    private <S extends RecipeSerializer<T>, T extends Recipe<?>> S register(String name, S serializer) {
        return Registry.register(
            Registry.RECIPE_SERIALIZER,
            new Identifier("gud_redstoneplus", name),
            serializer
        );
    }

    private <T extends Recipe<?>> RecipeType<T> register(String name){
        return Registry.register(
            Registry.RECIPE_TYPE,
            new Identifier("gud_redstoneplus", name),
            new RecipeType<T>(){
                public String toString(){
                    return name;
                }
            }
        );
    }
}

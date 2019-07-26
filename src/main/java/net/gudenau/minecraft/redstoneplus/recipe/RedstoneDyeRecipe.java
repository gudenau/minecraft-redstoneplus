package net.gudenau.minecraft.redstoneplus.recipe;

import net.gudenau.minecraft.redstoneplus.RedstonePlus;
import net.minecraft.block.MaterialColor;
import net.minecraft.block.RedstoneWireBlock;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.AliasedBlockItem;
import net.minecraft.item.DyeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SpecialCraftingRecipe;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class RedstoneDyeRecipe extends SpecialCraftingRecipe{
    public RedstoneDyeRecipe(Identifier identifier){
        super(identifier);
    }

    @Override
    public boolean matches(CraftingInventory inventory, World world){
        ItemStack dye = null;
        List<ItemStack> dusts = new ArrayList<>();

        for(int slot = 0; slot < inventory.getInvSize(); slot++){
            ItemStack stackInSlot = inventory.getInvStack(slot);
            if(stackInSlot.isEmpty()){
                continue;
            }
            Item item = stackInSlot.getItem();
            if(item instanceof DyeItem){
                if(dye == null){
                    dye = stackInSlot;
                }else{
                    return false;
                }
            }else if(item instanceof AliasedBlockItem){
                AliasedBlockItem blockItem = (AliasedBlockItem)item;
                if(blockItem.getBlock() instanceof RedstoneWireBlock){
                    dusts.add(stackInSlot);
                }
            }else{
                return false;
            }
        }

        return dye != null && !dusts.isEmpty();
    }

    @Override
    public ItemStack craft(CraftingInventory inventory){
        DyeColor dye = null;
        List<ItemStack> dusts = new ArrayList<>();

        for(int slot = 0; slot < inventory.getInvSize(); slot++){
            ItemStack stackInSlot = inventory.getInvStack(slot);
            if(stackInSlot.isEmpty()){
                continue;
            }
            Item item = stackInSlot.getItem();
            if(item instanceof DyeItem){
                if(dye == null){
                    dye = ((DyeItem)item).getColor();
                }else{
                    return ItemStack.EMPTY;
                }
            }else if(item instanceof AliasedBlockItem){
                AliasedBlockItem blockItem = (AliasedBlockItem)item;
                if(blockItem.getBlock() instanceof RedstoneWireBlock){
                    dusts.add(stackInSlot);
                }
            }else{
                return ItemStack.EMPTY;
            }
        }

        if(dye == null || dusts.isEmpty()){
            return ItemStack.EMPTY;
        }

        Item item;
        switch(dye){
            case WHITE: item = RedstonePlus.Items.redstoneDustWhite; break;
            case ORANGE: item = RedstonePlus.Items.redstoneDustOrange; break;
            case MAGENTA: item = RedstonePlus.Items.redstoneDustMagenta; break;
            case LIGHT_BLUE: item = RedstonePlus.Items.redstoneDustLightBlue; break;
            case YELLOW: item = RedstonePlus.Items.redstoneDustYellow; break;
            case LIME: item = RedstonePlus.Items.redstoneDustLime; break;
            case PINK: item = RedstonePlus.Items.redstoneDustPink; break;
            case GRAY: item = RedstonePlus.Items.redstoneDustGray; break;
            case LIGHT_GRAY: item = RedstonePlus.Items.redstoneDustLightGray; break;
            case CYAN: item = RedstonePlus.Items.redstoneDustCyan; break;
            case PURPLE: item = RedstonePlus.Items.redstoneDustPurple; break;
            case BLUE: item = RedstonePlus.Items.redstoneDustBlue; break;
            case BROWN: item = RedstonePlus.Items.redstoneDustBrown; break;
            case GREEN: item = RedstonePlus.Items.redstoneDustGreen; break;
            case RED: item = RedstonePlus.Items.redstoneDustRed; break;
            case BLACK: item = RedstonePlus.Items.redstoneDustBlack; break;
            default: return ItemStack.EMPTY;
        }

        return new ItemStack(item, dusts.size());
    }

    @Override
    public boolean fits(int width, int height){
        return width * height >= 2;
    }

    @Override
    public RecipeSerializer<?> getSerializer(){
        return RedstonePlus.Recipes.redstoneDye;
    }
}

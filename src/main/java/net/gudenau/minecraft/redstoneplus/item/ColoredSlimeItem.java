package net.gudenau.minecraft.redstoneplus.item;

import net.gudenau.minecraft.redstoneplus.entity.SlimeBallEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stat.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class ColoredSlimeItem extends Item{
    private DyeColor color;

    public ColoredSlimeItem(DyeColor color, Settings settings){
        super(settings);
        this.color = color;
    }

   @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand){
        ItemStack stack = player.getStackInHand(hand);
        if(!player.isCreative()){
            stack.decrement(1);
        }

        if(!world.isClient){
            SlimeBallEntity entity = new SlimeBallEntity(player, world);

            entity.setItem(stack);
            entity.method_19207(player, player.pitch, player.yaw, 0.0F, 1.5F, 1.0F);

            world.spawnEntity(entity);
        }

        player.incrementStat(Stats.USED.getOrCreateStat(this));
        return new TypedActionResult<>(ActionResult.PASS, stack);
    }

    public DyeColor getColor(){
        return color;
    }
}

package net.gudenau.minecraft.redstoneplus.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.gudenau.minecraft.redstoneplus.api.IColoredRedstone;
import net.minecraft.block.*;
import net.minecraft.block.enums.WireConnection;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.DyeColor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;
import java.util.Set;

@Mixin(RedstoneWireBlock.class)
public abstract class RedstoneWireBlockMixin extends Block implements IColoredRedstone{
    private RedstoneWireBlockMixin(Settings settings){
        super(settings);
    }

    @Shadow @Final public static IntProperty POWER;
    @Shadow private boolean wiresGivePower;
    @Shadow @Final private Set<BlockPos> affectedNeighbors;

    private DyeColor color;

    @Inject(
        method = "<init>",
        at = @At("RETURN")
    )
    private void onInit(Block.Settings settings, CallbackInfo info){
        color = null;
    }

    /**
     * @author gudenau
     */
    @Overwrite
    @Environment(EnvType.CLIENT)
    public void randomDisplayTick(BlockState blockState_1, World world, BlockPos position, Random random_1) {
        int power = blockState_1.get(POWER);
        if (power != 0) {
            double x = position.getX() + 0.5D + (random_1.nextFloat() - 0.5D) * 0.2D;
            double y = position.getY() + 0.0625F;
            double z = position.getZ() + 0.5D + (random_1.nextFloat() - 0.5D) * 0.2D;
            float powerPercent = (float)power / 15.0F;
            float colorScale = powerPercent * 0.6F + 0.4F;

            float red;
            float green;
            float blue;

            if(color == null){
                red = colorScale;
                green = Math.max(0.0F, powerPercent * powerPercent * 0.7F - 0.5F);
                blue = Math.max(0.0F, powerPercent * powerPercent * 0.6F - 0.7F);
            }else{
                float[] rgb = color.getColorComponents();
                red   = rgb[0] * powerPercent;
                green = rgb[1] * powerPercent;
                blue  = rgb[2] * powerPercent;
            }
            world.addParticle(new DustParticleEffect(red, green, blue, 1.0F), x, y, z, 0.0D, 0.0D, 0.0D);
        }
    }

    /**
     * @author gudenau
     */
    @Overwrite
    private BlockState updateLogic(World world, BlockPos position, BlockState state){
        int currentPower = state.get(POWER);
        wiresGivePower = false;
        int receivedPower = world.getReceivedRedstonePower(position);
        wiresGivePower = true;
        int calculatedPower = 0;
        if(receivedPower < 15){
            for(Direction horizontalDirection : Direction.Type.HORIZONTAL){
                BlockPos neighborPosition = position.offset(horizontalDirection);
                BlockState neighborState = world.getBlockState(neighborPosition);
                calculatedPower = increasePower(calculatedPower, neighborState);
                BlockPos blockPos_3 = position.up();
                if(
                    neighborState.isSimpleFullBlock(world, neighborPosition) &&
                    !world.getBlockState(blockPos_3).isSimpleFullBlock(world, blockPos_3)
                ){
                    calculatedPower = increasePower(calculatedPower, world.getBlockState(neighborPosition.up()));
                }else if(!neighborState.isSimpleFullBlock(world, neighborPosition)){
                    calculatedPower = increasePower(calculatedPower, world.getBlockState(neighborPosition.down()));
                }
            }
        }

        int newPower = calculatedPower - 1;
        if(receivedPower > newPower){
            newPower = receivedPower;
        }

        BlockState originalState = state;
        if(currentPower != newPower){
            state = state.with(POWER, newPower);
            if(world.getBlockState(position) == originalState){
                world.setBlockState(position, state, 2);
            }

            affectedNeighbors.add(position);

            for(Direction direction : Direction.values()){
                affectedNeighbors.add(position.offset(direction));
            }
        }

        return state;
    }

    /**
     * @author gudenau
     */
    @Overwrite
    private int increasePower(int power, BlockState state) {
        Block block = state.getBlock();
        if(block instanceof RedstoneWireBlock){
            if(block == this || block == Blocks.REDSTONE_WIRE || this == Blocks.REDSTONE_WIRE){
                int statePower = state.get(POWER);
                return Math.max(statePower, power);
            }
        }
        return power;
    }

    /**
     * @author gudenau
     */
    @Overwrite
    private WireConnection getRenderConnectionType(BlockView blockView, BlockPos position, Direction direction){
        BlockPos facingPos = position.offset(direction);
        BlockState facingState = blockView.getBlockState(facingPos);
        BlockPos upPosition = position.up();
        BlockState upState = blockView.getBlockState(upPosition);
        if(!upState.isSimpleFullBlock(blockView, upPosition)){
            boolean isSolidOrHopper = facingState.method_20827(blockView, facingPos, Direction.UP) ||
                facingState.getBlock() == Blocks.HOPPER;
            if(isSolidOrHopper && connectsToDynamic(blockView.getBlockState(facingPos.up()))){
                if(facingState.method_21743(blockView, facingPos)){
                    return WireConnection.UP;
                }

                return WireConnection.SIDE;
            }
        }

        return !connectsToDynamic(facingState, direction) && (facingState.isSimpleFullBlock(blockView, facingPos) || !connectsToDynamic(blockView.getBlockState(facingPos.down()))) ? WireConnection.NONE : WireConnection.SIDE;
    }

    /*
    private boolean connectsToDynamic(BlockView blockView, BlockPos position){
        return connectsToDynamic(blockView.getBlockState(position));
    }
     */

    private boolean connectsToDynamic(BlockState state){
        return connectsToDynamic(state, null);
    }

    private boolean connectsToDynamic(BlockState state, Direction direction){
        Block block = state.getBlock();
        if(block instanceof RedstoneWireBlock){
            return block == this || block == Blocks.REDSTONE_WIRE || this == Blocks.REDSTONE_WIRE;
        }else if(state.getBlock() == Blocks.REPEATER){
            Direction repeaterFacing = state.get(RepeaterBlock.FACING);
            return repeaterFacing == direction || repeaterFacing.getOpposite() == direction;
        }else if(Blocks.OBSERVER == state.getBlock()){
            return direction == state.get(ObserverBlock.FACING);
        }else{
            return state.emitsRedstonePower() && direction != null;
        }
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public RedstoneWireBlock setColor(DyeColor color){
        this.color = color;
        return (RedstoneWireBlock)((Object)this);
    }

    @Override
    public DyeColor getColor(){
        return color;
    }
}

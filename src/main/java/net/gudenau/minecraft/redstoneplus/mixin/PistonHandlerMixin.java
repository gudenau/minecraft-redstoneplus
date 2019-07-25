package net.gudenau.minecraft.redstoneplus.mixin;

import net.minecraft.block.*;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.block.piston.PistonHandler;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.List;

@Mixin(PistonHandler.class)
public abstract class PistonHandlerMixin{
    @Shadow @Final private World world;
    @Shadow @Final private BlockPos posFrom;
    @Shadow @Final private boolean field_12247;
    @Shadow @Final private BlockPos posTo;
    @Shadow @Final private Direction direction;
    @Shadow @Final private List<BlockPos> movedBlocks;
    @Shadow @Final private List<BlockPos> brokenBlocks;
    @Shadow @Final private Direction field_12248;

    @Shadow private void method_11539(int int_1, int int_2){}
    @Shadow private boolean method_11538(BlockPos blockPos_1){ return false; }

    private Block currentSlime = null;

    /**
     * @author gudenau
     * @reason Dev
     */
    @Overwrite
    public boolean calculatePush(){
        movedBlocks.clear();
        brokenBlocks.clear();
        BlockState blockState_1 = world.getBlockState(posTo);
        if(!PistonBlock.isMovable(blockState_1, world, posTo, direction, false, field_12248)){
            if(field_12247 && blockState_1.getPistonBehavior() == PistonBehavior.DESTROY){
                brokenBlocks.add(posTo);
                return true;
            }else{
                return false;
            }
        }else if(!tryMove(posTo, direction)){
            return false;
        }else{
            for(int int_1 = 0; int_1 < movedBlocks.size(); ++int_1){
                BlockPos blockPos_1 = movedBlocks.get(int_1);
                if(world.getBlockState(blockPos_1).getBlock() instanceof SlimeBlock && !method_11538(blockPos_1)){
                    return false;
                }
            }

            return true;
        }
    }

    /**
     * @author gudenau
     * @reason Dev
     */
    @Overwrite
    private boolean tryMove(BlockPos startingPosition, Direction direction){
        BlockState blockState = world.getBlockState(startingPosition);
        Block block = blockState.getBlock();

        if(block instanceof SlimeBlock && block != Blocks.SLIME_BLOCK){
            if(currentSlime == null){
                currentSlime = block;
            }else if(currentSlime != block){
                return true;
            }
        }

        if(blockState.isAir()){
            return true;
        }else if(!PistonBlock.isMovable(blockState, world, startingPosition, this.direction, false, direction)){
            return true;
        }else if(startingPosition.equals(posFrom)){
            return true;
        }else if(movedBlocks.contains(startingPosition)){
            return true;
        }else{
            int blockCount = 1;
            if(blockCount + movedBlocks.size() > 12){
                return false;
            }else{
                while(block instanceof SlimeBlock){
                    if(block != Blocks.SLIME_BLOCK){
                        if(currentSlime == null){
                            currentSlime = block;
                        }else if(currentSlime != block){
                            blockCount--;
                            break;
                        }
                    }

                    BlockPos nextPosition = startingPosition.offset(this.direction.getOpposite(), blockCount);
                    blockState = world.getBlockState(nextPosition);
                    block = blockState.getBlock();
                    if(blockState.isAir() || !PistonBlock.isMovable(blockState, world, nextPosition, this.direction, false, this.direction.getOpposite()) || nextPosition.equals(posFrom)){
                        break;
                    }

                    ++blockCount;
                    if(blockCount + movedBlocks.size() > 12){
                        return false;
                    }
                }

                if(blockCount == 0){
                    return true;
                }

                int int_2 = 0;

                int int_4;
                for(int_4 = blockCount - 1; int_4 >= 0; --int_4){
                    movedBlocks.add(startingPosition.offset(this.direction.getOpposite(), int_4));
                    ++int_2;
                }

                int_4 = 1;

                while(true){
                    BlockPos blockPos_3 = startingPosition.offset(this.direction, int_4);
                    int int_5 = movedBlocks.indexOf(blockPos_3);
                    if(int_5 > -1){
                        method_11539(int_2, int_5);

                        for(int int_6 = 0; int_6 <= int_5 + int_2; ++int_6){
                            BlockPos blockPos_4 = movedBlocks.get(int_6);
                            if(world.getBlockState(blockPos_4).getBlock() instanceof SlimeBlock && !method_11538(blockPos_4)){
                                return false;
                            }
                        }

                        return true;
                    }

                    blockState = world.getBlockState(blockPos_3);
                    if(blockState.isAir()){
                        return true;
                    }

                    if(!PistonBlock.isMovable(blockState, world, blockPos_3, this.direction, true, this.direction) || blockPos_3.equals(posFrom)){
                        return false;
                    }

                    if(blockState.getPistonBehavior() == PistonBehavior.DESTROY){
                        brokenBlocks.add(blockPos_3);
                        return true;
                    }

                    if(movedBlocks.size() >= 12){
                        return false;
                    }

                    movedBlocks.add(blockPos_3);
                    ++int_2;
                    ++int_4;
                }
            }
        }
    }
}

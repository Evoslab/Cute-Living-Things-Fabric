package com.github.Evoslab.surfandturfcuties.entity.BigClawedCrab;

import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityGroup;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.ai.pathing.*;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class BigClawedCrab extends AnimalEntity {

    public BigClawedCrab(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
        this.setPathfindingPenalty(PathNodeType.WATER, 0.0F);
    }

    protected void initGoals() {

        super.initGoals();
        this.goalSelector.add(0, new EscapeDangerGoal(this, 1.25D));
        this.goalSelector.add(1, new LookAroundGoal(this));
        this.goalSelector.add(2, new MoveIntoWaterGoal(this));
        this.goalSelector.add(3, new WanderAroundGoal(this, 0.5D));
        this.goalSelector.add(4, new SwimAroundGoal(this, 1.0D, 10));

    }

    protected EntityNavigation createNavigation(World world) {
        return new BigClawedCrab.Navigation(this, world);
    }

    public EntityGroup getGroup() {
        return EntityGroup.AQUATIC;
    }

    public boolean canBreatheInWater() {
        return true;
    }

    @Nullable
    @Override
    public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        return null;
    }

    static class Navigation extends MobNavigation {
        public Navigation(BigClawedCrab entity, World world) {
            super(entity, world);
        }

        protected PathNodeNavigator createPathNodeNavigator(int range) {
            this.nodeMaker = new LandPathNodeMaker();
            return new PathNodeNavigator(this.nodeMaker, range);
        }

        public boolean isValidPosition(BlockPos pos) {
            return this.world.getBlockState(pos).isOf(Blocks.WATER) || super.isValidPosition(pos);
        }

    }

}

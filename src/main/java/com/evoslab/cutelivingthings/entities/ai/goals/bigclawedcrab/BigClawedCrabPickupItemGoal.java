package com.evoslab.cutelivingthings.entities.ai.goals.bigclawedcrab;

import com.evoslab.cutelivingthings.entities.BigClawedCrabEntity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.item.ItemStack;

import java.util.EnumSet;
import java.util.List;

public class BigClawedCrabPickupItemGoal extends Goal {
    protected final BigClawedCrabEntity entity;

    public BigClawedCrabPickupItemGoal(BigClawedCrabEntity entity) {
        this.entity = entity;
        this.setControls(EnumSet.of(Control.MOVE));
    }

    @Override
    public boolean canStart() {
        if (!entity.getEquippedStack(EquipmentSlot.MAINHAND).isEmpty()) {
            return false;
        } else if (entity.getTarget() == null) {
            List<ItemEntity> list = entity.world.getEntitiesByClass(ItemEntity.class, entity.getBoundingBox().expand(8.0D, 8.0D, 8.0D), BigClawedCrabEntity.PICKABLE_DROP_FILTER);
            return !list.isEmpty() && entity.getEquippedStack(EquipmentSlot.MAINHAND).isEmpty();
        } else {
            return false;
        }
    }

    @Override
    public void tick() {
        List<ItemEntity> list = entity.world.getEntitiesByClass(ItemEntity.class, entity.getBoundingBox().expand(8.0D, 8.0D, 8.0D), BigClawedCrabEntity.PICKABLE_DROP_FILTER);
        ItemStack itemStack = entity.getEquippedStack(EquipmentSlot.MAINHAND);
        if (itemStack.isEmpty() && !list.isEmpty()) {
            entity.getNavigation().startMovingTo(list.get(0), 1.2000000476837158D);
        }
    }

    @Override
    public void start() {
        List<ItemEntity> list = entity.world.getEntitiesByClass(ItemEntity.class, entity.getBoundingBox().expand(8.0D, 8.0D, 8.0D), BigClawedCrabEntity.PICKABLE_DROP_FILTER);
        if (!list.isEmpty()) {
            entity.getNavigation().startMovingTo(list.get(0), 1.2000000476837158D);
        }
    }
}
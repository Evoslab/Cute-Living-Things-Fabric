package com.evoslab.cutelivingthings.entities;

import com.evoslab.cutelivingthings.entities.ai.goals.bigclawedcrab.BigClawedCrabPickupItemGoal;
import com.evoslab.cutelivingthings.entities.ai.goals.bigclawedcrab.DigInSandGoal;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ItemStackParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.recipe.Ingredient;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.*;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import javax.annotation.Nullable;
import java.util.function.Predicate;

public class BigClawedCrabEntity extends AnimalEntity implements IAnimatable {
    private final AnimationFactory factory = new AnimationFactory(this);
    public static final Predicate<ItemEntity> PICKABLE_DROP_FILTER = item -> item != null && !item.cannotPickup();
    private int eatingTime;
    public boolean ateSpecialFood = false;

    public BigClawedCrabEntity(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(1, new EscapeDangerGoal(this, 1.25D));
        this.goalSelector.add(2, new TemptGoal(this, 1.2D, temptationAllItems(), false));
        this.goalSelector.add(2, new BigClawedCrabPickupItemGoal(this));
        this.goalSelector.add(2, new DigInSandGoal(this));
        this.goalSelector.add(3, new WanderAroundFarGoal(this, 1.0D));
        this.goalSelector.add(4, new LookAtEntityGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.add(5, new LookAroundGoal(this));
        this.setCanPickUpLoot(true);
    }

    public void tickMovement() {
        if (!this.world.isClient && this.isAlive() && this.canMoveVoluntarily()) {
            ++this.eatingTime;
            ItemStack itemStack = this.getEquippedStack(EquipmentSlot.MAINHAND);
            if (this.canEat(itemStack)) {
                if (this.eatingTime > 600) {
                    ItemStack itemStack2 = itemStack.finishUsing(this.world, this);
                    if (!itemStack2.isEmpty()) {
                        this.equipStack(EquipmentSlot.MAINHAND, itemStack2);
                    }
                    if (itemStack.getItem() == Items.GOLDEN_CARROT || itemStack.getItem() == Items.GOLDEN_APPLE) {
                        this.world.sendEntityStatus(this, (byte)69);
                    }

                    this.eatingTime = 0;
                } else if (this.eatingTime > 560 && this.random.nextFloat() < 0.1F) {
                    this.playSound(this.getEatSound(itemStack), 1.0F, 1.0F);
                    this.world.sendEntityStatus(this, (byte)45);
                }
            }
        }

        if (this.isImmobile()) {
            this.jumping = false;
            this.sidewaysSpeed = 0.0F;
            this.forwardSpeed = 0.0F;
        }

        super.tickMovement();
    }

    @Override
    public void handleStatus(byte status) {
        if (status == 45) {
            ItemStack itemStack = this.getEquippedStack(EquipmentSlot.MAINHAND);
            if (!itemStack.isEmpty()) {
                for(int i = 0; i < 8; ++i) {
                    Vec3d vec3d = (new Vec3d(((double)this.random.nextFloat() - 0.5D) * 0.1D, Math.random() * 0.1D + 0.1D, 0.0D)).rotateX(-this.getPitch() * 0.017453292F).rotateY(-this.getYaw() * 0.017453292F);
                    this.world.addParticle(new ItemStackParticleEffect(ParticleTypes.ITEM, itemStack), this.getX() + this.getRotationVector().x / 2.0D, this.getY(), this.getZ() + this.getRotationVector().z / 2.0D, vec3d.x, vec3d.y + 0.05D, vec3d.z);
                }
            }
        } if (status == 69) {
            this.ateSpecialFood = true;
        } else {
            super.handleStatus(status);
        }
    }

    private boolean canEat(ItemStack stack) {
        return stack.getItem().isFood() && this.getTarget() == null && this.onGround;
    }

    protected void initEquipment(LocalDifficulty difficulty) {
        if (this.random.nextFloat() < 0.2F) {
            float f = this.random.nextFloat();
            ItemStack itemStack;
            if (f < 0.01F) {
                itemStack = new ItemStack(Items.DIAMOND);
            } else if (f < 0.2F) {
                itemStack = new ItemStack(Items.GOLD_INGOT);
            } else {
                itemStack = new ItemStack(Items.CHEST);
            }

            this.equipStack(EquipmentSlot.MAINHAND, itemStack);
        }

    }

    public boolean canPickupItem(ItemStack stack) {
        Item item = stack.getItem();
        ItemStack itemStack = this.getEquippedStack(EquipmentSlot.MAINHAND);
        return itemStack.isEmpty() || this.eatingTime > 0 && item.isFood() && !itemStack.getItem().isFood();
    }

    private void dropItem(ItemStack stack) {
        ItemEntity itemEntity = new ItemEntity(this.world, this.getX(), this.getY(), this.getZ(), stack);
        this.world.spawnEntity(itemEntity);
    }

    protected void loot(ItemEntity item) {
        ItemStack itemStack = item.getStack();
        if (this.canPickupItem(itemStack)) {
            int i = itemStack.getCount();
            if (i > 1) {
                this.dropItem(itemStack.split(i - 1));
            }

            this.spit(this.getEquippedStack(EquipmentSlot.MAINHAND));
            this.triggerItemPickedUpByEntityCriteria(item);
            this.equipStack(EquipmentSlot.MAINHAND, itemStack.split(1));
            this.handDropChances[EquipmentSlot.MAINHAND.getEntitySlotId()] = 2.0F;
            this.sendPickup(item, itemStack.getCount());
            item.discard();
            this.eatingTime = 0;
        }

    }

    private void spit(ItemStack stack) {
        if (!stack.isEmpty() && !this.world.isClient) {
            ItemEntity itemEntity = new ItemEntity(this.world, this.getX() + this.getRotationVector().x, this.getY() + 1.0D, this.getZ() + this.getRotationVector().z, stack);
            itemEntity.setPickupDelay(40);
            itemEntity.setThrower(this.getUuid());
            this.playSound(SoundEvents.ENTITY_FOX_SPIT, 1.0F, 1.0F);
            this.world.spawnEntity(itemEntity);
        }
    }







    public static Ingredient temptationAllItems() {
        return Ingredient.ofItems(Items.COOKED_PORKCHOP, Items.COOKED_BEEF, Items.COOKED_COD, Items.GOLDEN_APPLE, Items.GOLDEN_CARROT, Items.DIAMOND, Items.GOLD_INGOT, Items.EMERALD, Items.NETHERITE_INGOT);
    }

    public static boolean temptationEatItems(ItemStack stack) {
        return stack.isOf(Items.COOKED_PORKCHOP) || stack.isOf(Items.COOKED_BEEF) || stack.isOf(Items.COOKED_COD) || stack.isOf(Items.GOLDEN_APPLE) || stack.isOf(Items.GOLDEN_CARROT);
    }

    @Override
    public EntityGroup getGroup() {
        return EntityGroup.AQUATIC;
    }

    @Override
    public boolean canBreatheInWater() {
        return true;
    }

    @Override
    protected int getNextAirUnderwater(int air) {
        return air;
    }

    @Nullable
    @Override
    public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        return null;
    }

    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return temptationEatItems(stack);
    }

    @Override
    public void registerControllers(AnimationData animationData) {
        animationData.addAnimationController(new AnimationController<>(this, "controller", 0, this::predicate));
    }

    @Override
    protected void pushAway(Entity entity) {
        super.pushAway(entity);
        if (world.getDifficulty() != Difficulty.PEACEFUL && entity instanceof LivingEntity && !(entity instanceof BigClawedCrabEntity))
            entity.damage(DamageSource.CACTUS, 1.0F);
    }

    private <P extends IAnimatable> PlayState predicate(AnimationEvent<P> event) {
        if (event.isMoving())
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.bigclawedcrab.walk", true));
        else
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.bigclawedcrab.idle", true));
        return PlayState.CONTINUE;
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    @Override
    public boolean canSpawn(WorldView world) {
        return world.intersectsEntities(this);
    }

    @Nullable
    public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData, @Nullable NbtCompound entityNbt) {
        this.initEquipment(difficulty);
        return super.initialize(world, difficulty, spawnReason, entityData, entityNbt);
    }

    public static DefaultAttributeContainer.Builder createBigClawedCrabAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 8.0D)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.25D);
    }
}
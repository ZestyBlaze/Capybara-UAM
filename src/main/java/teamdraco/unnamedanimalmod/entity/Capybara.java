package teamdraco.unnamedanimalmod.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.PathFinder;
import net.minecraft.world.level.pathfinder.PathType;
import net.minecraft.world.level.pathfinder.WalkNodeEvaluator;
import net.minecraft.world.phys.shapes.CollisionContext;
import org.jetbrains.annotations.Nullable;
import teamdraco.unnamedanimalmod.data.providers.UAMItemTagsProvider;
import teamdraco.unnamedanimalmod.registry.EntityRegistry;
import teamdraco.unnamedanimalmod.registry.SoundRegistry;

public class Capybara extends TamableAnimal implements MenuProvider {
    public static final EntityDimensions BABY_DIMENSIONS = EntityRegistry.CAPYBARA.get().getDimensions()
            .scale(0.5f)
            .withEyeHeight(0.5f);
    public static final EntityDataAccessor<Integer> CHESTS = SynchedEntityData.defineId(Capybara.class, EntityDataSerializers.INT);
    protected SimpleContainer inventory;

    public Capybara(EntityType<? extends TamableAnimal> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new PanicGoal(this, 2.0d));
        this.goalSelector.addGoal(2, new SitWhenOrderedToGoal(this));
        this.goalSelector.addGoal(3, new BreedGoal(this, 1.0d));
        this.goalSelector.addGoal(4, new TemptGoal(this, 1.25d, stack -> stack.is(UAMItemTagsProvider.CAPYBARA_FOOD), false));
        this.goalSelector.addGoal(5, new FollowParentGoal(this, 1.25d));
        this.goalSelector.addGoal(6, new FollowOwnerGoal(this, 1.0d, 10.0f, 2.0f));
        this.goalSelector.addGoal(7, new RandomStrollGoal(this, 1.0d));
        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 6.0f));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(9, new CapybaraAnimalAttractionGoal(this));
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(CHESTS, 0);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 14.0d)
                .add(Attributes.MOVEMENT_SPEED, 0.2d);
    }

    @Override
    protected float getBlockSpeedFactor() {
        return 0.65f;
    }

    @Override
    public boolean isFood(ItemStack itemStack) {
        return itemStack.is(Items.MELON);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundRegistry.CAPYBARA_AMBIENT.value();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return SoundRegistry.CAPYBARA_HURT.value();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundRegistry.CAPYBARA_DEATH.value();
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState state) {
        this.playSound(SoundEvents.COW_STEP, 0.15f, 1.0f);
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        if (isInvulnerableTo(source) || source.getEntity() == getOwner()) {
            return false;
        } else {
            Entity entity = source.getEntity();
            this.setOrderedToSit(false);
            if (entity != null && !(entity instanceof Player) && !(entity instanceof AbstractArrow)) {
                amount = (amount + 1.0f) / 2.0f;
            }
            return super.hurt(source, amount);
        }
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        Item item = stack.getItem();

        if(!this.level().isClientSide) {
            if (this.isFood(stack) && this.getHealth() < this.getMaxHealth()) {
                if (!player.getAbilities().instabuild) {
                    stack.shrink(1);
                }
                this.heal(item.getFoodProperties(stack, player).nutrition());
                return InteractionResult.SUCCESS;
            }

            if (player.isCrouching() && !this.isBaby()) {
                if (stack.getItem() == Blocks.CHEST.asItem() && this.isTame()) {
                    if (inventory == null || inventory.getContainerSize() < 27) {
                        inventory = new SimpleContainer(27);
                        entityData.set(CHESTS, 1);
                        if (!player.getAbilities().instabuild) {
                            stack.shrink(1);
                        }
                        return InteractionResult.SUCCESS;
                    } else if (inventory.getContainerSize() < 54) {
                        SimpleContainer inv = new SimpleContainer(54);
                        for (int i = 0; i < 27; i++) {
                            inv.setItem(i, inventory.getItem(i));
                        }
                        inventory = inv;
                        entityData.set(CHESTS, 2);
                        if (!player.getAbilities().instabuild) {
                            stack.shrink(1);
                        }
                        return InteractionResult.SUCCESS;
                    }
                } else if (stack.getItem() == Items.STICK && !this.isBaby()) {
                    this.setOrderedToSit(!this.isOrderedToSit());
                    return InteractionResult.SUCCESS;
                }
            } else if (stack.is(UAMItemTagsProvider.CAPYBARA_FOOD) && !isTame()) {
                if (this.random.nextInt(3) == 0) {
                    this.tame(player);
                    this.navigation.stop();
                    this.setTarget(null);
                    this.level().broadcastEntityEvent(this, (byte) 7);
                }
                if (!player.getAbilities().instabuild) {
                    stack.shrink(1);
                } else {
                    this.level().broadcastEntityEvent(this, (byte) 6);
                }
                return InteractionResult.SUCCESS;
            } else if (!this.isBaby() && this.isTame()) {
                player.openMenu(this);
                return InteractionResult.SUCCESS;
            }
        }
        return super.mobInteract(player, hand);
    }

    @Override
    protected float getSoundVolume() {
        return 0.4f;
    }

    @Override
    public @Nullable AgeableMob getBreedOffspring(ServerLevel serverLevel, AgeableMob ageableMob) {
        return EntityRegistry.CAPYBARA.get().create(serverLevel);
    }

    @Override
    protected EntityDimensions getDefaultDimensions(Pose pose) {
        return isBaby() ? BABY_DIMENSIONS : super.getDefaultDimensions(pose);
    }

    @Override
    public void tick() {
        super.tick();
        floatStrider();
        checkInsideBlocks();

        if(getPassengers().isEmpty()) {
            for(Entity e : level().getEntities(this, getBoundingBox().inflate(0.5))) {
                if(e instanceof Mob && e.getBbWidth() <= 0.75f && e.getBbHeight() <= 0.75 && !this.isBaby() && e.getClassification(false) != MobCategory.WATER_CREATURE && !isInWater()) {
                    e.startRiding(this);
                }
            }
        } else if(isInWater()) {
            ejectPassengers();
        }
    }

    @Override
    protected PathNavigation createNavigation(Level level) {
        return new WaterPathNavigator(this, level);
    }

    private void floatStrider() {
        if(this.isUnderWater()) {
            CollisionContext shapeContext = CollisionContext.of(this);
            if(shapeContext.isAbove(LiquidBlock.STABLE_SHAPE, this.blockPosition(), true) && !this.level().getFluidState(this.blockPosition().above()).is(FluidTags.WATER)) {
                this.setOnGround(true);
            } else {
                this.setDeltaMovement(this.getDeltaMovement().scale(0.5d).add(0.0d, 0.05d, 0.0d));
            }
        }
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putInt("Chests", getChestCount());
        if (this.getChestCount() > 0) {
            ListTag listtag = new ListTag();

            for(int i = 0; i < this.inventory.getContainerSize(); ++i) {
                ItemStack itemstack = this.inventory.getItem(i);
                if (!itemstack.isEmpty()) {
                    CompoundTag compoundtag = new CompoundTag();
                    compoundtag.putByte("Slot", (byte)(i));
                    listtag.add(itemstack.save(this.registryAccess(), compoundtag));
                }
            }

            compound.put("Items", listtag);
        }
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        setChests(compound.getInt("Chests"));
        this.createInventory();
        if (this.getChestCount() > 0) {
            ListTag listtag = compound.getList("Items", 10);

            for(int i = 0; i < listtag.size(); ++i) {
                CompoundTag compoundtag = listtag.getCompound(i);
                int j = compoundtag.getByte("Slot") & 255;
                if (j < this.inventory.getContainerSize()) {
                    this.inventory.setItem(j, ItemStack.parse(this.registryAccess(), compoundtag).orElse(ItemStack.EMPTY));
                }
            }
        }
    }

    @Override
    public AbstractContainerMenu createMenu(int i, Inventory inv, Player player) {
        if (inventory == null) {
            return null;
        }

        return inventory.getContainerSize() < 54 ? ChestMenu.threeRows(i, inv, inventory) : ChestMenu.sixRows(i, inv, inventory);
    }

    public int getChestCount() {
        return entityData.get(CHESTS);
    }

    public void setChests(int chests) {
        entityData.set(CHESTS, chests);
    }

    protected void createInventory() {
        SimpleContainer simplecontainer = this.inventory;
        this.inventory = new SimpleContainer(getChestCount() == 2 ? 54 : 27);
        if (simplecontainer != null) {
            int i = Math.min(simplecontainer.getContainerSize(), this.inventory.getContainerSize());

            for(int j = 0; j < i; ++j) {
                ItemStack itemstack = simplecontainer.getItem(j);
                if (!itemstack.isEmpty()) {
                    this.inventory.setItem(j, itemstack.copy());
                }
            }
        }
    }

    @Override
    protected void dropEquipment() {
        super.dropEquipment();
        if(getChestCount() > 0) {
            if(!this.level().isClientSide()) {
                for(int c = 0; c < getChestCount(); c++) {
                    this.spawnAtLocation(Blocks.CHEST);
                }

                if(this.inventory != null) {
                    for(int i = 0; i < this.inventory.getContainerSize(); i++) {
                        ItemStack stack = this.inventory.getItem(i);
                        if(!stack.isEmpty()) {
                            this.spawnAtLocation(stack);
                        }
                    }
                }
            }
        }
    }

    static class WaterPathNavigator extends GroundPathNavigation {
        public WaterPathNavigator(Mob mob, Level level) {
            super(mob, level);
        }

        @Override
        protected PathFinder createPathFinder(int maxVisitedNodes) {
            this.nodeEvaluator = new WalkNodeEvaluator();
            return new PathFinder(this.nodeEvaluator, maxVisitedNodes);
        }

        @Override
        protected boolean hasValidPathType(PathType pathType) {
            return pathType == PathType.WATER || super.hasValidPathType(pathType);
        }

        @Override
        public boolean isStableDestination(BlockPos pos) {
            return this.level.getBlockState(pos).is(Blocks.WATER) || super.isStableDestination(pos);
        }
    }
}

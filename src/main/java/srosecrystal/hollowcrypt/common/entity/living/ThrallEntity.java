package srosecrystal.hollowcrypt.common.entity.living;


import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityGroup;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.RevengeGoal;
import net.minecraft.entity.ai.goal.ZombieAttackGoal;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.World;
import srosecrystal.hollowcrypt.common.entity.goals.FollowOwnerGoal;

import java.util.Optional;
import java.util.UUID;

public class ThrallEntity extends ZombieEntity {


    protected static final TrackedData<Optional<UUID>> OWNER_UUID;


    public ThrallEntity(EntityType<? extends ZombieEntity> type, World world) {
        super(type, world);


    }



    @Override
    protected void initAttributes() {
        super.initAttributes();
        this.getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED).setBaseValue(0.35D);
        this.getAttributeInstance(EntityAttributes.GENERIC_ATTACK_DAMAGE).setBaseValue(3.5D);
        this.getAttributeInstance(EntityAttributes.GENERIC_ARMOR).setBaseValue(2.8D);
    }

    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(OWNER_UUID, Optional.empty());
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(6, new FollowOwnerGoal(this, this.getOwner(), this.world, 1.0, this.getNavigation(), 90.0F, 3.0F, true));
        this.initCustomGoals();
    }

    @Override
    protected void initCustomGoals() {
        this.goalSelector.add(1, new ZombieAttackGoal(this, 2.0D, true));
        this.targetSelector.add(2, (new RevengeGoal(this, new Class[0])).setGroupRevenge());
    }


    private void setOwnerUuid(UUID uuid) {
        this.dataTracker.set(OWNER_UUID, Optional.ofNullable(uuid));
    }

    public Optional<UUID> getOwnerUUID() {
        return this.dataTracker.get(OWNER_UUID);
    }

    public void setOwner(Entity player) {
        this.setOwnerUuid(player.getUuid());
    }



    public void writeCustomDataToTag(CompoundTag tag) {
        super.writeCustomDataToTag(tag);
        tag.putUuid("OwnerUUID", getOwnerUUID().get());
    }


    public void readCustomDataFromTag(CompoundTag tag) {
        super.readCustomDataFromTag(tag);
        UUID id;
        if (tag.contains("OwnerUUID")) {
            id = tag.getUuid("OwnerUUID");
        } else {
            id = tag.getUuid("OwnerUUID");
        }
        if (id != null) {
            this.setOwnerUuid(tag.getUuid("OwnerUUID"));
        }
    }



    @Override
    public void setAttacker(LivingEntity attacker) {
        if(attacker == getOwner()) {
        }
        else {
            super.setAttacker(attacker);
        }
    }


    @Override
    public void tickMovement() {
        if (this.isAlive()) {
            if (getOwner() != null) {
                if (getOwner().getAttacker() != null) {
                    this.setTarget(getOwner().getAttacker());
                } else if (getOwner().getAttacking() != null) {
                    this.setTarget(getOwner().getAttacking());
                }
            }
            else {


            }
        }
        super.tickMovement();
    }


    public LivingEntity getOwner() {
        try {
            Optional<UUID> uUID = this.getOwnerUUID();
            return uUID.map(value -> this.world.getPlayerByUuid(value)).orElse(null);
        } catch (IllegalArgumentException var2) {
            return null;
        }
    }

    static {
        OWNER_UUID = DataTracker.registerData(ThrallEntity.class, TrackedDataHandlerRegistry.OPTIONAL_UUID);
    }
    @Override
    public EntityGroup getGroup() {
        return EntityGroup.UNDEAD;
    }



}

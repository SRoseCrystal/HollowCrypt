package srosecrystal.hollowcrypt.common.entity.living;

import ladysnake.requiem.common.entity.effect.RequiemStatusEffects;
import moriyashiine.bewitchment.client.network.packet.SpawnSmokeParticlesPacket;
import moriyashiine.bewitchment.common.entity.living.GhostEntity;
import moriyashiine.bewitchment.common.entity.living.util.BWHostileEntity;
import moriyashiine.bewitchment.common.registry.BWStatusEffects;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.control.MoveControl;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class NGhostEntity extends GhostEntity{

    public NGhostEntity(EntityType<? extends HostileEntity>entityType, World world) {
        super(entityType, world);
        this.moveControl = new NGhostEntity.GhostMoveControl(this);
    }



    @Override
    protected boolean hasShiny() {
        return false;
    }

    @Override
    public int getVariants() {
        return 0;
    }

    public static DefaultAttributeContainer.Builder createGhostAttributes() {
        return MobEntity.createMobAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 25).add(EntityAttributes.GENERIC_FLYING_SPEED, 0.20).add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 1).add(EntityAttributes.GENERIC_FOLLOW_RANGE, 8);
    }

    @Override
    public void tick() {
        noClip = true;
        super.tick();
        noClip = false;
        setNoGravity(true);
        if (age % 20 == 0 && getTarget() != null) {
            getTarget().addStatusEffect(new StatusEffectInstance(RequiemStatusEffects.ATTRITION, 10, 1));
        }
        if (!world.isClient && !hasCustomName() && world.isDay() && !world.isRaining() && world.isSkyVisibleAllowingSea(getBlockPos())) {
            PlayerLookup.tracking(this).forEach(playerEntity -> SpawnSmokeParticlesPacket.send(playerEntity, this));
            remove();
        }
    }




    private class GhostMoveControl extends MoveControl {
        public GhostMoveControl(MobEntity entity) {
            super(entity);
        }
    }

}

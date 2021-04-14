package srosecrystal.hollowcrypt.common.rituals;

import moriyashiine.bewitchment.api.BewitchmentAPI;
import moriyashiine.bewitchment.api.registry.RitualFunction;
import moriyashiine.bewitchment.common.entity.interfaces.FamiliarAccessor;
import moriyashiine.bewitchment.common.item.TaglockItem;
import moriyashiine.bewitchment.common.world.BWUniversalWorldState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.particle.ParticleType;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.Pair;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import srosecrystal.hollowcrypt.common.entity.living.ThrallEntity;
import srosecrystal.hollowcrypt.common.registry.HCMobs;

import java.util.function.Predicate;

public class ThrallRitualFunction extends RitualFunction {
    public ThrallRitualFunction(ParticleType<?> startParticle, Predicate<LivingEntity> sacrifice) {
        super(startParticle, sacrifice);
    }

    @Override
    public String getInvalidMessage() {
        return "ritual.precondition.found_entity";
    }

    @Override
    public boolean isValid(ServerWorld world, BlockPos pos, Inventory inventory) {
        ItemStack taglock = null;
        for (int i = 0; i < inventory.size(); i++) {
            ItemStack stack = inventory.getStack(i);
            if (inventory.getStack(i).getItem() instanceof TaglockItem && TaglockItem.hasTaglock(stack)) {
                taglock = stack;
                break;
            }
        }
        return taglock != null && BewitchmentAPI.getTaglockOwner(world, taglock) != null;
    }

    @Override
    public void start(ServerWorld world, BlockPos glyphPos, BlockPos effectivePos, Inventory inventory, boolean catFamiliar) {
        boolean succeeded = false;
        ItemStack taglock = null;
        for (int i = 0; i < inventory.size(); i++) {
            ItemStack stack = inventory.getStack(i);
            if (inventory.getStack(i).getItem() instanceof TaglockItem) {
                taglock = stack;
                break;
            }
        }
        if (taglock != null) {

            ThrallEntity thrallEntity = HCMobs.THRALL_ENTITY.create(world);
            if (thrallEntity != null) {
                LivingEntity livingEntity = BewitchmentAPI.getTaglockOwner(world, taglock);
                if (livingEntity != null) {
                    CompoundTag entityTag = new CompoundTag();
                    thrallEntity.saveSelfToTag(entityTag);
                    if (entityTag.contains("Owner") && livingEntity.getUuid().equals(entityTag.getUuid("Owner"))) {
                        thrallEntity.setOwner(livingEntity);
                        thrallEntity.setPos(livingEntity.getX(), livingEntity.getY(), livingEntity.getZ()+3);
                        world.spawnEntity(thrallEntity);
                        succeeded = true;
                    }
                }
            }
        }
        if (!succeeded) {
            ItemScatterer.spawn(world, glyphPos, inventory);
        }
        super.start(world, glyphPos, effectivePos, inventory, catFamiliar);
    }
}

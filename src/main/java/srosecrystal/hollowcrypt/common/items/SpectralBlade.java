package srosecrystal.hollowcrypt.common.items;

import ladysnake.requiem.api.v1.RequiemPlayer;
import ladysnake.requiem.common.entity.RequiemEntities;
import ladysnake.requiem.common.entity.effect.RequiemStatusEffects;
import moriyashiine.bewitchment.api.BewitchmentAPI;
import moriyashiine.bewitchment.common.registry.BWDamageSources;
import moriyashiine.bewitchment.common.registry.BWEntityTypes;
import net.minecraft.entity.EntityGroup;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.server.network.ServerPlayerEntity;

public class SpectralBlade extends SwordItem {




    public SpectralBlade(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (target.getGroup() == EntityGroup.UNDEAD) {
            if (BewitchmentAPI.usePlayerMagic((PlayerEntity)attacker, 1, false)) {
                target.addStatusEffect(new StatusEffectInstance(RequiemStatusEffects.ATTRITION, 10, 3));
                target.damage(BWDamageSources.MAGIC_COPY, 4F);
            }
        }
        return super.postHit(stack, target, attacker);
    }
}

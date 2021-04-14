package srosecrystal.hollowcrypt.common.trinkets;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import dev.emi.trinkets.api.SlotGroups;
import dev.emi.trinkets.api.Slots;
import dev.emi.trinkets.api.TrinketItem;
import moriyashiine.bewitchment.api.BewitchmentAPI;
import moriyashiine.bewitchment.common.entity.living.GhostEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;


import java.util.List;
import java.util.UUID;

public class GhostlyAmulet extends TrinketItem {
    public GhostlyAmulet() {
        super(new Settings().group(ItemGroup.TRANSPORTATION).maxCount(1).maxDamage(128));
    }

    @Environment(EnvType.CLIENT)
    public void appendTooltip(ItemStack stack, World world, List<Text> list, TooltipContext context) {
        list.add(new LiteralText("Ghosts are friends too").formatted(Formatting.GOLD));
    }

    @Override
    public boolean canWearInSlot(String group, String slot) {
        return group.equals(SlotGroups.CHEST) && slot.equals(Slots.NECKLACE);
    }

    @Override
    public Multimap<EntityAttribute, EntityAttributeModifier> getTrinketModifiers(String group, String slot, UUID uuid, ItemStack stack) {
        Multimap<EntityAttribute, EntityAttributeModifier> map = HashMultimap.create();
        map.put(EntityAttributes.GENERIC_MOVEMENT_SPEED, new EntityAttributeModifier(uuid, "Movement Speed", 0.02F, EntityAttributeModifier.Operation.ADDITION));
        return map;
    }

    @Override
    public void tick(PlayerEntity player, ItemStack stack) {
        if (!player.world.isClient) {
            player.world.getOtherEntities(null, new Box(player.getX() - 14, player.getY() - 16, player.getZ() - 16, player.getX() + 16, player.getY() + 16, player.getZ() + 16)).forEach((entity) -> {
                if (entity instanceof GhostEntity ) {
                    if((((GhostEntity) entity).canTarget(player) || ((GhostEntity) entity).canSee(player))) {
                        if (BewitchmentAPI.usePlayerMagic(player, 1, false)) {
                        ((GhostEntity) entity).setTarget(null);
                        }
                    }

                }
            });
            player.world.getOtherEntities(null, new Box(player.getX() - 8.1, player.getY() - 8.1, player.getZ() - 8.1, player.getX() + 8.1, player.getY() + 8.1, player.getZ() + 8.1)).forEach((entity) -> {
                if (entity instanceof GhostEntity ) {
                    if((((GhostEntity) entity).canTarget(player) || ((GhostEntity) entity).canSee(player))) {
                        if (BewitchmentAPI.usePlayerMagic(player, 1, true)) {
                        ((GhostEntity) entity).pushAwayFrom(player);
                        }
                    }

                }
            });
        }
    }
}




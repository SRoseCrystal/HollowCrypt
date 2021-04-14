package srosecrystal.hollowcrypt.common.registry;

import dev.emi.trinkets.api.SlotGroups;
import dev.emi.trinkets.api.Slots;
import dev.emi.trinkets.api.TrinketSlots;
import moriyashiine.bewitchment.common.Bewitchment;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import srosecrystal.hollowcrypt.common.items.GhostlyEssence;
import srosecrystal.hollowcrypt.common.trinkets.GhostlyAmulet;

public class HCObjects {
    public static Item GHOSTLY_ESSENCE = registerItem("ghostly_essence", new GhostlyEssence(new Item.Settings().group(Bewitchment.BEWITCHMENT_GROUP)));
    public static Item GHOSTLY_AMULET = registerItem("ghostly_amulet", new GhostlyAmulet());
    public static Item SKULL_CINCTURE = registerItem("skull_cincture", new GhostlyAmulet());
    public static Item THRALL_SPAWN_EGG = registerItem("thrall_spawn_egg", new SpawnEggItem(HCMobs.THRALL_ENTITY, 0xcacaca, 0x969696, new Item.Settings().group(Bewitchment.BEWITCHMENT_GROUP)));

    public static void init() {

        TrinketSlots.addSlot(SlotGroups.CHEST, Slots.NECKLACE, new Identifier("trinkets", "textures/item/empty_trinket_slot_necklace.png"));
    }

    private static <T extends Item> T registerItem(String name, T item){
        return Registry.register(Registry.ITEM, new Identifier("hollowcrypt", name), item);
    }
}

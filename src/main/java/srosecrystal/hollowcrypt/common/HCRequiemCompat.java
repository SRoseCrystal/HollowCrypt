package srosecrystal.hollowcrypt.common;

import moriyashiine.bewitchment.common.Bewitchment;
import moriyashiine.bewitchment.common.registry.BWEntityTypes;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import srosecrystal.hollowcrypt.common.entity.living.NGhostEntity;
import srosecrystal.hollowcrypt.common.items.SpectralBlade;
import srosecrystal.hollowcrypt.common.items.SpectralBladeMaterial;
import srosecrystal.hollowcrypt.common.trinkets.GhostlyAmulet;


public class HCRequiemCompat {


    public static final Item GHOST_SPAWN_EGG = registerItem("fghost_spawn_egg", new SpawnEggItem(HCRequiemCompat.NGHOST_ENTITY, 0xcacaca, 0x969696, new Item.Settings().group(Bewitchment.BEWITCHMENT_GROUP)));


    public static Item SPECTRAL_BLADE = registerItem("spectral_blade", new SpectralBlade(SpectralBladeMaterial.INSTANCE, 5, -1.8F, new Item.Settings().group(Bewitchment.BEWITCHMENT_GROUP)));

    public static final EntityType<NGhostEntity> NGHOST_ENTITY = Registry.register(
            Registry.ENTITY_TYPE, new Identifier("hollowcrypt", "nether_ghost"),
            FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, NGhostEntity::new).dimensions(BWEntityTypes.GHOST.getDimensions()).fireImmune().build());




    private static <T extends Item> T registerItem(String name, T item){
        return Registry.register(Registry.ITEM, new Identifier("hollowcrypt", name), item);
    }
    public static void init() {

        FabricDefaultAttributeRegistry.register(NGHOST_ENTITY, NGhostEntity.createGhostAttributes());

    }

}

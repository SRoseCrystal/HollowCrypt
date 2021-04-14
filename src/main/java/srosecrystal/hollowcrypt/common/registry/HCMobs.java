package srosecrystal.hollowcrypt.common.registry;

import moriyashiine.bewitchment.common.registry.BWEntityTypes;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import srosecrystal.hollowcrypt.common.entity.living.NGhostEntity;
import srosecrystal.hollowcrypt.common.entity.living.ThrallEntity;

public class HCMobs {


    public static final EntityType<ThrallEntity> THRALL_ENTITY = Registry.register(
            Registry.ENTITY_TYPE, new Identifier("hollowcrypt", "thrall"),
            FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, ThrallEntity::new).dimensions(EntityType.ZOMBIE.getDimensions()).build());
    public static void init() {

        FabricDefaultAttributeRegistry.register(THRALL_ENTITY, ThrallEntity.createZombieAttributes());

    }
}

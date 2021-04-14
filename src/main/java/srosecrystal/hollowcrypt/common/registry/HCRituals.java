
package srosecrystal.hollowcrypt.common.registry;

import moriyashiine.bewitchment.api.registry.RitualFunction;
import moriyashiine.bewitchment.common.Bewitchment;
import moriyashiine.bewitchment.common.entity.living.GhostEntity;
import moriyashiine.bewitchment.common.registry.BWRegistries;
import moriyashiine.bewitchment.common.ritualfunction.*;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import srosecrystal.hollowcrypt.common.rituals.ThrallRitualFunction;

import java.util.LinkedHashMap;
import java.util.Map;

public class HCRituals {
    private static final Map<RitualFunction, Identifier> HCRITUALS = new LinkedHashMap<>();

    public static final RitualFunction THRALL = create("thrall", new ThrallRitualFunction(ParticleTypes.ASH, livingEntity -> livingEntity instanceof GhostEntity));


    private static <T extends RitualFunction> T create(String name, T ritualFunction) {
        HCRITUALS.put(ritualFunction, new Identifier("hollowcrypt", name));
        return ritualFunction;
    }

    public static void init() {
        HCRITUALS.keySet().forEach(contract -> Registry.register(BWRegistries.RITUAL_FUNCTIONS, HCRITUALS.get(contract), contract));
    }
}

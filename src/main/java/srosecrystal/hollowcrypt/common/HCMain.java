package srosecrystal.hollowcrypt.common;

import net.fabricmc.api.ModInitializer;
import srosecrystal.hollowcrypt.common.registry.HCMobs;
import srosecrystal.hollowcrypt.common.registry.HCObjects;
import srosecrystal.hollowcrypt.common.registry.HCRituals;

public class HCMain implements ModInitializer{
	public static boolean isRequiemLoaded;
	@Override
	public void onInitialize() {
		HCObjects.init();
		HCRituals.init();
		HCMobs.init();
		if (isRequiemLoaded) {
			HCRequiemCompat.init();
		}
	}


}

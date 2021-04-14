package srosecrystal.hollowcrypt.common.items;

import moriyashiine.bewitchment.common.registry.BWObjects;
import net.minecraft.item.*;
import net.minecraft.recipe.Ingredient;

public class SpectralBladeMaterial implements ToolMaterial {
    public static final SpectralBladeMaterial INSTANCE = new SpectralBladeMaterial();

    @Override
    public int getDurability() {
        return 1797;
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return 0.0F;
    }

    @Override
    public float getAttackDamage() {
        return 0.0F;
    }

    @Override
    public int getMiningLevel() {
        return 0;
    }

    @Override
    public int getEnchantability() {
        return 24;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(BWObjects.ECTOPLASM);
    }
}

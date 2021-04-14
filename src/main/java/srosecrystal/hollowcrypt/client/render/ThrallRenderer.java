
package srosecrystal.hollowcrypt.client.render;

import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.ZombieEntityModel;
import net.minecraft.util.Identifier;
import srosecrystal.hollowcrypt.common.entity.living.ThrallEntity;

public class ThrallRenderer extends MobEntityRenderer<ThrallEntity, ZombieEntityModel<ThrallEntity>> {


    public ThrallRenderer(EntityRenderDispatcher dispatcher) {
        super(dispatcher, new ZombieEntityModel<>(1, false), 1);
    }

    @Override
    public Identifier getTexture(ThrallEntity entity) {
        return new Identifier("textures/entity/zombie/zombie.png");
    }


}

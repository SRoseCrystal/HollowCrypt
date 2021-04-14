
package srosecrystal.hollowcrypt.client.render;

import moriyashiine.bewitchment.client.model.entity.living.GhostEntityModel;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.ZombieEntityModel;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import srosecrystal.hollowcrypt.common.entity.living.NGhostEntity;
import srosecrystal.hollowcrypt.common.entity.living.ThrallEntity;

public class NGhostRenderer extends MobEntityRenderer<NGhostEntity, GhostEntityModel<NGhostEntity>> {


    public NGhostRenderer(EntityRenderDispatcher dispatcher) {
        super(dispatcher, new GhostEntityModel<>(), 0);
    }


    @Override
    public Identifier getTexture(NGhostEntity entity) {
        return new Identifier("hollowcrypt", "textures/entity/living/nghost.png");
    }
    @Override
    protected int getBlockLight(NGhostEntity entity, BlockPos blockPos) {
        return 15;
    }



}

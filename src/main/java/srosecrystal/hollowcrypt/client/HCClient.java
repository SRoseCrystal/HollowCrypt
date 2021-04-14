package srosecrystal.hollowcrypt.client;


import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import srosecrystal.hollowcrypt.client.render.NGhostRenderer;
import srosecrystal.hollowcrypt.client.render.ThrallRenderer;
import srosecrystal.hollowcrypt.common.HCRequiemCompat;
import srosecrystal.hollowcrypt.common.registry.HCMobs;

public class HCClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.INSTANCE.register(HCMobs.THRALL_ENTITY,
                (dispatcher, context) ->
                        new ThrallRenderer(dispatcher));

        EntityRendererRegistry.INSTANCE.register(HCRequiemCompat.NGHOST_ENTITY,
                (dispatcher, context) ->
                        new NGhostRenderer(dispatcher));

    }
}

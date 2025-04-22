package teamdraco.unnamedanimalmod;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import teamdraco.unnamedanimalmod.entity.CapybaraModel;
import teamdraco.unnamedanimalmod.entity.CapybaraRenderer;
import teamdraco.unnamedanimalmod.registry.EntityRegistry;

@EventBusSubscriber(modid = UAM.MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class UAMClient {
    public static final ModelLayerLocation CAPYBARA = new ModelLayerLocation(UAM.reloc("capybara"), "main");

    @SubscribeEvent
    public static void registerEntityLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(CAPYBARA, CapybaraModel::getLayerDefinition);
    }

    @SubscribeEvent
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(EntityRegistry.CAPYBARA.get(), CapybaraRenderer::new);
    }
}

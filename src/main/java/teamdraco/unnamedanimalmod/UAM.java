package teamdraco.unnamedanimalmod;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import teamdraco.unnamedanimalmod.entity.Capybara;
import teamdraco.unnamedanimalmod.registry.EntityRegistry;
import teamdraco.unnamedanimalmod.registry.ItemRegistry;
import teamdraco.unnamedanimalmod.registry.SoundRegistry;

@Mod(UAM.MODID)
public class UAM {
    public static final String MODID = "unnamedanimalmod";

    public UAM(IEventBus bus) {
        EntityRegistry.ENTITY_TYPES.register(bus);
        SoundRegistry.SOUND_EVENTS.register(bus);
        ItemRegistry.ITEMS.register(bus);

        bus.register(this);
    }

    public static ResourceLocation reloc(String id) {
        return ResourceLocation.fromNamespaceAndPath(MODID, id);
    }

    @SubscribeEvent
    public void creativeModeTab(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.SPAWN_EGGS) {
            event.insertAfter(new ItemStack(Items.CAMEL_SPAWN_EGG), ItemRegistry.CAPYBARA_SPAWN_EGG.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }
    }

    @SubscribeEvent
    public void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(EntityRegistry.CAPYBARA.get(), Capybara.createAttributes().build());
    }
}

package teamdraco.unnamedanimalmod.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import teamdraco.unnamedanimalmod.UAM;

public class ItemRegistry {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(UAM.MODID);

    public static final DeferredItem<Item> CAPYBARA_SPAWN_EGG = ITEMS.register(
            "capybara_spawn_egg",
            () -> new SpawnEggItem(EntityRegistry.CAPYBARA.get(), new Item.Properties().setId(ResourceKey.create(Registries.ITEM, UAM.reloc("capybara_spawn_egg"))))
    );
}

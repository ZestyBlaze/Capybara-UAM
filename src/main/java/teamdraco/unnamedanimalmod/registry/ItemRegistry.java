package teamdraco.unnamedanimalmod.registry;

import net.minecraft.world.item.Item;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import teamdraco.unnamedanimalmod.UAM;

public class ItemRegistry {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(UAM.MODID);

    public static final DeferredItem<Item> CAPYBARA_SPAWN_EGG = ITEMS.register(
            "capybara_spawn_egg",
            () -> new DeferredSpawnEggItem(EntityRegistry.CAPYBARA, 0x9e5d39, 0x412f24, new Item.Properties())
    );
}

package teamdraco.unnamedanimalmod.data.providers;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import teamdraco.unnamedanimalmod.UAM;
import teamdraco.unnamedanimalmod.registry.ItemRegistry;

public class UAMItemModelProvider extends ItemModelProvider {
    public UAMItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, UAM.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        spawnEggItem(ItemRegistry.CAPYBARA_SPAWN_EGG.get());
    }
}

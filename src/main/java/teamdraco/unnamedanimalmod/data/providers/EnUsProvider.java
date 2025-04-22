package teamdraco.unnamedanimalmod.data.providers;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;
import teamdraco.unnamedanimalmod.UAM;
import teamdraco.unnamedanimalmod.registry.EntityRegistry;
import teamdraco.unnamedanimalmod.registry.ItemRegistry;

public class EnUsProvider extends LanguageProvider {
    public EnUsProvider(PackOutput output) {
        super(output, UAM.MODID, "en_us");
    }

    @Override
    protected void addTranslations() {
        add(EntityRegistry.CAPYBARA.get(), "Capybara");
        add(ItemRegistry.CAPYBARA_SPAWN_EGG.get(), "Capybara Spawn Egg");
    }
}

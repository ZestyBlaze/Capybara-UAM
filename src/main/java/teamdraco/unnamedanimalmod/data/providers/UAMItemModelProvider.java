package teamdraco.unnamedanimalmod.data.providers;

import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ItemModelOutput;
import net.minecraft.client.data.models.model.ModelInstance;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.resources.ResourceLocation;
import teamdraco.unnamedanimalmod.registry.ItemRegistry;

import java.util.function.BiConsumer;

public class UAMItemModelProvider extends ItemModelGenerators {
    public UAMItemModelProvider(ItemModelOutput itemModelOutput, BiConsumer<ResourceLocation, ModelInstance> modelOutput) {
        super(itemModelOutput, modelOutput);
    }

    @Override
    public void run() {
        generateFlatItem(ItemRegistry.CAPYBARA_SPAWN_EGG.asItem(), ModelTemplates.FLAT_ITEM);
    }
}

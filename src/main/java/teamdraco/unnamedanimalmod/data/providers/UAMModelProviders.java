package teamdraco.unnamedanimalmod.data.providers;

import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.data.PackOutput;
import teamdraco.unnamedanimalmod.UAM;

public class UAMModelProviders extends ModelProvider {
    public UAMModelProviders(PackOutput output) {
        super(output, UAM.MODID);
    }

    @Override
    protected void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {
        ItemModelGenerators uamItems = new UAMItemModelProvider(itemModels.itemModelOutput, itemModels.modelOutput);
        uamItems.run();
    }
}

package teamdraco.unnamedanimalmod.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import teamdraco.unnamedanimalmod.UAM;
import teamdraco.unnamedanimalmod.data.providers.*;

import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = UAM.MODID, value = Dist.CLIENT)
public class UAMDatagen {

    @SubscribeEvent
    public static void onDataLoad(GatherDataEvent.Client event) {
        DataGenerator gen = event.getGenerator();
        PackOutput output = gen.getPackOutput();
        CompletableFuture<HolderLookup.Provider> provider = event.getLookupProvider();

        event.addProvider(new EnUsProvider(output));
        event.addProvider(new UAMModelProviders(output));

        BlockTagsProvider blockTagsProvider = new UAMBlockTagsProvider(output, provider);
        event.addProvider(blockTagsProvider);
        event.addProvider(new UAMItemTagsProvider(output, provider, blockTagsProvider.contentsGetter()));
        event.addProvider(new UAMRegistrySetBuilderProvider(output, provider));
    }
}

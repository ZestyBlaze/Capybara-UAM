package teamdraco.unnamedanimalmod.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import teamdraco.unnamedanimalmod.UAM;
import teamdraco.unnamedanimalmod.data.providers.*;

import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = UAM.MODID, bus = EventBusSubscriber.Bus.MOD)
public class UAMDatagen {
    @SubscribeEvent
    public static void onDataLoad(GatherDataEvent event) {
        DataGenerator gen = event.getGenerator();
        PackOutput output = gen.getPackOutput();
        CompletableFuture<HolderLookup.Provider> provider = event.getLookupProvider();
        ExistingFileHelper fileHelper = event.getExistingFileHelper();

        gen.addProvider(event.includeClient(), new EnUsProvider(output));
        gen.addProvider(event.includeClient(), new UAMItemModelProvider(output, fileHelper));

        BlockTagsProvider blockTagsProvider = new UAMBlockTagsProvider(output, provider, fileHelper);
        gen.addProvider(event.includeServer(), blockTagsProvider);
        gen.addProvider(event.includeServer(), new UAMItemTagsProvider(output, provider, blockTagsProvider.contentsGetter(), fileHelper));
        gen.addProvider(event.includeServer(), new UAMRegistrySetBuilderProvider(output, provider));
    }
}

package teamdraco.unnamedanimalmod.data.providers;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderSet;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.random.WeightedList;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import teamdraco.unnamedanimalmod.UAM;
import teamdraco.unnamedanimalmod.registry.EntityRegistry;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class UAMRegistrySetBuilderProvider extends DatapackBuiltinEntriesProvider {
    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(NeoForgeRegistries.Keys.BIOME_MODIFIERS, UAMRegistrySetBuilderProvider::bootstrapBiomeModifications);

    public static final ResourceKey<BiomeModifier> ADD_CAPYBARA_SPAWNS = ResourceKey.create(
            NeoForgeRegistries.Keys.BIOME_MODIFIERS, UAM.reloc("add_capybara_spawns")
    );

    public UAMRegistrySetBuilderProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Set.of(UAM.MODID));
    }

    private static void bootstrapBiomeModifications(BootstrapContext<BiomeModifier> bootstrap) {
        HolderGetter<Biome> biomes = bootstrap.lookup(Registries.BIOME);

        bootstrap.register(ADD_CAPYBARA_SPAWNS,
                new BiomeModifiers.AddSpawnsBiomeModifier(
                        HolderSet.direct(biomes.getOrThrow(Biomes.MANGROVE_SWAMP)),
                        WeightedList.<MobSpawnSettings.SpawnerData>builder().add(new MobSpawnSettings.SpawnerData(EntityRegistry.CAPYBARA.get(), 1, 4), 30).build()
                        )
                );
    }
}

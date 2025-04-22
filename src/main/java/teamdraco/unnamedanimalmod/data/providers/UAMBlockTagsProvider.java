package teamdraco.unnamedanimalmod.data.providers;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;
import teamdraco.unnamedanimalmod.UAM;

import java.util.concurrent.CompletableFuture;

public class UAMBlockTagsProvider extends BlockTagsProvider {
    public static final TagKey<Block> CAPYBARAS_SPAWN_ON = BlockTags.create(UAM.reloc("capybaras_spawn_on"));

    public UAMBlockTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, UAM.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(CAPYBARAS_SPAWN_ON).add(Blocks.GRASS_BLOCK, Blocks.MUD, Blocks.MANGROVE_ROOTS, Blocks.MUDDY_MANGROVE_ROOTS);
    }
}

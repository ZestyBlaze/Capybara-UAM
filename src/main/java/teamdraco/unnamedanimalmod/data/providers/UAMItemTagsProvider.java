package teamdraco.unnamedanimalmod.data.providers;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.Tags;
import teamdraco.unnamedanimalmod.UAM;

import java.util.concurrent.CompletableFuture;

public class UAMItemTagsProvider extends ItemTagsProvider {
    public static final TagKey<Item> CAPYBARA_FOOD = ItemTags.create(UAM.reloc("capybara_food"));

    public UAMItemTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagLookup<Block>> blockTags) {
        super(output, lookupProvider, blockTags, UAM.MODID);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(CAPYBARA_FOOD).add(Items.MELON, Items.APPLE, Items.SUGAR_CANE, Items.MELON_SLICE);
        tag(Tags.Items.ANIMAL_FOODS).addTag(CAPYBARA_FOOD);
    }
}

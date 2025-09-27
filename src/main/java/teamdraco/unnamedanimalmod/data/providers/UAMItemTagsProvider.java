package teamdraco.unnamedanimalmod.data.providers;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagBuilder;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.Tags;
import org.jetbrains.annotations.NotNull;
import teamdraco.unnamedanimalmod.UAM;

import java.util.concurrent.CompletableFuture;

public class UAMItemTagsProvider extends TagsProvider<Item> {

    public static final TagKey<Item> CAPYBARA_FOOD = ItemTags.create(UAM.reloc("capybara_food"));

    public UAMItemTagsProvider(
            PackOutput output,
            CompletableFuture<HolderLookup.Provider> registries,
            CompletableFuture<TagLookup<Block>> blockTags
    ) {
        super(
                output,
                Registries.ITEM,
                registries,
                //blockTags, //TODO why is this passed
                UAM.MODID
        );
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {

        TagBuilder builder = this.getOrCreateRawBuilder(CAPYBARA_FOOD);
        builder.addElement(BuiltInRegistries.ITEM.getKey(Items.MELON));
        builder.addElement(BuiltInRegistries.ITEM.getKey(Items.APPLE));
        builder.addElement(BuiltInRegistries.ITEM.getKey(Items.SUGAR_CANE));
        builder.addElement(BuiltInRegistries.ITEM.getKey(Items.MELON_SLICE));

        TagBuilder builder2 = this.getOrCreateRawBuilder(Tags.Items.ANIMAL_FOODS);
        builder2.addTag(CAPYBARA_FOOD.location());

    }
}

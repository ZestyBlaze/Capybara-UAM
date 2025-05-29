package teamdraco.unnamedanimalmod.registry;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import teamdraco.unnamedanimalmod.UAM;
import teamdraco.unnamedanimalmod.entity.Capybara;

public class EntityRegistry {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, UAM.MODID);

    public static final DeferredHolder<EntityType<?>, EntityType<Capybara>> CAPYBARA =
            ENTITY_TYPES.register("capybara", () ->
                    EntityType.Builder.of(Capybara::new, MobCategory.CREATURE)
                            .sized(0.8f, 1.1f)
                            .passengerAttachments(1.0f)
                            .eyeHeight(0.9f)
                            .build(ResourceKey.create(Registries.ENTITY_TYPE, UAM.reloc("capybara")))
            );
}

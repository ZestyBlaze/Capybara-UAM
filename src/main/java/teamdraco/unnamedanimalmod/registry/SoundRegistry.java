package teamdraco.unnamedanimalmod.registry;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.registries.DeferredRegister;
import teamdraco.unnamedanimalmod.UAM;

public class SoundRegistry {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(BuiltInRegistries.SOUND_EVENT, UAM.MODID);

    public static final Holder<SoundEvent> CAPYBARA_AMBIENT = SOUND_EVENTS.register("capybara.ambient", SoundEvent::createVariableRangeEvent);
    public static final Holder<SoundEvent> CAPYBARA_HURT = SOUND_EVENTS.register("capybara.hurt", SoundEvent::createVariableRangeEvent);
    public static final Holder<SoundEvent> CAPYBARA_DEATH = SOUND_EVENTS.register("capybara.death", SoundEvent::createVariableRangeEvent);
}

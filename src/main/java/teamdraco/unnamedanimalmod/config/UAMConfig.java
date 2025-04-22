package teamdraco.unnamedanimalmod.config;

import net.neoforged.neoforge.common.ModConfigSpec;

public class UAMConfig {
    public static final ModConfigSpec COMMON_SPEC;

    public static ModConfigSpec.BooleanValue capybaraAttractionGoal;

    static {
        ModConfigSpec.Builder commonBuilder = new ModConfigSpec.Builder();
        setupCommonConfig(commonBuilder);
        COMMON_SPEC = commonBuilder.build();
    }

    private static void setupCommonConfig(ModConfigSpec.Builder builder) {
        capybaraAttractionGoal = builder.comment("If other animals will be attracted to the Capybara and sit on it's back")
                .define("capybaraAttractionGoal", true);
    }
}

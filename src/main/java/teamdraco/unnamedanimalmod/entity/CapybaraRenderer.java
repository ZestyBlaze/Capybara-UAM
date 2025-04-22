package teamdraco.unnamedanimalmod.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import teamdraco.unnamedanimalmod.UAM;
import teamdraco.unnamedanimalmod.UAMClient;

import java.util.Locale;

public class CapybaraRenderer extends MobRenderer<Capybara, CapybaraModel> {
    private static final ResourceLocation TEXTURE = UAM.reloc("textures/entity/capybara.png");
    private static final ResourceLocation MARIO = UAM.reloc("textures/entity/mario.png");

    public CapybaraRenderer(EntityRendererProvider.Context context) {
        super(context, new CapybaraModel(context.bakeLayer(UAMClient.CAPYBARA)), 0.5f);
        this.addLayer(new CapybaraChestLayer(this));
    }

    @Override
    public ResourceLocation getTextureLocation(Capybara entity) {
        if(entity.getName().getString().toLowerCase(Locale.ROOT).equals("mario")) {
            return MARIO;
        }
        return TEXTURE;
    }

    @Override
    protected void setupRotations(Capybara entity, PoseStack poseStack, float bob, float yBodyRot, float partialTick, float scale) {
        super.setupRotations(entity, poseStack, bob, yBodyRot, partialTick, scale);
        poseStack.scale(0.77f, 0.77f, 0.77f);
        if(entity.isInWater() && !entity.isBaby()) {
            poseStack.translate(0, -0.625, 0);
        }
    }
}

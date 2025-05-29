package teamdraco.unnamedanimalmod.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import teamdraco.unnamedanimalmod.UAM;
import teamdraco.unnamedanimalmod.UAMClient;

import java.util.Locale;

public class CapybaraRenderer extends MobRenderer<Capybara, CapybaraRenderState ,CapybaraModel> {
    private static final ResourceLocation TEXTURE = UAM.reloc("textures/entity/capybara.png");
    private static final ResourceLocation MARIO = UAM.reloc("textures/entity/mario.png");

    public CapybaraRenderer(EntityRendererProvider.Context context) {
        super(context, new CapybaraModel(context.bakeLayer(UAMClient.CAPYBARA)), 0.5f);
        this.addLayer(new CapybaraChestLayer(this));
    }

    @Override
    public ResourceLocation getTextureLocation(CapybaraRenderState state) {
        if(state.customName != null && state.customName.getString().toLowerCase(Locale.ROOT).equals("mario")) {
            return MARIO;
        }
        return TEXTURE;
    }

    @Override
    protected void setupRotations(CapybaraRenderState renderState, PoseStack poseStack, float bodyRot, float scale) {
        super.setupRotations(renderState, poseStack, bodyRot, scale);
        poseStack.scale(0.77f, 0.77f, 0.77f);
        if(renderState.isInWater && !renderState.isBaby) {
            poseStack.translate(0, -0.625, 0);
        }
    }

    @Override
    public CapybaraRenderState createRenderState() {
        return new CapybaraRenderState();
    }

    @Override
    public void extractRenderState(Capybara capybara, CapybaraRenderState state, float age) {
        super.extractRenderState(capybara, state, age);
        state.isSitting = capybara.isInSittingPose();
        state.chestCount = capybara.getChestCount();
    }
}

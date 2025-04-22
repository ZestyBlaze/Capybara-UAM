package teamdraco.unnamedanimalmod.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import teamdraco.unnamedanimalmod.UAM;

public class CapybaraChestLayer extends RenderLayer<Capybara, CapybaraModel> {
    private static final ResourceLocation SINGLE_CHEST = UAM.reloc("textures/entity/single_chest.png");
    private static final ResourceLocation DOUBLE_CHEST = UAM.reloc("textures/entity/double_chest.png");

    public CapybaraChestLayer(RenderLayerParent<Capybara, CapybaraModel> context) {
        super(context);
    }

    @Override
    public void render(PoseStack matrices, MultiBufferSource vertexConsumers, int light, Capybara entity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
        final int chestCount = entity.getChestCount();
        if(chestCount > 0) {
            CapybaraModel model = this.getParentModel();
            model.prepareMobModel(entity, limbAngle, limbDistance, tickDelta);
            model.setupAnim(entity, limbAngle, limbDistance, animationProgress, headYaw, headPitch);
            model.renderToBuffer(matrices, vertexConsumers.getBuffer(RenderType.entityCutoutNoCull(chestCount > 1 ? DOUBLE_CHEST : SINGLE_CHEST)), light,
                    OverlayTexture.NO_OVERLAY);
        }
    }
}

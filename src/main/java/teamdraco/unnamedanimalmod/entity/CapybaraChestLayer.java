package teamdraco.unnamedanimalmod.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import teamdraco.unnamedanimalmod.UAM;

public class CapybaraChestLayer extends RenderLayer<CapybaraRenderState, CapybaraModel> {
    private static final ResourceLocation SINGLE_CHEST = UAM.reloc("textures/entity/single_chest.png");
    private static final ResourceLocation DOUBLE_CHEST = UAM.reloc("textures/entity/double_chest.png");

    public CapybaraChestLayer(RenderLayerParent<CapybaraRenderState, CapybaraModel> context) {
        super(context);
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource multiBufferSource, int i, CapybaraRenderState capybaraRenderState, float v, float v1) {
        final int chestCount = capybaraRenderState.chestCount;
        if(chestCount > 0) {
            CapybaraModel model = this.getParentModel();
            model.setupAnim(capybaraRenderState);
            model.renderToBuffer(poseStack, multiBufferSource.getBuffer(RenderType.entityCutoutNoCull(chestCount > 1 ? DOUBLE_CHEST : SINGLE_CHEST)), i,
                    OverlayTexture.NO_OVERLAY);
        }
    }
}

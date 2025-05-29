package teamdraco.unnamedanimalmod.entity;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;

public class CapybaraModel extends EntityModel<CapybaraRenderState> {
    private final ModelPart body;
    private final ModelPart rightBackLeg;
    private final ModelPart leftBackLeg;
    private final ModelPart rightFrontLeg;
    private final ModelPart leftFrontLeg;
    private final ModelPart head;
    private final ModelPart chestLeft;
    private final ModelPart chestRight;
    private final ModelPart earRight;
    private final ModelPart earLeft;
    private final ModelPart hat;
    private final ModelPart hatBrim;

    public CapybaraModel(ModelPart root) {
        super(root);
        this.body = root.getChild("body");
        this.head = root.getChild("head");
        this.chestRight = this.body.getChild("chestRight");
        this.chestLeft = this.body.getChild("chestLeft");

        this.earRight = this.head.getChild("earRight");
        this.earLeft = this.head.getChild("earLeft");
        this.hat = this.head.getChild("hat");

        this.hatBrim = this.hat.getChild("hatBrim");

        this.rightBackLeg = root.getChild("rightBackLeg");
        this.leftBackLeg = root.getChild("leftBackLeg");
        this.rightFrontLeg = root.getChild("rightFrontLeg");
        this.leftFrontLeg = root.getChild("leftFrontLeg");
    }

    public static LayerDefinition getLayerDefinition() {
        MeshDefinition modelData = new MeshDefinition();
        PartDefinition modelPartData = modelData.getRoot();
        modelPartData.addOrReplaceChild("rightBackLeg", CubeListBuilder.create().texOffs(0, 0).addBox(-2.5F, 0.0F, -2.5F, 5.0F, 11.0F, 5.0F), PartPose.offset(-3.5F, 13.3F, 10.0F));
        modelPartData.addOrReplaceChild("leftBackLeg", CubeListBuilder.create().texOffs(0, 0).addBox(-2.5F, 0.0F, -2.5F, 5.0F, 11.0F, 5.0F), PartPose.offset(3.5F, 13.3F, 10.0F));
        modelPartData.addOrReplaceChild("rightFrontLeg", CubeListBuilder.create().texOffs(50, 0).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 11.0F, 4.0F), PartPose.offset(-4.0F, 13.3F, -8.0F));
        modelPartData.addOrReplaceChild("leftFrontLeg", CubeListBuilder.create().texOffs(50, 0).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 11.0F, 4.0F), PartPose.offset(4.0F, 13.3F, -8.0F));
        PartDefinition modelPartData1 = modelPartData.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-7.0F, -7.0F, -11.0F, 14.0F, 14.0F, 22.0F), PartPose.offset(0.0F, 11.0F, 0.0F));
        modelPartData1.addOrReplaceChild("chestRight", CubeListBuilder.create().texOffs(40, 58).addBox(-2.0F, -4.0F, -4.0F, 2.0F, 8.0F, 8.0F), PartPose.offset(-7.0F, -3.0F, 4.0F));
        modelPartData1.addOrReplaceChild("chestLeft", CubeListBuilder.create().texOffs(60, 58).addBox(0.0F, -4.0F, -4.0F, 2.0F, 8.0F, 8.0F, true), PartPose.offset(7.0F, -3.0F, 4.0F));
        PartDefinition modelPartData2 = modelPartData.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 36).addBox(-4.0F, -5.5F, -11.0F, 8.0F, 10.0F, 14.0F), PartPose.offset(0.0F, 4.5F, -10.0F));
        modelPartData2.addOrReplaceChild("earRight", CubeListBuilder.create().texOffs(0, 16).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 3.0F, 3.0F), PartPose.offset(-3.5F, -5.0F, 1.5F));
        modelPartData2.addOrReplaceChild("earLeft", CubeListBuilder.create().texOffs(0, 16).addBox(0.0F, -2.0F, -1.0F, 1.0F, 3.0F, 3.0F, true), PartPose.offset(3.5F, -5.0F, 1.5F));
        PartDefinition modelPartData3 = modelPartData2.addOrReplaceChild("hat", CubeListBuilder.create().texOffs(30, 36).addBox(-3.5F, -4.0F, -1.5F, 7.0F, 4.0F, 7.0F), PartPose.offset(0.0F, -5.1F, -1.5F));
        modelPartData3.addOrReplaceChild("hatBrim", CubeListBuilder.create().texOffs(45, 17).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 0.0F, 5.0F), PartPose.offset(0.0F, 0.0F, -3.5F));

        return LayerDefinition.create(modelData, 80, 74);
    }

    @Override
    public void setupAnim(CapybaraRenderState state) {
        this.earRight.xRot = -0.39269908169872414f;
        this.earRight.yRot = -0.39269908169872414f;
        this.body.xRot = -0.08726646259971647f;
        this.hat.xRot = -0.3127630032889644f;
        this.earLeft.xRot = -0.39269908169872414f;
        this.earLeft.yRot = 0.39269908169872414f;

        float speed = 1.0f;
        float degree = 1.0f;

        this.head.xRot = state.xRot * (Mth.PI / 180f);
        this.head.yRot = state.yRot * (Mth.PI / 180f);
        this.body.yRot = 0;
        this.body.zRot = Mth.cos((float) (state.z * speed * 0.4f)) * degree * 0.15f * state.walkAnimationSpeed;

        if(state.isInWater) {
            this.body.yRot = Mth.cos(state.ageInTicks * speed * 0.4f) * degree * 0.05f * 1;
            this.body.zRot = 0;
            this.leftBackLeg.xRot = Mth.cos(1.0f + state.ageInTicks * speed * 0.4f) * degree * 1.2f * 0.2f + 0.45f;
            this.rightBackLeg.xRot = Mth.cos(1.0F + state.ageInTicks * speed * 0.4F) * degree * -1.2F * 0.2F + 0.45F;
            this.rightFrontLeg.xRot = Mth.cos(1.0F + state.ageInTicks * speed * 0.4F) * degree * 0.8F * 0.2F + 0.45F;
            this.leftFrontLeg.xRot = Mth.cos(1.0F + state.ageInTicks * speed * 0.4F) * degree * -0.8F * 0.2F + 0.45F;
            this.head.xRot += Mth.cos(state.ageInTicks * speed * 0.4F) * degree * 0.2F * 0.2F - 0.25F;
        } else {
            if(state.isSitting) {
                this.body.y = 17.0F;
                this.body.yRot = 0.0F;
                this.rightBackLeg.y = 21.3F;
                this.rightBackLeg.yRot = -0.3490658503988659F;
                this.rightBackLeg.xRot = 1.5708F;
                this.leftBackLeg.y = 21.3F;
                this.leftBackLeg.yRot = 0.3490658503988659F;
                this.leftBackLeg.xRot = 1.5708F;
                this.rightFrontLeg.y = 22.3F;
                this.rightFrontLeg.yRot = 0.3490658503988659F;
                this.rightFrontLeg.xRot = -1.5708F;
                this.leftFrontLeg.y = 22.3F;
                this.leftFrontLeg.yRot = -0.3490658503988659F;
                this.leftFrontLeg.xRot = -1.5708F;
                this.head.y = 10.5F;
            } else {
                this.body.y = 11.0F;
                this.body.yRot = 0.0F;
                this.rightBackLeg.y = 13.3F;
                this.rightBackLeg.yRot = 0.0F;
                this.leftBackLeg.y = 13.3F;
                this.leftBackLeg.yRot = 0.0F;
                this.rightFrontLeg.y = 13.3F;
                this.rightFrontLeg.yRot = 0.0F;
                this.leftFrontLeg.y = 13.3F;
                this.leftFrontLeg.yRot = 0.0F;
                this.head.y = 4.5F;

                this.leftBackLeg.xRot = Mth.cos(1.0F + state.walkAnimationPos * speed * 0.4F) * degree * 0.8F * state.walkAnimationSpeed;
                this.rightBackLeg.xRot = Mth.cos(1.0F + state.walkAnimationPos * speed * 0.4F) * degree * -0.8F * state.walkAnimationSpeed;
                this.rightFrontLeg.xRot = Mth.cos(1.0F + state.walkAnimationPos * speed * 0.4F) * degree * 0.8F * state.walkAnimationSpeed;
                this.leftFrontLeg.xRot = Mth.cos(1.0F + state.walkAnimationPos * speed * 0.4F) * degree * -0.8F * state.walkAnimationSpeed;
            }
        }
    }
}

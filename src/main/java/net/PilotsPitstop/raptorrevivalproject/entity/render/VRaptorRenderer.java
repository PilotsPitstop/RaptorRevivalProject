package net.PilotsPitstop.raptorrevivalproject.entity.render;

import net.PilotsPitstop.raptorrevivalproject.RaptorRevivalProject;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.PilotsPitstop.raptorrevivalproject.entity.custom.VRaptorEntity;
import net.PilotsPitstop.raptorrevivalproject.entity.model.VRaptorModel;

public class VRaptorRenderer extends MobRenderer<VRaptorEntity, VRaptorModel<VRaptorRenderer>>
{
    protected static final ResourceLocation TEXTURE =
            new ResourceLocation(RaptorRevivalProject.MOD_ID, "textures/entity/vraptor.png");

    public VRaptorRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new VRaptorModel<>(), 0.7F);
    }

    @Override
    public ResourceLocation getEntityTexture(VRaptorEntity entity) {
        return TEXTURE;
    }
}

package net.PilotsPitstop.raptorrevivalproject.entity.render;

import net.PilotsPitstop.raptorrevivalproject.RaptorRevivalProject;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.PilotsPitstop.raptorrevivalproject.entity.custom.PigeonEntity;
import net.PilotsPitstop.raptorrevivalproject.entity.model.PigeonModel;

public class PigeonRenderer extends MobRenderer<PigeonEntity, PigeonModel<PigeonEntity>>
{
    protected static final ResourceLocation TEXTURE =
            new ResourceLocation(RaptorRevivalProject.MOD_ID, "textures/entity/pigeon.png");

    public PigeonRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new PigeonModel<>(), 0.2F);
    }

    @Override
    public ResourceLocation getEntityTexture(PigeonEntity entity) {
        return TEXTURE;
    }
}

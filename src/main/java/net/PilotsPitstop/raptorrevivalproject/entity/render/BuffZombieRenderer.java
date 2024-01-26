package net.PilotsPitstop.raptorrevivalproject.entity.render;

import net.PilotsPitstop.raptorrevivalproject.RaptorRevivalProject;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.PilotsPitstop.raptorrevivalproject.entity.custom.BuffZombieEntity;
import net.PilotsPitstop.raptorrevivalproject.entity.model.BuffZombieModel;

public class BuffZombieRenderer extends MobRenderer<BuffZombieEntity, BuffZombieModel<BuffZombieEntity>>
{
    protected static final ResourceLocation TEXTURE =
            new ResourceLocation(RaptorRevivalProject.MOD_ID, "textures/entity/buff_zombie.png");

    public BuffZombieRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new BuffZombieModel<>(), 0.7F);
    }

    @Override
    public ResourceLocation getEntityTexture(BuffZombieEntity entity) {
        return TEXTURE;
    }
}

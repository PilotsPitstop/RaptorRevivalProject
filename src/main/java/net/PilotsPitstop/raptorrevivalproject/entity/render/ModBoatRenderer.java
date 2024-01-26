package net.PilotsPitstop.raptorrevivalproject.entity.render;

import net.PilotsPitstop.raptorrevivalproject.RaptorRevivalProject;
import net.minecraft.client.renderer.entity.BoatRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.entity.item.BoatEntity;
import net.minecraft.util.ResourceLocation;

public class ModBoatRenderer extends BoatRenderer {
    private static final ResourceLocation BOAT_TEXTURE =
            new ResourceLocation(RaptorRevivalProject.MOD_ID, "textures/entity/boat/redwood.png");

    public ModBoatRenderer(EntityRendererManager p_i46179_1_) {
        super(p_i46179_1_);
    }

    @Override
    public ResourceLocation getEntityTexture(BoatEntity entity) {
        return BOAT_TEXTURE;
    }
}

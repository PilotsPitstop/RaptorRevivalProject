package net.PilotsPitstop.raptorrevivalproject.world.dimension;

import net.PilotsPitstop.raptorrevivalproject.RaptorRevivalProject;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

public class ModDimensions {
    public static RegistryKey<World> KJDim = RegistryKey.getOrCreateKey(Registry.WORLD_KEY,
            new ResourceLocation(RaptorRevivalProject.MOD_ID, "kjdim"));
}

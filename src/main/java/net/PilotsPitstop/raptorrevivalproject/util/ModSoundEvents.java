package net.PilotsPitstop.raptorrevivalproject.util;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.PilotsPitstop.raptorrevivalproject.RaptorRevivalProject;

public class ModSoundEvents {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, RaptorRevivalProject.MOD_ID);

    public static final RegistryObject<SoundEvent> SMALL_EXPLOSION =
            registerSoundEvent("small_explosion");

    public static final RegistryObject<SoundEvent> BAR_BRAWL =
            registerSoundEvent("bar_brawl");

    private static RegistryObject<SoundEvent> registerSoundEvent(String name) {
        return SOUND_EVENTS.register(name, () -> new SoundEvent(new ResourceLocation(RaptorRevivalProject.MOD_ID, name)));
    }

    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }
}

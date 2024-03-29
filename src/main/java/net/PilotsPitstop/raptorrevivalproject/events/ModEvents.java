package net.PilotsPitstop.raptorrevivalproject.events;

import net.PilotsPitstop.raptorrevivalproject.RaptorRevivalProject;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.server.command.ConfigCommand;
import net.PilotsPitstop.raptorrevivalproject.commands.ReturnHomeCommand;
import net.PilotsPitstop.raptorrevivalproject.commands.SetHomeCommand;

@Mod.EventBusSubscriber(modid = RaptorRevivalProject.MOD_ID)
public class ModEvents {

    @SubscribeEvent
    public static void onCommandsRegister(RegisterCommandsEvent event) {
        new SetHomeCommand(event.getDispatcher());
        new ReturnHomeCommand(event.getDispatcher());

        ConfigCommand.register(event.getDispatcher());
    }

    @SubscribeEvent
    public static void onPlayerCloneEvent(PlayerEvent.Clone event) {
        if(!event.getOriginal().getEntityWorld().isRemote()) {
            event.getPlayer().getPersistentData().putIntArray(RaptorRevivalProject.MOD_ID + "homepos",
                    event.getOriginal().getPersistentData().getIntArray(RaptorRevivalProject.MOD_ID + "homepos"));
        }
    }
}

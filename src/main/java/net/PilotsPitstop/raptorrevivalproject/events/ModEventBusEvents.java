package net.PilotsPitstop.raptorrevivalproject.events;

import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.PilotsPitstop.raptorrevivalproject.RaptorRevivalProject;
import net.PilotsPitstop.raptorrevivalproject.entity.ModEntityTypes;
import net.PilotsPitstop.raptorrevivalproject.entity.custom.BuffZombieEntity;
import net.PilotsPitstop.raptorrevivalproject.entity.custom.PigeonEntity;
import net.PilotsPitstop.raptorrevivalproject.events.loot.FirestoneAdditionModifier;
import net.PilotsPitstop.raptorrevivalproject.events.loot.FirestoneStructureAdditionModifier;
import net.PilotsPitstop.raptorrevivalproject.item.custom.ModSpawnEggItem;

import javax.annotation.Nonnull;

@Mod.EventBusSubscriber(modid = RaptorRevivalProject.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {
    @SubscribeEvent
    public static void addEntityAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntityTypes.BUFF_ZOMBIE.get(), BuffZombieEntity.setCustomAttributes().create());
        event.put(ModEntityTypes.PIGEON.get(), PigeonEntity.setCustomAttributes().create());
    }

    @SubscribeEvent
    public static void onRegisterEntities(RegistryEvent.Register<EntityType<?>> event) {
        ModSpawnEggItem.initSpawnEggs();
    }

    @SubscribeEvent
    public static void registerModifierSerializers(@Nonnull final RegistryEvent.Register<GlobalLootModifierSerializer<?>>
                                                           event) {
        event.getRegistry().registerAll(
                new FirestoneAdditionModifier.Serializer().setRegistryName
                        (new ResourceLocation(RaptorRevivalProject.MOD_ID,"firestone_from_magma")),
                new FirestoneStructureAdditionModifier.Serializer().setRegistryName
                        (new ResourceLocation(RaptorRevivalProject.MOD_ID,"firestone_in_igloo"))
        );
    }
}

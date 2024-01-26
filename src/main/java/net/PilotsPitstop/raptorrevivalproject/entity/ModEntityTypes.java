package net.PilotsPitstop.raptorrevivalproject.entity;

import net.PilotsPitstop.raptorrevivalproject.RaptorRevivalProject;
import net.PilotsPitstop.raptorrevivalproject.entity.custom.BuffZombieEntity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.PilotsPitstop.raptorrevivalproject.entity.custom.VRaptorEntity;
import net.PilotsPitstop.raptorrevivalproject.entity.custom.ModBoatEntity;
import net.PilotsPitstop.raptorrevivalproject.entity.custom.PigeonEntity;

public class ModEntityTypes {
    public static DeferredRegister<EntityType<?>> ENTITY_TYPES
            = DeferredRegister.create(ForgeRegistries.ENTITIES, RaptorRevivalProject.MOD_ID);

    public static final RegistryObject<EntityType<BuffZombieEntity>> BUFF_ZOMBIE =
            ENTITY_TYPES.register("buff_zombie",
                    () -> EntityType.Builder.create(BuffZombieEntity::new,
                                    EntityClassification.MONSTER).size(1f, 3f)
                            .build(new ResourceLocation(RaptorRevivalProject.MOD_ID, "buff_zombie").toString()));

    public static final RegistryObject<EntityType<PigeonEntity>> PIGEON =
            ENTITY_TYPES.register("pigeon",
                    () -> EntityType.Builder.create(PigeonEntity::new,
                                    EntityClassification.CREATURE).size(0.4f, 0.3f)
                            .build(new ResourceLocation(RaptorRevivalProject.MOD_ID, "pigeon").toString()));


    public static final RegistryObject<EntityType<VRaptorEntity>> VRAPTOR =
            ENTITY_TYPES.register("vraptor",
                    () -> EntityType.Builder.create(VRaptorEntity::new,
                                    EntityClassification.MONSTER).size(1f, 3f)
                            .build(new ResourceLocation(RaptorRevivalProject.MOD_ID, "vraptor").toString()));


    public static final RegistryObject<EntityType<ModBoatEntity>> REDWOOD_BOAT =
            ENTITY_TYPES.register("redwood_boat",
                    () -> EntityType.Builder.<ModBoatEntity>create(ModBoatEntity::new,
                                    EntityClassification.MISC).size(0.5f, 0.5f)
                            .build(new ResourceLocation(RaptorRevivalProject.MOD_ID, "redwood_boat").toString()));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}

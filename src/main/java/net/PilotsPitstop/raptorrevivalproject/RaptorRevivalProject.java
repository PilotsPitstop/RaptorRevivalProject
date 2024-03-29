package net.PilotsPitstop.raptorrevivalproject;

import com.google.common.collect.ImmutableMap;
import net.PilotsPitstop.raptorrevivalproject.entity.render.VRaptorRenderer;
import net.minecraft.block.Block;
import net.minecraft.block.WoodType;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.Atlases;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.tileentity.SignTileEntityRenderer;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.world.gen.Heightmap;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.PilotsPitstop.raptorrevivalproject.block.ModBlocks;
import net.PilotsPitstop.raptorrevivalproject.block.ModWoodTypes;
import net.PilotsPitstop.raptorrevivalproject.container.ModContainers;
import net.PilotsPitstop.raptorrevivalproject.data.recipes.ModRecipeTypes;
import net.PilotsPitstop.raptorrevivalproject.entity.ModEntityTypes;
import net.PilotsPitstop.raptorrevivalproject.entity.render.BuffZombieRenderer;
import net.PilotsPitstop.raptorrevivalproject.entity.render.ModBoatRenderer;
import net.PilotsPitstop.raptorrevivalproject.entity.render.PigeonRenderer;
import net.PilotsPitstop.raptorrevivalproject.fluid.ModFluids;
import net.PilotsPitstop.raptorrevivalproject.item.ModItems;
import net.PilotsPitstop.raptorrevivalproject.paintings.ModPaintings;
import net.PilotsPitstop.raptorrevivalproject.screen.LightningChannelerScreen;
import net.PilotsPitstop.raptorrevivalproject.tileentity.ModTileEntities;
import net.PilotsPitstop.raptorrevivalproject.util.ModItemModelProperties;
import net.PilotsPitstop.raptorrevivalproject.util.ModSoundEvents;
import net.PilotsPitstop.raptorrevivalproject.world.biome.ModBiomes;
import net.PilotsPitstop.raptorrevivalproject.world.biome.ModBiomesDatapack;
import net.PilotsPitstop.raptorrevivalproject.world.gen.ModBiomeGeneration;
import net.PilotsPitstop.raptorrevivalproject.world.structure.ModStructures;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import top.theillusivec4.curios.api.SlotTypeMessage;
import top.theillusivec4.curios.api.SlotTypePreset;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(RaptorRevivalProject.MOD_ID)
public class RaptorRevivalProject {
    public static final String MOD_ID = "raptorrevivalproject";

    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();
]
    public RaptorRevivalProject() {
        // Register the setup method for modloading
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(eventBus);
        ModBlocks.register(eventBus);
        ModTileEntities.register(eventBus);
        ModContainers.register(eventBus);

        ModStructures.register(eventBus);
        ModFluids.register(eventBus);
        ModRecipeTypes.register(eventBus);
        ModSoundEvents.register(eventBus);

        ModEntityTypes.register(eventBus);
        ModBiomes.register(eventBus);
        ModBiomesDatapack.register(eventBus);
        ModPaintings.register(eventBus);

        GeckoLib.initialize();
        eventBus.addListener(this::setup);
        // Register the enqueueIMC method for modloading
        eventBus.addListener(this::enqueueIMC);
        // Register the processIMC method for modloading
        eventBus.addListener(this::processIMC);
        // Register the doClientStuff method for modloading
        eventBus.addListener(this::doClientStuff);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            AxeItem.BLOCK_STRIPPING_MAP = new ImmutableMap.Builder<Block, Block>().putAll(AxeItem.BLOCK_STRIPPING_MAP)
                    .put(ModBlocks.REDWOOD_LOG.get(), ModBlocks.STRIPPED_REDWOOD_LOG.get())
                    .put(ModBlocks.REDWOOD_WOOD.get(), ModBlocks.STRIPPED_REDWOOD_WOOD.get()).build();

            ModStructures.setupStructures();
            WoodType.register(ModWoodTypes.REDWOOD);

            ModBiomeGeneration.generateBiomes();

            EntitySpawnPlacementRegistry.register(ModEntityTypes.BUFF_ZOMBIE.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND,
                    Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MonsterEntity::canMonsterSpawn);
            EntitySpawnPlacementRegistry.register(ModEntityTypes.PIGEON.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND,
                    Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::canAnimalSpawn);
        });
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            RenderTypeLookup.setRenderLayer(ModBlocks.AMETHYST_DOOR.get(), RenderType.getCutout());
            RenderTypeLookup.setRenderLayer(ModBlocks.AMETHYST_TRAPDOOR.get(), RenderType.getCutout());

            RenderTypeLookup.setRenderLayer(ModBlocks.OATS.get(), RenderType.getCutout());

            RenderTypeLookup.setRenderLayer(ModBlocks.REDWOOD_LEAVES.get(), RenderType.getCutout());
            RenderTypeLookup.setRenderLayer(ModBlocks.REDWOOD_SAPLING.get(), RenderType.getCutout());

            RenderTypeLookup.setRenderLayer(ModBlocks.HYACINTH.get(), RenderType.getCutout());

            ScreenManager.registerFactory(ModContainers.LIGHTNING_CHANNELER_CONTAINER.get(),
                    LightningChannelerScreen::new);

            ClientRegistry.bindTileEntityRenderer(ModTileEntities.SIGN_TILE_ENTITIES.get(),
                    SignTileEntityRenderer::new);
            Atlases.addWoodType(ModWoodTypes.REDWOOD);

            RenderTypeLookup.setRenderLayer(ModFluids.OIL_FLUID.get(), RenderType.getTranslucent());
            RenderTypeLookup.setRenderLayer(ModFluids.OIL_BLOCK.get(), RenderType.getTranslucent());
            RenderTypeLookup.setRenderLayer(ModFluids.OIL_FLOWING.get(), RenderType.getTranslucent());

            ModItemModelProperties.makeBow(ModItems.KAUPENBOW.get());

            RenderTypeLookup.setRenderLayer(ModBlocks.KAUPEN_ALTAR.get(), RenderType.getCutout());
        });
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.BUFF_ZOMBIE.get(), BuffZombieRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.VRAPTOR.get(), VRaptorRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.PIGEON.get(), PigeonRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.REDWOOD_BOAT.get(), ModBoatRenderer::new);
    }

    private void enqueueIMC(final InterModEnqueueEvent event) {
        InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE,
                () -> SlotTypePreset.CHARM.getMessageBuilder().build());
    }

    private void processIMC(final InterModProcessEvent event) {

    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
        // do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    // You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
    // Event bus for receiving Registry Events)
    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        @SubscribeEvent
        public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {
            // register a new block here
            LOGGER.info("HELLO from Register Block");
        }
    }
}

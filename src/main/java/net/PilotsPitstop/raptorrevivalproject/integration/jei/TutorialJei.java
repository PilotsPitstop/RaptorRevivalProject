package net.PilotsPitstop.raptorrevivalproject.integration.jei;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.PilotsPitstop.raptorrevivalproject.RaptorRevivalProject;
import net.minecraft.client.Minecraft;
import net.minecraft.item.crafting.RecipeManager;
import net.minecraft.util.ResourceLocation;
import net.PilotsPitstop.raptorrevivalproject.data.recipes.LightningChannelerRecipe;
import net.PilotsPitstop.raptorrevivalproject.data.recipes.ModRecipeTypes;

import java.util.Objects;
import java.util.stream.Collectors;

@JeiPlugin
public class TutorialJei implements IModPlugin {
    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(RaptorRevivalProject.MOD_ID, "jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(
                new LightningChannelerRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager rm = Objects.requireNonNull(Minecraft.getInstance().world).getRecipeManager();
        registration.addRecipes(rm.getRecipesForType(ModRecipeTypes.LIGHTNING_RECIPE).stream()
                        .filter(r -> r instanceof LightningChannelerRecipe).collect(Collectors.toList()),
                LightningChannelerRecipeCategory.UID);
    }
}

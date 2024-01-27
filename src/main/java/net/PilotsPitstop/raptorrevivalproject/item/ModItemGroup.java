package net.PilotsPitstop.raptorrevivalproject.item;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ModItemGroup {

    public static final ItemGroup RAPTORREVIVALPROJECT_GROUP = new ItemGroup("RaptorRevivalProject_group") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ModItems.AMETHYST.get());
        }
    };

}

package pl.bartlomiejstepien.cruciblemod;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.ArrayList;
import java.util.List;

public class ModItems
{
    public static final List<Item> ITEMS = new ArrayList<>();

    public static void registerItems(IForgeRegistry<Item> registry)
    {
        for (final Item item : ITEMS)
        {
            registry.register(item);
        }

//        for (final Block block : ModBlocks.BLOCKS)
//        {
//            ItemBlock itemBlock = new ItemBlock(block);
//            registry.register(itemBlock.setRegistryName(block.getRegistryName()));
//            ForgeRegistries.ITEMS.register(itemBlock);
//        }
    }
}

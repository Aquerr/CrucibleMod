package pl.bartlomiejstepien.cruciblemod;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;
import pl.bartlomiejstepien.cruciblemod.block.CrucibleBlock;
import pl.bartlomiejstepien.cruciblemod.tileentity.CrucibleTileEntity;

import java.util.ArrayList;
import java.util.List;

public class ModBlocks
{
    public static final List<Block> BLOCKS = new ArrayList<>();

    public static final CrucibleBlock CRUCIBLE_BLOCK = new CrucibleBlock();

    public static void registerBlocks()
    {
        for (final Block block : BLOCKS)
        {
            ForgeRegistries.BLOCKS.register(block);

            final ItemBlock itemBlock = new ItemBlock(block);
            itemBlock.setRegistryName(block.getRegistryName());
            ForgeRegistries.ITEMS.register(itemBlock);

            GameRegistry.registerTileEntity(CrucibleTileEntity.class, new ResourceLocation(CrucibleMod.MOD_ID, "crucibletileentity"));
        }
    }
}

package pl.bartlomiejstepien.cruciblemod.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import pl.bartlomiejstepien.cruciblemod.CrucibleMod;
import pl.bartlomiejstepien.cruciblemod.ModBlocks;

public class BlockBase extends Block
{
    public BlockBase(Material materialIn)
    {
        super(materialIn);
        ModBlocks.BLOCKS.add(this);
        setCreativeTab(CrucibleMod.CREATIVE_TAB);
    }
}

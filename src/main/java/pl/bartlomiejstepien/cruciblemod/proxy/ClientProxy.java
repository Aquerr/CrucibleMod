package pl.bartlomiejstepien.cruciblemod.proxy;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import pl.bartlomiejstepien.cruciblemod.ModBlocks;
import pl.bartlomiejstepien.cruciblemod.tileentity.CrucibleTileEntity;
import pl.bartlomiejstepien.cruciblemod.tileentity.CrucibleTileEntityRenderer;

@SideOnly(value = Side.CLIENT)
public class ClientProxy extends ServerProxy
{
    @Override
    public void preInit()
    {
        super.preInit();

        for (final Block block : ModBlocks.BLOCKS)
        {
            registerRenderer(block);
        }
    }

    @Override
    public void init()
    {
        super.init();

        ClientRegistry.bindTileEntitySpecialRenderer(CrucibleTileEntity.class, new CrucibleTileEntityRenderer());
    }

    private void registerRenderer(Block block)
    {
        ModelResourceLocation modelResourceLocation = new ModelResourceLocation(block.getRegistryName(), "inventory");
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, modelResourceLocation);
    }
}

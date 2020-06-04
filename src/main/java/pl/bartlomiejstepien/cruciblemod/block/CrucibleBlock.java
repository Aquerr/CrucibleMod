package pl.bartlomiejstepien.cruciblemod.block;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import pl.bartlomiejstepien.cruciblemod.tileentity.CrucibleTileEntity;

import javax.annotation.Nullable;

public class CrucibleBlock extends BlockBase
{
    public static final String REGISTRY_NAME = "crucibleblock";
    public static final String UNLOCALIZED_NAME = "crucible";

    public CrucibleBlock()
    {
        super(Material.ROCK);
        setRegistryName(REGISTRY_NAME);
        setTranslationKey(UNLOCALIZED_NAME);
    }

    @Override
    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state)
    {
        return false;
    }

    @Override
    public boolean hasTileEntity(IBlockState blockState)
    {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(World world, IBlockState state)
    {
//        if (world.isRemote) return null;
        return new CrucibleTileEntity();
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        TileEntity tileEntity = worldIn.getTileEntity(pos);
        if (!(tileEntity instanceof CrucibleTileEntity))
            return false;
        final CrucibleTileEntity crucibleTileEntity = (CrucibleTileEntity)tileEntity;
        final ItemStack itemStack = playerIn.getHeldItem(hand);

        if (itemStack.isEmpty())
            return true;

        if (itemStack.getItem() == Item.getItemFromBlock(Blocks.COBBLESTONE))
        {
            if (crucibleTileEntity.addCobbleStone())
            {
                itemStack.shrink(1);
                return true;
            }
        }
        else if (itemStack.getItem() == Items.BUCKET)
        {
            if (crucibleTileEntity.getLavaBucket())
            {
                if (itemStack.getCount() == 1)
                {
                    playerIn.setHeldItem(hand, new ItemStack(Items.LAVA_BUCKET));
                }
                else
                {
                    itemStack.shrink(1);
                    playerIn.addItemStackToInventory(new ItemStack(Items.LAVA_BUCKET));
                }
                return true;
            }
        }
        return true;
    }
}

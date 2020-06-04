package pl.bartlomiejstepien.cruciblemod.tileentity;

import net.minecraft.block.BlockTorch;
import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.world.World;
import net.minecraftforge.fluids.Fluid;

import javax.annotation.Nullable;

public class CrucibleTileEntity extends TileEntity implements ITickable
{
    private final int maxCobblestoneAmount = 4;
    private final int cobbleMeltTimeTicks = 800;
    private int currentMeltTimeTicks = 0;
    private int currentCobblestoneAmount = 0;
    private boolean isMelting = false;

    private final float maxLavaAmount = Fluid.BUCKET_VOLUME * 4;
    private float lavaIncrement = 1.25f;
    private float currentLavaAmount = 0.0f;

    public CrucibleTileEntity()
    {

    }

    public boolean addCobbleStone()
    {
        if (world.isRemote) return false;

        if (currentCobblestoneAmount < maxCobblestoneAmount)
        {
            currentCobblestoneAmount++;
            return true;
        }
        return false;
    }

    public boolean getLavaBucket()
    {
        if (world.isRemote) return false;

        if (currentLavaAmount >= Fluid.BUCKET_VOLUME)
        {
            currentLavaAmount -= Fluid.BUCKET_VOLUME;
            return true;
        }
        return false;
    }

    @Nullable
    @Override
    public SPacketUpdateTileEntity getUpdatePacket()
    {
        NBTTagCompound nbtTagCompound = new NBTTagCompound();
        writeToNBT(nbtTagCompound);
        int metadata = getBlockMetadata();
        return new SPacketUpdateTileEntity(this.pos, metadata, nbtTagCompound);
    }

    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt)
    {
        readFromNBT(pkt.getNbtCompound());
    }

    @Override
    public NBTTagCompound getUpdateTag()
    {
        NBTTagCompound nbtTagCompound = new NBTTagCompound();
        writeToNBT(nbtTagCompound);
        return nbtTagCompound;
    }

    @Override
    public void handleUpdateTag(NBTTagCompound tag)
    {
        readFromNBT(tag);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound parentNBTTagCompound)
    {
        super.writeToNBT(parentNBTTagCompound);

//        parentNBTTagCompound.setInteger("maxCobblestoneAmount", maxCobblestoneAmount);
        parentNBTTagCompound.setInteger("currentCobblestoneAmount", currentCobblestoneAmount);

        parentNBTTagCompound.setFloat("currentLavaAmount", currentLavaAmount);
//        parentNBTTagCompound.setFloat("maxLavaAmount", maxLavaAmount);

        return parentNBTTagCompound;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);

        this.currentCobblestoneAmount = compound.getInteger("currentCobblestoneAmount");
        this.currentLavaAmount = compound.getFloat("currentLavaAmount");
    }

    private boolean canMelt()
    {
        final IBlockState blockState = world.getBlockState(this.pos.down());
        return blockState.getBlock() instanceof BlockTorch;
    }

    @Override
    public void update()
    {
        if (!super.hasWorld()) return;
        final World world = super.getWorld();
        if (world.isRemote) return;

        if (!canMelt())
        {
            this.isMelting = false;
            return;
        }

        if (currentCobblestoneAmount == 0 && this.currentMeltTimeTicks == 0)
            return;

        if (!this.isMelting)
        {
            this.isMelting = true;
            currentCobblestoneAmount--;
        }

        if (this.cobbleMeltTimeTicks == this.currentMeltTimeTicks)
        {
            this.currentMeltTimeTicks = 0;
            this.isMelting = false;
        }
        this.currentMeltTimeTicks++;


        if (this.currentLavaAmount + lavaIncrement > this.maxLavaAmount)
            this.currentLavaAmount = this.maxLavaAmount;
        else this.currentLavaAmount += lavaIncrement;
//        super.markDirty();
    }
}

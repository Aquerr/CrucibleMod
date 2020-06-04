package pl.bartlomiejstepien.cruciblemod.tileentity;

import net.minecraft.block.Block;
import net.minecraft.client.model.ModelVex;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.client.model.animation.FastTESR;
import org.lwjgl.opengl.GL11;
import pl.bartlomiejstepien.cruciblemod.block.CrucibleBlock;

public class CrucibleTileEntityRenderer extends FastTESR<CrucibleTileEntity>
{
//    private static final ResourceLocation cobblestoneTexture = new ResourceLocation();1

    private static final ResourceLocation cobblestoneTexture = new ResourceLocation("minecraft:textures/blocks/cobblestone.png");
//
//    private static final long INVALID_TIME = 0;
//    private static long lastTime = INVALID_TIME;
//    private static double lastAngularPosition;

//    private static double getNextAngularPosition(double revsPerSecond)
//    {
//        long timeNow = System.nanoTime();
//        if (lastTime == INVALID_TIME) {   // automatically initialise to 0 if not set yet
//            lastTime = timeNow;
//            lastAngularPosition = 0.0;
//        }
//        final double DEGREES_PER_REV = 360.0;
//        final double NANOSECONDS_PER_SECOND = 1e9;
//        double nextAngularPosition = lastAngularPosition + (timeNow - lastTime) * revsPerSecond * DEGREES_PER_REV / NANOSECONDS_PER_SECOND;
//        nextAngularPosition = nextAngularPosition % DEGREES_PER_REV;
//        lastAngularPosition = nextAngularPosition;
//        lastTime = timeNow;
//        return nextAngularPosition;
//    }

//    @Override
//    public void render(CrucibleTileEntity tileEntity, double x, double y, double z, float partialTicks, int destroyStage, float alpha)
//    {
//        this.bindTexture(cobblestoneTexture);
//
//        super.render(tileEntity, x, y, z, partialTicks, destroyStage, alpha);

//        final EntityItem entityItem = new EntityItem(tileEntity.getWorld(), x, y, z, new ItemStack(Item.getItemFromBlock(Blocks.COBBLESTONE)));
//        Minecraft.getMinecraft().getRenderManager().renderEntity(entityItem, x, y, z, 0f, 0f, false);

//        try
//        {
//            GL11.glPushMatrix();
//            GL11.glPushAttrib(GL11.GL_ENABLE_BIT);
//
//            GlStateManager.translate(x + 0.5, y + 0.8, + z + 0.5);
////            GlStateManager.rotate((float)getNextAngularPosition(1.0), 0, 1, 0);
//
//            Tessellator tessellator = Tessellator.getInstance();
//            BufferBuilder bufferBuilder = tessellator.getBuffer();
//            this.bindTexture(cobblestoneTexture);
//
//            GL11.glDisable(GL11.GL_LIGHTING);
//            GL11.glDisable(GL11.GL_BLEND);
//            GL11.glDepthMask(true);
//
//            bufferBuilder.begin(GL11.GL_TRIANGLES, DefaultVertexFormats.POSITION_TEX);
//            bufferBuilder.pos(0.000, 1.000, 0.000).tex(0.000, 0.118).endVertex();
//            tessellator.draw();
//        }
//        finally
//        {
//            GL11.glPopAttrib();
//            GL11.glPopMatrix();
//        }





//    }

    @Override
    public void renderTileEntityFast(CrucibleTileEntity tileEntity, double x, double y, double z, float partialTicks, int destroyStage, float partial, BufferBuilder buffer)
    {
        final BlockPos blockPos = tileEntity.getPos();
        final Block block = super.getWorld().getBlockState(blockPos).getBlock();

        if (!(block instanceof CrucibleBlock)) return;

        buffer.setTranslation(x, y, z);

        

        buffer.setTranslation(0, 0, 0);
    }

    @Override
    public boolean isGlobalRenderer(CrucibleTileEntity tileEntity)
    {
        return false;
    }
}

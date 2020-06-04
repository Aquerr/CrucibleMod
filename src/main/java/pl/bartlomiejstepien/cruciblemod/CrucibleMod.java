package pl.bartlomiejstepien.cruciblemod;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import org.apache.logging.log4j.Logger;
import pl.bartlomiejstepien.cruciblemod.proxy.ServerProxy;

@Mod(modid = CrucibleMod.MOD_ID, name = CrucibleMod.NAME, version = CrucibleMod.VERSION)
public class CrucibleMod
{
    public static final String MOD_ID = "cruciblemod";
    public static final String NAME = "Crucible Mod";
    public static final String VERSION = "1.0";

    private static Logger logger;

    @Mod.Instance
    public static CrucibleMod INSTANCE;

    @SidedProxy(serverSide = "pl.bartlomiejstepien.cruciblemod.proxy.ServerProxy", clientSide = "pl.bartlomiejstepien.cruciblemod.proxy.ClientProxy")
    private static ServerProxy proxy;

    public static final CreativeTabs CREATIVE_TAB = new CreativeTabs("cruciblemod")
    {
        @Override
        public ItemStack createIcon()
        {
            return new ItemStack(Item.getItemFromBlock(ModBlocks.CRUCIBLE_BLOCK));
        }
    };

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();

        proxy.preInit();
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        // some example code
        logger.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());

        proxy.init();
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
//        proxy.postInit(event);
    }

    @SubscribeEvent
    public static void registerModels(final ModelRegistryEvent event)
    {
//        proxy.registerModels(event);
    }
}

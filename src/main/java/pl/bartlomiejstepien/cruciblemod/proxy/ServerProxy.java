package pl.bartlomiejstepien.cruciblemod.proxy;

import pl.bartlomiejstepien.cruciblemod.ModBlocks;

public class ServerProxy
{
    public void preInit()
    {
        ModBlocks.registerBlocks();
    }

    public void init()
    {

    }
}

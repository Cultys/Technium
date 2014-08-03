package cultys.technium;

import java.text.SimpleDateFormat;
import java.util.Date;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import cultys.technium.init.TechniumBlocks;
import cultys.technium.init.TechniumItems;
import cultys.technium.init.TechniumRecipes;
import cultys.technium.init.TechniumRef;
import cultys.technium.init.TechniumTileEntities;
import cultys.technium.init.TechniumWorldGen;
import cultys.technium.network.CommonProxy;
import cultys.technium.network.PacketDispatcher;

@Mod(modid = TechniumMod.MODID, version = TechniumMod.VERSION, dependencies = "after:coloredlightscore")
public class TechniumMod
{
    public static final String MODID = "technium";
    public static final String VERSION = "1.0";
    
    @Instance(MODID)
    public static TechniumMod instance;
    
    @SidedProxy(clientSide="cultys.technium.network.ClientProxy", serverSide="cultys.technium.network.CommonProxy")
    public static CommonProxy proxy;
    
    TechniumWorldGen worldGen = new TechniumWorldGen();;
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
    	instance = this;
    	
    	/* Load the config */
    	TechniumRef.loadConfig(event.getSuggestedConfigurationFile());
    	
    	
    	/* Initialize the network stuff */
    	PacketDispatcher.registerPackets();
    	NetworkRegistry.INSTANCE.registerGuiHandler(this, proxy);
    	proxy.registerRenders();
    	
    	/* Initialize the blocks, items and tile entities */
    	TechniumBlocks.init();
    	TechniumItems.init();
    	TechniumTileEntities.init();
    }
    
    
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {	
    	/* Initialize the worldgen */
    	GameRegistry.registerWorldGenerator(worldGen, 0);
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
    	/* Initialize the references and recipes */
    	TechniumRef.mapOres();
    	TechniumRecipes.init();
    }
    
    public static void writeToConsole (String message) {
    	Date date = new Date();
    	SimpleDateFormat ft = new SimpleDateFormat ("HH:mm:ss");
    	System.out.println("[" + ft.format(date) + "] [" + MODID + "]: " + message);
    }
    
    public static CreativeTabs techniumTabs = new CreativeTabs("tabTechnium") {
        @SideOnly(Side.CLIENT)
        public Item getTabIconItem() {
            return TechniumItems.itemTechniumShard;
        }
    };
}

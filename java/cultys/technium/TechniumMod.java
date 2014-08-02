package cultys.technium;

import java.text.SimpleDateFormat;
import java.util.Date;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import cultys.technium.blocks.TechniumBlocks;
import cultys.technium.items.TechniumItems;
import cultys.technium.network.CommonProxy;
import cultys.technium.network.PacketDispatcher;
import cultys.technium.recipes.TechniumRecipes;
import cultys.technium.tileentities.TechniumTileEntities;

@Mod(modid = TechniumMod.MODID, version = TechniumMod.VERSION, dependencies = "after:coloredlightscore")
public class TechniumMod
{
    public static final String MODID = "technium";
    public static final String VERSION = "1.0";
    
    @Instance(MODID)
    public static TechniumMod instance;
    
    @SidedProxy(clientSide="net.cultys.technium.network.ClientProxy", serverSide="net.cultys.technium.network.CommonProxy")
    public static CommonProxy proxy;
    
    public static CreativeTabs techniumTabs = new CreativeTabs("tabTechnium") {
        @SideOnly(Side.CLIENT)
        public Item getTabIconItem() {
            return Item.getItemFromBlock(Blocks.anvil);
        }
    };
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
    	TechniumRef.loadConfig(event.getSuggestedConfigurationFile());
    	PacketDispatcher.registerPackets();
    	NetworkRegistry.INSTANCE.registerGuiHandler(this, proxy);
    	proxy.registerRenders();
    	instance = this;
    }
    
    TechniumWorldGen worldGen = new TechniumWorldGen();;
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    	TechniumBlocks.init();
    	TechniumItems.init();
    	TechniumTileEntities.init();
    	TechniumRef.mapOres();
    	TechniumRecipes.init();
    	
    	GameRegistry.registerWorldGenerator(worldGen, 0);
    }
    
    public static void writeToConsole (String message) {
    	Date date = new Date();
    	SimpleDateFormat ft = new SimpleDateFormat ("HH:mm:ss");
    	System.out.println("[" + ft.format(date) + "] [" + MODID + "]: " + message);
    }
}

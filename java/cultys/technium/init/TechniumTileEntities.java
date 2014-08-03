package cultys.technium.init;

import cpw.mods.fml.common.registry.GameRegistry;
import cultys.technium.TechniumMod;
import cultys.technium.tileentities.TileEntityCrusher;
import cultys.technium.tileentities.TileEntityTechnium;
import cultys.technium.tileentities.TileEntityTechniumSource;


public class TechniumTileEntities {

	
	public static void init(){
		
		GameRegistry.registerTileEntity(TileEntityTechnium.class, "TileEntityTechnium");
		GameRegistry.registerTileEntity(TileEntityTechniumSource.class, "TileEntityTechniumSource");
		GameRegistry.registerTileEntity(TileEntityCrusher.class, "TileEntityCrusher");
		
		TechniumMod.writeToConsole("TileEntities initialized.");
	}
}

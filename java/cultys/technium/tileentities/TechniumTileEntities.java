package cultys.technium.tileentities;

import cpw.mods.fml.common.registry.GameRegistry;
import cultys.technium.TechniumMod;


public class TechniumTileEntities {

	
	public static void init(){
		
		GameRegistry.registerTileEntity(TileEntityTechnium.class, "TileEntityTechnium");
		GameRegistry.registerTileEntity(TileEntityTechniumSource.class, "TileEntityTechniumSource");
		GameRegistry.registerTileEntity(TileEntityCrusher.class, "TileEntityCrusher");
		
		TechniumMod.writeToConsole("TileEntities initialized.");
	}
}

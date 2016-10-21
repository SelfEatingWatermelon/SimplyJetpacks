package tonius.simplyjetpacks.integration;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import tonius.simplyjetpacks.SimplyJetpacks;

public abstract class RAItems {
    
    public static ItemStack plateFlux = null;
    public static ItemStack armorFluxPlate = null;
    
    public static void init() {
        SimplyJetpacks.logger.info("Stealing Redstone Arsenal's items");
        
        plateFlux = new ItemStack(GameRegistry.findItem("RedstoneArsenal", "plateFlux"), 1);
        armorFluxPlate = new ItemStack(GameRegistry.findItem("RedstoneArsenal", "armorFluxPlate"), 1, OreDictionary.WILDCARD_VALUE);
    }
    
}

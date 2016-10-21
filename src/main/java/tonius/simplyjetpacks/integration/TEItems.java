package tonius.simplyjetpacks.integration;

import net.minecraft.item.ItemStack;
import tonius.simplyjetpacks.SimplyJetpacks;
import net.minecraftforge.fml.common.registry.GameRegistry;

public abstract class TEItems {
    
    public static ItemStack capacitorBasic = null;
    public static ItemStack capacitorHardened = null;
    public static ItemStack capacitorReinforced = null;
    public static ItemStack capacitorResonant = null;
    public static ItemStack cellBasic = null;
    public static ItemStack dynamoReactant = null;
    public static ItemStack dynamoMagmatic = null;
    public static ItemStack dynamoEnervation = null;
    public static ItemStack dynamoSteam = null;
    public static ItemStack frameCellReinforcedFull = null;
    public static ItemStack frameIlluminator = null;
    public static ItemStack pneumaticServo = null;
    public static ItemStack powerCoilElectrum = null;
    public static ItemStack powerCoilGold = null;
    
    public static void init() {
        SimplyJetpacks.logger.info("Stealing Thermal Expansion's items");
        
        capacitorBasic = new ItemStack(GameRegistry.findItem("ThermalExpansion", "capacitorBasic"), 1);
        capacitorHardened = new ItemStack(GameRegistry.findItem("ThermalExpansion", "capacitorHardened"), 1);
        capacitorReinforced = new ItemStack(GameRegistry.findItem("ThermalExpansion", "capacitorReinforced"), 1);
        capacitorResonant = new ItemStack(GameRegistry.findItem("ThermalExpansion", "capacitorResonant"), 1);
        cellBasic = new ItemStack(GameRegistry.findItem("ThermalExpansion", "cellBasic"), 1);
        dynamoReactant = new ItemStack(GameRegistry.findItem("ThermalExpansion", "dynamoReactant"), 1);
        dynamoMagmatic = new ItemStack(GameRegistry.findItem("ThermalExpansion", "dynamoMagmatic"), 1);
        dynamoEnervation = new ItemStack(GameRegistry.findItem("ThermalExpansion", "dynamoEnervation"), 1);
        dynamoSteam = new ItemStack(GameRegistry.findItem("ThermalExpansion", "dynamoSteam"), 1);
        frameCellReinforcedFull = new ItemStack(GameRegistry.findItem("ThermalExpansion", "frameCellReinforcedFull"), 1);
        frameIlluminator = new ItemStack(GameRegistry.findItem("ThermalExpansion", "frameIlluminator"), 1);
        pneumaticServo = new ItemStack(GameRegistry.findItem("ThermalExpansion", "pneumaticServo"), 1);
        powerCoilElectrum = new ItemStack(GameRegistry.findItem("ThermalExpansion", "powerCoilElectrum"), 1);
        powerCoilGold = new ItemStack(GameRegistry.findItem("ThermalExpansion", "powerCoilGold"), 1);
    }
    
}

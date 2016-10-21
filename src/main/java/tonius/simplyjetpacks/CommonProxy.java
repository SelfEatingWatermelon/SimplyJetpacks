package tonius.simplyjetpacks;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import tonius.simplyjetpacks.crafting.PlatingReturnHandler;
import tonius.simplyjetpacks.handler.EntityInteractHandler;
import tonius.simplyjetpacks.handler.GuiHandler;
import tonius.simplyjetpacks.handler.LivingTickHandler;
import tonius.simplyjetpacks.handler.SyncHandler;
import tonius.simplyjetpacks.setup.ParticleType;
import net.minecraftforge.fml.common.network.NetworkRegistry;

public class CommonProxy {
    
    public void registerHandlers() {
        SimplyJetpacks.logger.info("Registering handlers");
        NetworkRegistry.INSTANCE.registerGuiHandler(SimplyJetpacks.instance, new GuiHandler());
        MinecraftForge.EVENT_BUS.register(new SyncHandler());
        MinecraftForge.EVENT_BUS.register(new PlatingReturnHandler());
        MinecraftForge.EVENT_BUS.register(new EntityInteractHandler());
        MinecraftForge.EVENT_BUS.register(new LivingTickHandler());
    }
    
    public void registerItemModelResourceLocation(Item item, int metadata, String resourcePath, String modelType) {
    }
    
    public void showJetpackParticles(World world, EntityLivingBase wearer, ParticleType particle) {
    }
    
    public void updateCustomKeybinds(String flyKeyName, String descendKeyName) {
    }
    
    public String getPackGUIKey() {
        return null;
    }
    
    public void throwCoFHLibException() {
        throw new RuntimeException("CoFHLib is not installed or not up to date. Please install CoFH Core 3.0.2 or newer, or CoFHLib standalone 1.0.2 or newer.");
    }
    
}

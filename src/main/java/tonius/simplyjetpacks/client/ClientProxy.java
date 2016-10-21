package tonius.simplyjetpacks.client;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiErrorScreen;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.util.math.Vec3d;
//import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import tonius.simplyjetpacks.CommonProxy;
import tonius.simplyjetpacks.SimplyJetpacks;
import tonius.simplyjetpacks.client.handler.ClientTickHandler;
import tonius.simplyjetpacks.client.handler.HUDTickHandler;
import tonius.simplyjetpacks.client.handler.KeyHandler;
import tonius.simplyjetpacks.client.util.ParticleUtils;
import tonius.simplyjetpacks.item.ItemMeta;
import tonius.simplyjetpacks.setup.ParticleType;
import cofh.lib.util.helpers.MathHelper;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.CustomModLoadingErrorDisplayException;
import net.minecraftforge.fml.common.FMLCommonHandler;

public class ClientProxy extends CommonProxy {
    
    private static final Minecraft mc = Minecraft.getMinecraft();
    
    @Override
    public void registerHandlers() {
        super.registerHandlers();
        
        MinecraftForge.EVENT_BUS.register(new ClientTickHandler());
        MinecraftForge.EVENT_BUS.register(new KeyHandler());
        MinecraftForge.EVENT_BUS.register(new HUDTickHandler());
    }
    
    @Override
    public void registerItemModelResourceLocation(Item item, int metadata, String resourcePath, String modelType) {
    	ModelLoader.setCustomModelResourceLocation(item, metadata, new ModelResourceLocation(resourcePath, modelType));
    }

    @Override
    public void showJetpackParticles(World world, EntityLivingBase wearer, ParticleType particle) {
        if (mc.gameSettings.particleSetting == 0 || mc.gameSettings.particleSetting == 1 && mc.theWorld.getTotalWorldTime() % 4L == 0) {
            Vec3d userPos = new Vec3d(wearer.posX, wearer.posY + 1.6D, wearer.posZ);
            
            Random rand = MathHelper.RANDOM;
            
            Vec3d vLeft = new Vec3d(-0.25D, -0.95D, -0.38D);
            vLeft = vLeft.rotateYaw((float) Math.toRadians(-wearer.renderYawOffset));
            
            Vec3d vRight = new Vec3d(0.25D, -0.95D, -0.38D);
            vRight = vRight.rotateYaw((float) Math.toRadians(-wearer.renderYawOffset));
            
            Vec3d vCenter = new Vec3d((rand.nextFloat() - 0.5F) * 0.25F, -0.95D, -0.38D);
            vCenter = vCenter.rotateYaw((float) Math.toRadians(-wearer.renderYawOffset));
            
            vLeft = vLeft.addVector(-wearer.motionX * 0.2D, -wearer.motionY * 0.2D, -wearer.motionZ * 0.2D);
            vRight = vRight.addVector(-wearer.motionX * 0.2D, -wearer.motionY * 0.2D, -wearer.motionZ * 0.2D);
            vCenter = vCenter.addVector(-wearer.motionX * 0.2D, -wearer.motionY * 0.2D, -wearer.motionZ * 0.2D);
            
            Vec3d v = userPos.addVector(vLeft.xCoord, vLeft.yCoord, vLeft.zCoord);
            ParticleUtils.spawnParticle(particle, world, v.xCoord, v.yCoord, v.zCoord, rand.nextDouble() * 0.05D - 0.025D, -0.2D, rand.nextDouble() * 0.05D - 0.025D);
            
            v = userPos.addVector(vRight.xCoord, vRight.yCoord, vRight.zCoord);
            ParticleUtils.spawnParticle(particle, world, v.xCoord, v.yCoord, v.zCoord, rand.nextDouble() * 0.05D - 0.025D, -0.2D, rand.nextDouble() * 0.05D - 0.025D);
            
            v = userPos.addVector(vCenter.xCoord, vCenter.yCoord, vCenter.zCoord);
            ParticleUtils.spawnParticle(particle, world, v.xCoord, v.yCoord, v.zCoord, rand.nextDouble() * 0.05D - 0.025D, -0.2D, rand.nextDouble() * 0.05D - 0.025D);
        }
    }
    
    @Override
    public void updateCustomKeybinds(String flyKeyName, String descendKeyName) {
        KeyHandler.updateCustomKeybinds(flyKeyName, descendKeyName);
    }
    
    @Override
    public String getPackGUIKey() {
        int keyCode = KeyHandler.keyOpenPackGUI.getKeyCode();
        if (keyCode == 0) {
            return null;
        }
        return GameSettings.getKeyDisplayString(keyCode);
    }
    
    @Override
    @SuppressWarnings("serial")
    public void throwCoFHLibException() {
        throw new CustomModLoadingErrorDisplayException() {
            
            @Override
            public void initGui(GuiErrorScreen errorScreen, FontRenderer fontRenderer) {
            }
            
            @Override
            public void drawScreen(GuiErrorScreen gui, FontRenderer fontRenderer, int mouseRelX, int mouseRelY, float tickTime) {
                gui.drawDefaultBackground();
                gui.drawCenteredString(fontRenderer, "Simply Jetpacks Error - CoFHLib Not Found", gui.width / 2, 85, 0xFF5555);
                
                gui.drawCenteredString(fontRenderer, "CoFHLib is not installed or not up to date.", gui.width / 2, 100, 0xFFFFFF);
                gui.drawCenteredString(fontRenderer, "Please install CoFH Core 3.0.2 or newer, or CoFHLib standalone 1.0.2 or newer.", gui.width / 2, 110, 0xFFFFFF);
            }
            
        };
    }
    
}

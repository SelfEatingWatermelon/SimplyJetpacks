package tonius.simplyjetpacks.item;

import tonius.simplyjetpacks.SimplyJetpacks;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ItemRegistered extends Item {
    
    public ItemRegistered(String name) {
    	super();
    	this.setUnlocalizedName(SimplyJetpacks.PREFIX + name);
    	this.setRegistryName(new ResourceLocation(SimplyJetpacks.MODID, name));

    	GameRegistry.register(this);
    }

}

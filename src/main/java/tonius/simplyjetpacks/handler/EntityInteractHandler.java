package tonius.simplyjetpacks.handler;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.EntityInteract;
import tonius.simplyjetpacks.SimplyJetpacks;
import tonius.simplyjetpacks.item.ItemPack.ItemJetpack;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EntityInteractHandler {
    
    @SubscribeEvent
    public void onEntityInteract(EntityInteract evt) {
    	EntityPlayer player = evt.getEntityPlayer();
    	EntityLiving target = (EntityLiving)evt.getTarget();
        if (player.isSneaking() && (target instanceof EntityZombie || target instanceof EntitySkeleton)) {
            ItemStack heldStack = player.getHeldItemMainhand();
            if (heldStack != null && heldStack.getItem() instanceof ItemJetpack) {
                if (!target.worldObj.isRemote) {
                	ItemStack chest = target.getItemStackFromSlot(EntityEquipmentSlot.CHEST); 
                	if (chest != null)
                	{
                		target.entityDropItem(chest, 0.0F);
                	}
                    target.setItemStackToSlot(EntityEquipmentSlot.CHEST, heldStack.copy());
                    target.setDropChance(EntityEquipmentSlot.CHEST, 2.0F);
                    target.enablePersistence();
                    player.setHeldItem(EnumHand.MAIN_HAND, null);
                }
            }
        }
    }
	
}

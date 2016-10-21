package tonius.simplyjetpacks.item;

import java.util.List;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tonius.simplyjetpacks.SimplyJetpacks;
import tonius.simplyjetpacks.item.meta.PackBase;
import tonius.simplyjetpacks.setup.FuelType;
import tonius.simplyjetpacks.setup.ModCreativeTab;
import tonius.simplyjetpacks.util.SJStringHelper;
import cofh.api.energy.IEnergyProvider;
import cofh.lib.util.helpers.BlockHelper;

public class ItemJetpackFueller extends ItemRegistered {
    
    public ItemJetpackFueller(String registryName) {
        super(registryName);
        
        this.setUnlocalizedName(SimplyJetpacks.PREFIX + "jetpackFueller");
        this.setCreativeTab(ModCreativeTab.instance);
        this.setMaxStackSize(1);
        this.setFull3D();
        SimplyJetpacks.proxy.registerItemModelResourceLocation(this, 0, SimplyJetpacks.MODID + ":" + registryName, "inventory");
    }


    @Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStack, World world, EntityPlayer player, EnumHand hand) {
        RayTraceResult blockPos = BlockHelper.getCurrentMovingObjectPosition(player, true);
        if (blockPos == null || blockPos.typeOfHit != RayTraceResult.Type.BLOCK) {
            return new ActionResult(EnumActionResult.PASS, itemStack);
        }

        player.setActiveHand(hand);
        return new ActionResult(EnumActionResult.SUCCESS, itemStack);
    }
    
    @Override
    public int getMaxItemUseDuration(ItemStack itemStack) {
        return Short.MAX_VALUE;
    }
    
	@Override
    public EnumAction getItemUseAction(ItemStack par1ItemStack) {
        return EnumAction.BOW;
    }
    
	// TODO: Fueler does not work on EIO capacitor blocks (never has apparently...)
    @Override
	public void onUsingTick(ItemStack stack, EntityLivingBase player, int count) {
        RayTraceResult blockPos = BlockHelper.getCurrentMovingObjectPosition((EntityPlayer)player, true);
        if (blockPos == null || blockPos.typeOfHit != RayTraceResult.Type.BLOCK) {
        	player.setActiveHand(null);
        } else {
        	player.setActiveHand(EnumHand.MAIN_HAND);
            if (player.worldObj.isRemote) {
                return;
            }
            
            ItemStack chestplate = player.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
            if (chestplate == null || !(chestplate.getItem() instanceof ItemPack)) {
            	SimplyJetpacks.logger.info("Jetpack not equipped");
                return;
            }
            ItemPack packItem = (ItemPack) chestplate.getItem();
            PackBase pack = packItem.getPack(chestplate);
            if (pack == null) {
            	SimplyJetpacks.logger.info("Unrecognized jetpack");
                return;
            }
            FuelType fuelType = pack.fuelType;
            
            player.worldObj.getBlockState(blockPos.getBlockPos()).getBlock();
            TileEntity tile = player.worldObj.getTileEntity(blockPos.getBlockPos());
            if (tile == null) {
            	SimplyJetpacks.logger.info("There is no TileEntity associated with this block");
            	return;
            }

            int toPull = Math.min(pack.fuelPerTickIn, packItem.getMaxFuelStored(chestplate) - packItem.getFuelStored(chestplate));
            int pulled = 0;
            
            switch (fuelType) {
            	case ENERGY:
                	if (tile instanceof IEnergyProvider) {
                		IEnergyProvider tileEnergy = (IEnergyProvider)tile;
                		pulled = tileEnergy.extractEnergy(blockPos.sideHit, toPull, false);
                	} else if (tile.hasCapability(CapabilityEnergy.ENERGY, blockPos.sideHit)) {
                		IEnergyStorage energyStorage = tile.getCapability(CapabilityEnergy.ENERGY, blockPos.sideHit);
                		pulled = energyStorage.extractEnergy(toPull, false);
                	}
            		break;

            	case FLUID:
                    if (tile.hasCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, blockPos.sideHit)) {
                		IFluidHandler fluidHandler = tile.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, blockPos.sideHit);
                		FluidStack fluid = fluidHandler.drain(toPull, false);
                   		if (fluid != null && fluid.getFluid().getName().equals(pack.fuelFluid)) {
                       		fluid = fluidHandler.drain(toPull, true);
                       		if (fluid != null) {
                       			pulled = fluid.amount;
                       		}
                   		}
                    }
            		break;

            	default:
            		break;
            }

            if (pulled > 0) {
                packItem.addFuel(chestplate, pulled, false);
            }
        }
    }
    
	@Override
    @SideOnly(Side.CLIENT)
    @SuppressWarnings("unchecked")
    public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean bool) {
        if (SJStringHelper.canShowDetails()) {
            SJStringHelper.addDescriptionLines(list, "jetpackFueller");
        } else {
            list.add(SJStringHelper.getShiftText());
        }
    }
    
}

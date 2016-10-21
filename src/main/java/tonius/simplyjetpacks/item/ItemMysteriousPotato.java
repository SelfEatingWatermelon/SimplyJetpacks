package tonius.simplyjetpacks.item;

import java.util.List;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagFloat;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import tonius.simplyjetpacks.SimplyJetpacks;
import tonius.simplyjetpacks.setup.ModCreativeTab;
import tonius.simplyjetpacks.setup.ModItems;
import tonius.simplyjetpacks.util.SJStringHelper;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemMysteriousPotato extends ItemRegistered {
    
    public ItemMysteriousPotato(String registryName) {
        super(registryName);
        
        this.setUnlocalizedName(SimplyJetpacks.PREFIX + registryName);
        this.setCreativeTab(ModCreativeTab.instance);
        SimplyJetpacks.proxy.registerItemModelResourceLocation(this, 0, SimplyJetpacks.MODID + ":" + registryName, "inventory");
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    @SuppressWarnings("unchecked")
    public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean bool) {
        list.add(SJStringHelper.localize("tooltip.mysteriousPotato.description"));
    }
    
    @Override
    public EnumRarity getRarity(ItemStack itemStack) {
        return EnumRarity.EPIC;
    }

	@Override
	public boolean hasEffect(ItemStack stack) {
		return true;
	}

	// TODO: Potato changes spawner zombie => pig
	/*
	@Override
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!world.isRemote) {
            TileEntity tile = world.getTileEntity(pos);
            if (tile instanceof TileEntityMobSpawner) {
                NBTTagCompound tag = new NBTTagCompound();
                tile.writeToNBT(tag);
                
                tag.setString("EntityId", "Zombie");
                
                NBTTagList spawnPotentials = new NBTTagList();
                NBTTagCompound zombieSpawn = new NBTTagCompound();
                
                zombieSpawn.setString("Type", "Zombie");
                zombieSpawn.setInteger("Weight", 1);
                
                NBTTagCompound zombieSpawnProperties = new NBTTagCompound();
                zombieSpawnProperties.setString("id", "Zombie");
                
                NBTTagList equipment = new NBTTagList();
                equipment.appendTag(new NBTTagCompound());
                equipment.appendTag(new NBTTagCompound());
                equipment.appendTag(new NBTTagCompound());
                equipment.appendTag(ModItems.jetpackPotato.writeToNBT(new NBTTagCompound()));
                zombieSpawnProperties.setTag("Equipment", equipment);
                
                NBTTagList dropChances = new NBTTagList();
                for (int i = 0; i <= 4; i++) {
                    dropChances.appendTag(new NBTTagFloat(0.0F));
                }
                zombieSpawnProperties.setTag("DropChances", dropChances);
                
                zombieSpawn.setTag("Properties", zombieSpawnProperties);
                spawnPotentials.appendTag(zombieSpawn);
                
                tag.setTag("SpawnPotentials", spawnPotentials);
                
                tag.setShort("SpawnCount", (short) 2);
                tag.setShort("SpawnRange", (short) 8);
                tag.setShort("Delay", (short) -1);
                tag.setShort("MinSpawnDelay", (short) 30);
                tag.setShort("MaxSpawnDelay", (short) 60);
                tag.setShort("MaxNearbyEntities", (short) 10);
                tag.setShort("RequiredPlayerRange", (short) 96);
                tile.readFromNBT(tag);
            }
        }

        return EnumActionResult.SUCCESS;
    }
    */

}

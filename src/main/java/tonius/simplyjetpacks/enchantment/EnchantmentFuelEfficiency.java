package tonius.simplyjetpacks.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import tonius.simplyjetpacks.SimplyJetpacks;
import tonius.simplyjetpacks.item.ItemPack;
import tonius.simplyjetpacks.setup.ModEnchantments;

public class EnchantmentFuelEfficiency extends Enchantment {
    
	public EnchantmentFuelEfficiency(String name, EnumEnchantmentType enchantType) {
		super(Rarity.RARE, enchantType, new EntityEquipmentSlot[]{EntityEquipmentSlot.CHEST});
		this.setName(name);
		this.setRegistryName(new ResourceLocation(SimplyJetpacks.MODID, name));
	}

	@Override
    public int getMinEnchantability(int level) {
        return 8 + (level - 1) * 8;
    }
    
    @Override
    public int getMaxEnchantability(int level) {
        return super.getMinEnchantability(level) + 50;
    }
    
    @Override
    public int getMaxLevel() {
        return 4;
    }
    
    @Override
    public String getName() {
        return SimplyJetpacks.PREFIX + super.getName();
    }
    
    @Override
    public boolean canApply(ItemStack stack) {
        return stack.getItem() instanceof ItemPack;
    }
    
}

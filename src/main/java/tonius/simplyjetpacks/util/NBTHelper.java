package tonius.simplyjetpacks.util;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public abstract class NBTHelper {
    
    public static NBTTagCompound getNBT(ItemStack stack) {
        if (!stack.hasTagCompound()) {
        	stack.setTagCompound(new NBTTagCompound());
        }
        return stack.getTagCompound();
    }
    
    public static boolean getNBTBoolean(ItemStack stack, String tag, boolean fallback) {
        NBTTagCompound tagCompound = getNBT(stack);
        if (!tagCompound.hasKey(tag)) {
            tagCompound.setBoolean(tag, fallback);
        }
        return tagCompound.getBoolean(tag);
    }
    
}

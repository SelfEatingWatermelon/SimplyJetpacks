package tonius.simplyjetpacks.client.audio;

import tonius.simplyjetpacks.SimplyJetpacks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModSounds {

    public static SoundEvent SOUND;
	public static SoundEvent SOUND_OTHER;
	public static SoundEvent ROCKET;
	public static SoundEvent EXPLOSION;
	
	public static void preInit() {
		SOUND = GameRegistry.register(createSoundEvent("jetpack"));
		SOUND_OTHER = GameRegistry.register(createSoundEvent("jetpack_other"));
		ROCKET = GameRegistry.register(createSoundEvent("rocket"));
		EXPLOSION = SoundEvent.REGISTRY.getObject(new ResourceLocation("minecraft", "entity.generic.explode"));
	}
	
	private static SoundEvent createSoundEvent(String soundName) {
		final ResourceLocation soundID = new ResourceLocation(SimplyJetpacks.MODID, soundName);
		return new SoundEvent(soundID).setRegistryName(soundID);
	}
}
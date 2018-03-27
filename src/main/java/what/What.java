package what;

import com.google.common.collect.BiMap;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

@Mod(modid = What.MODID, acceptedMinecraftVersions = "[1.12, 1.13)", clientSideOnly = true)
@Mod.EventBusSubscriber
public class What
{
    public static final String MODID = "what";

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onStitch(TextureStitchEvent.Pre event)
    {
        BiMap<String,Fluid> masterFluidReference = ReflectionHelper.getPrivateValue(FluidRegistry.class,null,"masterFluidReference");
        TextureMap map = event.getMap();
        for (Fluid fluid : masterFluidReference.values()) {
            map.registerSprite(fluid.getStill());
            map.registerSprite(fluid.getFlowing());
        }
    }
}

package io.github.thatsmusic99.norefreshscroll.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.multiplayer.MultiplayerScreen;
import net.minecraft.client.gui.screen.multiplayer.MultiplayerServerListWidget;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(MultiplayerScreen.class)
public class MultiplayerScreenMixin {

    @Shadow private boolean initialized;

    @Redirect(method = "refresh", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/MinecraftClient;setScreen(Lnet/minecraft/client/gui/screen/Screen;)V"))
    public void realignScrollBar(MinecraftClient client, Screen screen) {
        if (!(screen instanceof MultiplayerScreen)) {
            client.setScreen(screen);
            return;
        }
        Screen currentScreen = client.currentScreen;
        if (!(currentScreen instanceof MultiplayerScreen)) {
            client.setScreen(screen);
            return;
        }
        MultiplayerScreen multiplayerScreen = (MultiplayerScreen) currentScreen;
        MultiplayerServerListWidget oldListWidget = ((MultiplayerScreenAccessor) multiplayerScreen).getServerListWidget();
        double scrollAmount = oldListWidget.getScrollAmount();
        // Check if the screen is initialised.
        if (!((MultiplayerScreenAccessor) screen).isInitialized()) {
            screen.init(client, client.getWindow().getScaledWidth(), client.getWindow().getScaledHeight());
            ((MultiplayerScreenAccessor) screen).invokeInit();
        }
        // Get the new server list widget.
        MultiplayerServerListWidget newListWidget = ((MultiplayerScreenAccessor) screen).getServerListWidget();
        newListWidget.setScrollAmount(scrollAmount);
        // Set the screen.
        client.setScreen(screen);
    }
}

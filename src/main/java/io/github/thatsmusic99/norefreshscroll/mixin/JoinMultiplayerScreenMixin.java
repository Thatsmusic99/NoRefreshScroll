package io.github.thatsmusic99.norefreshscroll.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.multiplayer.JoinMultiplayerScreen;
import net.minecraft.client.gui.screens.multiplayer.ServerSelectionList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(JoinMultiplayerScreen.class)
public class JoinMultiplayerScreenMixin {

    @Redirect(method = "refreshServerList", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Minecraft;setScreen(Lnet/minecraft/client/gui/screens/Screen;)V"))
    public void realignScrollBar(Minecraft client, Screen screen) {
        if (!(screen instanceof JoinMultiplayerScreen)) {
            client.setScreen(screen);
            return;
        }
        Screen currentScreen = client.screen;
        if (!(currentScreen instanceof JoinMultiplayerScreen multiplayerScreen)) {
            client.setScreen(screen);
            return;
        }
        ServerSelectionList oldListWidget = ((JoinMultiplayerScreenAccessor) multiplayerScreen).getServerSelectionList();

        double scrollAmount = oldListWidget.scrollAmount();

        // Set the screen.
        client.setScreen(screen);

        // Get the new server list widget.
        ServerSelectionList newListWidget = ((JoinMultiplayerScreenAccessor) screen).getServerSelectionList();
        newListWidget.setScrollAmount(scrollAmount);
    }
}

package io.github.thatsmusic99.norefreshscroll.mixin;

import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.multiplayer.JoinMultiplayerScreen;
import net.minecraft.client.gui.screens.multiplayer.ServerSelectionList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(JoinMultiplayerScreen.class)
public class JoinMultiplayerScreenMixin {

    @Redirect(method = "refreshServerList", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Gui;setScreen(Lnet/minecraft/client/gui/screens/Screen;)V"))
    public void realignScrollBar(Gui gui, Screen screen) {
        if (!(screen instanceof JoinMultiplayerScreen)) {
            gui.setScreen(screen);
            return;
        }
        Screen currentScreen = gui.screen();
        if (!(currentScreen instanceof JoinMultiplayerScreen multiplayerScreen)) {
            gui.setScreen(screen);
            return;
        }
        ServerSelectionList oldListWidget = ((JoinMultiplayerScreenAccessor) multiplayerScreen).getServerSelectionList();

        double scrollAmount = oldListWidget.scrollAmount();

        // Set the screen.
        gui.setScreen(screen);

        // Get the new server list widget.
        ServerSelectionList newListWidget = ((JoinMultiplayerScreenAccessor) screen).getServerSelectionList();
        newListWidget.setScrollAmount(scrollAmount);
    }
}

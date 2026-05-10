package io.github.thatsmusic99.norefreshscroll.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.multiplayer.JoinMultiplayerScreen;
import net.minecraft.client.gui.screens.multiplayer.ServerSelectionList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(JoinMultiplayerScreen.class)
public class JoinMultiplayerScreenMixin {

    @WrapOperation(method = "refreshServerList", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Minecraft;setScreen(Lnet/minecraft/client/gui/screens/Screen;)V"))
    public void realignScrollBar(Minecraft instance, Screen screen, Operation<Void> original) {
        if (!(screen instanceof JoinMultiplayerScreen)) {
            original.call(instance, screen);
            return;
        }
        Screen currentScreen = instance.screen;
        if (!(currentScreen instanceof JoinMultiplayerScreen multiplayerScreen)) {
            original.call(instance, screen);
            return;
        }

        ServerSelectionList oldListWidget = ((JoinMultiplayerScreenAccessor) multiplayerScreen).getServerSelectionList();

        double scrollAmount = oldListWidget.scrollAmount();

        // Set the screen.
        original.call(instance, screen);

        // Get the new server list widget.
        ServerSelectionList newListWidget = ((JoinMultiplayerScreenAccessor) screen).getServerSelectionList();
        newListWidget.setScrollAmount(scrollAmount);
    }
}

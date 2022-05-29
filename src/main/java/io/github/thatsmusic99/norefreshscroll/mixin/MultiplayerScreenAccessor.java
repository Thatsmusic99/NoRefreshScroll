package io.github.thatsmusic99.norefreshscroll.mixin;

import net.minecraft.client.gui.screen.multiplayer.MultiplayerScreen;
import net.minecraft.client.gui.screen.multiplayer.MultiplayerServerListWidget;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(MultiplayerScreen.class)
public interface MultiplayerScreenAccessor {

    @Accessor
    MultiplayerServerListWidget getServerListWidget();

    @Accessor
    boolean isInitialized();

    @Invoker
    void invokeInit();
}

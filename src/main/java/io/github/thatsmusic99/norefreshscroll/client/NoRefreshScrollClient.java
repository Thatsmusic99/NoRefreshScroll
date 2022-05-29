package io.github.thatsmusic99.norefreshscroll.client;

import net.fabricmc.api.ClientModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@net.fabricmc.api.Environment(net.fabricmc.api.EnvType.CLIENT)
public class NoRefreshScrollClient implements ClientModInitializer {

    public static final Logger LOGGER = LoggerFactory.getLogger("norefreshscroll");

    @Override
    public void onInitializeClient() {
        LOGGER.info("Client initialised. Hello!");
    }
}

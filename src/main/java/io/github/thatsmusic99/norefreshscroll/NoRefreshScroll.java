package io.github.thatsmusic99.norefreshscroll;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NoRefreshScroll implements ModInitializer {

    public static final Logger LOGGER = LoggerFactory.getLogger("norefreshscroll");

    @Override
    public void onInitialize() {
        LOGGER.info("Main class initialised.");
    }
}

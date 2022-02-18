package com.chenyue;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Slf4j
public class AppTest {
    //private static final Logger logger = LoggerFactory.getLogger(AppTest.class);

    @Test
    void test01() {
        log.debug("logback success");
        log.info("logback success");
        log.error("logback success");
    }
}

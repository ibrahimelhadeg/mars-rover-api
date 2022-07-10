package com.mars.rover.it;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MainTest {

    @Test
    void givenLogsAppendedToList_whenInvokePsvm_thenLogApplicationStarted() {
        final ListAppender<ILoggingEvent> appender = new ListAppender<>();
        final var mainLogger = (Logger) LoggerFactory.getLogger(Main.class);
        appender.start();
        mainLogger.addAppender(appender);

        Main.main(new String[]{});

        assertThat(appender.list)
                .extracting(ILoggingEvent::getFormattedMessage)
                .contains("Application started");

        mainLogger.detachAppender(appender);
    }
}

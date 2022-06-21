package com.mars.rover;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MainTest {

    @Test
    void givenSystemOutRedirection_whenInvokePsvm_thenOutputHelloWorld() {
        final ByteArrayOutputStream OUTPUT_STREAM_CAPTOR = new ByteArrayOutputStream();
        System.setOut(new PrintStream(OUTPUT_STREAM_CAPTOR));

        Main.main(new String[]{});

        assertThat(OUTPUT_STREAM_CAPTOR.toString().trim())
                .contains("Hello World!");
    }

    @Test
    void givenLogsAppendedToList_whenInvokePsvm_thenLogApplicationStarted() {
        final ListAppender<ILoggingEvent> appender = new ListAppender<>();
        final Logger mainLogger = (Logger) LoggerFactory.getLogger(Main.class);
        appender.start();
        mainLogger.addAppender(appender);

        Main.main(new String[]{});

        assertThat(appender.list)
                .extracting(ILoggingEvent::getFormattedMessage)
                .contains("Application started");

        mainLogger.detachAppender(appender);
    }
}

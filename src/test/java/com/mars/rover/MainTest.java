package com.mars.rover;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;

import org.junit.jupiter.api.Test;

import static java.lang.reflect.Modifier.isPrivate;

import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.assertThrows;

class MainTest {

    @Test
    void test_for_nonInstantiability() throws NoSuchMethodException {
        Constructor<Main> constructor = Main.class.getDeclaredConstructor();
        assertThat(isPrivate(constructor.getModifiers())).isTrue();

        constructor.setAccessible(true);
        InvocationTargetException thrown = assertThrows(
                InvocationTargetException.class,
                constructor::newInstance,
                InvocationTargetException.class.getSimpleName() + " was expected");
        assertThat(thrown.getTargetException()).isInstanceOf(AssertionError.class);

        AssertionError targetException = (AssertionError) thrown.getTargetException();
        assertThat(targetException.getMessage())
                .isEqualTo("Suppress default constructor for non-instantiability");
    }

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

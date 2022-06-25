package com.mars.rover.core;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.assertThrows;

import static com.mars.rover.core.Command.*;

class CommandDecodingTest {

    private static Stream<Arguments> allowedCommands() {
        return Stream.of(
                Arguments.of("FLFRB",
                        List.of(FORWARD, LEFT, FORWARD, RIGHT, BACKWARD)),
                Arguments.of("RrRffFB",
                        List.of(RIGHT, RIGHT, RIGHT, FORWARD, FORWARD, FORWARD, BACKWARD)),
                Arguments.of("B",
                        List.of(BACKWARD))
        );
    }

    private static Stream<Arguments> nonAllowedCommands() {
        return Stream.of(
                Arguments.of("XLFRB", "X"),
                Arguments.of("R*RffFB", "*"),
                Arguments.of("B\"BB", "\"")
        );
    }

    @ParameterizedTest
    @MethodSource("allowedCommands")
    void test_decoding_set_of_command(String commandCodes,
                                      List<Command> expectedCommands) {
        var command = Command.ofCodes(commandCodes);

        assertThat(command).isEqualTo(expectedCommands);
    }

    @ParameterizedTest(name = "{index} \"{0}\" should FAIL because of \"{1}\"")
    @MethodSource("nonAllowedCommands")
    void test_failed_decoding_command(String commandCodes,
                                      String failingCommand) {
        var thrown = assertThrows(
                IllegalArgumentException.class,
                () -> Command.ofCodes(commandCodes),
                "An" + IllegalArgumentException.class.getSimpleName() + " was expected");

        assertThat(thrown.getMessage())
                .isEqualTo("\"" + failingCommand + "\" is not a known Command code");
    }
}

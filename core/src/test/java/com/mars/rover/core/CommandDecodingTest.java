package com.mars.rover.core;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.assertThrows;

import static com.mars.rover.core.Command.*;

class CommandDecodingTest {

    private static Stream<AllowedTestCase> allowedCommands() {
        return Stream.of(
                new AllowedTestCase(
                        "FLFRB",
                        List.of(FORWARD, LEFT, FORWARD, RIGHT, BACKWARD)),
                new AllowedTestCase(
                        "RrRffFB",
                        List.of(RIGHT, RIGHT, RIGHT, FORWARD, FORWARD, FORWARD, BACKWARD)),
                new AllowedTestCase(
                        "B", List.of(BACKWARD))
        );
    }

    private static Stream<NonAllowedTestCase> nonAllowedCommands() {
        return Stream.of(
                new NonAllowedTestCase("XLFRB", "X"),
                new NonAllowedTestCase("R*RffFB", "*"),
                new NonAllowedTestCase("B\"BB", "\"")
        );
    }

    @ParameterizedTest
    @MethodSource("allowedCommands")
    void test_decoding_set_of_command(AllowedTestCase testCase) {
        var command = Command.ofCodes(testCase.commandCodes());

        assertThat(command).isEqualTo(testCase.expectedCommands());
    }

    @ParameterizedTest
    @MethodSource("nonAllowedCommands")
    void test_failed_decoding_command(NonAllowedTestCase testCase) {
        var thrown = assertThrows(
                IllegalArgumentException.class,
                () -> Command.ofCodes(testCase.commandCodesInput()),
                "An" + IllegalArgumentException.class.getSimpleName() + " was expected");

        assertThat(thrown.getMessage())
                .isEqualTo("\"" + testCase.failingCommand() + "\" is not a known Command code");
    }

    private record AllowedTestCase(String commandCodes,
                                   List<Command> expectedCommands) {
    }

    private record NonAllowedTestCase(String commandCodesInput,
                                      String failingCommand) {
    }
}

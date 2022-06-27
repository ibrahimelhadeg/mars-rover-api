package com.mars.rover.core;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public enum Command {

    LEFT('L'),
    RIGHT('R'),

    BACKWARD('B'),
    FORWARD('F');

    private static final Map<Character, Command> COMMAND_TO_DIRECTION =
            Arrays.stream(values())
                    .collect(Collectors.toUnmodifiableMap(
                            Command::code,
                            command -> command));

    private final char code;

    private static Command ofCode(char code) {
        var command =
                COMMAND_TO_DIRECTION.get(
                        Character.toUpperCase(code));
        if (command != null) {
            return command;
        }
        throw new IllegalArgumentException(
                MessageFormat.format(
                        "\"{0}\" is not a known {1} code", code, Command.class.getSimpleName()));
    }

    public static List<Command> ofCodes(String codes) {
        return IntStream.range(0, codes.length())
                .mapToObj(index -> ofCode(codes.charAt(index)))
                .toList();
    }
}

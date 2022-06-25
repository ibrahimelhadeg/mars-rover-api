package com.mars.rover.core;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import static java.util.Collections.unmodifiableMap;

@Getter
@ToString
@AllArgsConstructor
public enum Command {

    LEFT('L'),
    RIGHT('R'),

    BACKWARD('B'),
    FORWARD('F');

    private static final Map<Character, Command> COMMAND_TO_DIRECTION;

    static {
        var mutableCommandMap = new HashMap<Character, Command>();
        for (var command : values()) {
            mutableCommandMap.put(command.getCode(), command);
        }
        COMMAND_TO_DIRECTION = unmodifiableMap(mutableCommandMap);
    }

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

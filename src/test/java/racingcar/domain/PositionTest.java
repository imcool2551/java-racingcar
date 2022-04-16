package racingcar.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PositionTest {

    private static final int SMALL_POSITION = 1;
    private static final int BIG_POSITION = 100;

    @DisplayName("생성자에 음수 전달하면 예외")
    @Test
    void validation() {
        assertThatThrownBy(() -> new Position(-1))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void move() {
        // given
        int startPosition = 5;
        int moveCount = 3;
        Position position = new Position(startPosition);

        // when
        for (int i = 0; i < moveCount; i++) {
            position = position.move();
        }

        // then
        assertThat(position).isEqualTo(new Position(startPosition + moveCount));
    }

    @ParameterizedTest
    @MethodSource("provideArgumentsForEquals")
    void equals(Position position, Position anotherPosition, boolean expected) {
        assertThat(position.equals(anotherPosition)).isEqualTo(expected);
    }

    private static Stream<Arguments> provideArgumentsForEquals() {
        return Stream.of(
                Arguments.of(new Position(), new Position(), true),
                Arguments.of(new Position(SMALL_POSITION), new Position(SMALL_POSITION), true),
                Arguments.of(new Position(SMALL_POSITION), new Position(BIG_POSITION), false)
        );
    }

    @ParameterizedTest
    @MethodSource("provideArgumentsForCompareTo")
    void compareTo(Position position, Position anotherPosition, int expected) {
        assertThat(position.compareTo(anotherPosition)).isEqualTo(expected);
    }

    private static Stream<Arguments> provideArgumentsForCompareTo() {
        final int SAME = 0;
        final int GREATER = 1;
        final int SMALLER = -1;

        return Stream.of(
                Arguments.of(new Position(), new Position(), SAME),
                Arguments.of(new Position(SMALL_POSITION), new Position(SMALL_POSITION), SAME),
                Arguments.of(new Position(BIG_POSITION), new Position(SMALL_POSITION), GREATER),
                Arguments.of(new Position(SMALL_POSITION), new Position(BIG_POSITION), SMALLER)
        );
    }
}
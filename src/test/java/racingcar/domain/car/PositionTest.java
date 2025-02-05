package racingcar.domain.car;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import racingcar.domain.random.FixedRandomNumberGenerator;
import racingcar.domain.random.RandomNumberGenerator;

import static org.assertj.core.api.Assertions.assertThat;

class PositionTest {
    private final String defaultName = "김이름";

    @Test
    void 기본_생성자로_생성할경우_초기위치는_0이다() {
        Position position = new Position();
        Position zeroPosition = new Position(0);

        Assertions.assertThat(position).isEqualTo(zeroPosition);
    }

    @Nested
    class max_메서드는 {
        private final int anyNumber = 3;
        private final RandomNumberGenerator anyRandomNumberGenerator = new FixedRandomNumberGenerator(anyNumber);

        @Test
        void 자신의_위치와_비교해_max_를_리턴한다() {
            int carLocation = 3;
            Position carPosition = new Position(carLocation);

            Car car = new Car(defaultName, carLocation, anyRandomNumberGenerator);
            Position max = car.maxLocation(new Position(carLocation - 1));

            assertThat(max).isEqualTo(carPosition);
        }
    }

    @Test
    void 음수_위치는_생성될수_없다() {
        int negativePosition = -1;

        Assertions.assertThatIllegalArgumentException()
                .isThrownBy(() -> new Position(negativePosition));
    }

    @Test
    void 현재위치가_주어진값보다_큰경우_true를_리턴한다() {
        Position smallPosition = new Position(3);
        Position position = new Position(4);

        assertThat(position.isGreaterThan(smallPosition)).isTrue();
    }

    @Test
    void move_호출시_위치를_1_증가시킨_Position_객체를_리턴한다() {
        int initPosition = 1;

        Position position = new Position(initPosition);
        Position expectedPosition = new Position(initPosition + 1);

        position = position.move();

        Assertions.assertThat(position).isEqualTo(expectedPosition);
    }
}

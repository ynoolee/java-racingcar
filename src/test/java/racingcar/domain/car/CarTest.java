package racingcar.domain.car;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import racingcar.domain.random.RandNum;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayNameGeneration(ReplaceUnderscores.class)
class CarTest {
    private final String defaultName = "horse";

    @Test
    void 자동차의_초기_위치값은_0이다() {
        Car car = new Car(defaultName);

        int location = car.location().getLocation();

        assertThat(location).isZero();
    }

    @Nested
    class move_메소드는 {
        @Test
        void 전달된_값이_4이상인_경우_1_전진한다() {
            Car car = new Car(defaultName);
            Position movedLocation = new Position(car.location().getLocation() + 1);
            int passedValue = 4;

            car.move(new RandNum(passedValue));

            Position currentLocation = car.location();

            Assertions.assertThat(currentLocation)
                    .isEqualTo(movedLocation);
        }

        @Test
        void 전달된_값이_4미만인_경우_원래위치에_머무른다() {
            Car car = new Car(defaultName);
            Position beforeLocation = car.location();
            int passedValue = 3;

            car.move(new RandNum(passedValue));

            Position currentLocation = car.location();

            Assertions.assertThat(currentLocation)
                    .isEqualTo(beforeLocation);
        }
    }

    @Nested
    class 생성자_메서드는 {
        @Test
        void 다섯글자_를_초과하는_이름을_전달받는경우_생성에_실패한다() {
            String invalidName = "123456";

            Assertions.assertThatExceptionOfType(IllegalArgumentException.class)
                    .isThrownBy(() -> new Car(invalidName));
        }
    }

    @Nested
    class isWinner_메서드는 {
        @Test
        void 현재까지_자동차들의_최대_위치값이_0인경우_0인위치의_자동차는_우승자다() {
            Car carLocatedOnZero = new Car(defaultName);

            Assertions.assertThat(carLocatedOnZero.isWinner(new Position(0)))
                    .isTrue();
        }
    }
}

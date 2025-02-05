package racingcar.domain.random;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class RandomGeneratorTest {

    @Test
    void 생성시_전달된_최소값_이상의_랜덤값을_생성한다() {
        int min = 0;
        int max = 9;

        RangeRandomNumberGenerator randomGenerator = new RangeRandomNumberGenerator(min, max);
        RandNum res = randomGenerator.generate();

        assertThat(res.isGreaterThan(min)).isTrue();
    }
}

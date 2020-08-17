package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import strategy.RandomLottoNumberGenerator;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoTest {

    @DisplayName("로또 생성")
    @Test
    void lotto() {
        Lotto lotto = Lotto.of(new RandomLottoNumberGenerator());

        assertThat(lotto.getNumbers()).hasSize(6);
    }

    @DisplayName("당첨번호에 따른 상금을 계산")
    @Test
    void hasPrize() {
        Lotto lotto = Lotto.of(() -> Arrays.asList(
                LottoNumber.of(1),
                LottoNumber.of(2),
                LottoNumber.of(3),
                LottoNumber.of(4),
                LottoNumber.of(5),
                LottoNumber.of(6))
        );

        long prize = lotto.hasWinningNumber(Arrays.asList(1, 3, 5, 7, 9, 11));

        assertThat(prize).isEqualTo(3);

    }
}

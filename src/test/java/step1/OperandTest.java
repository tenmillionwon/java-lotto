package step1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class OperandTest {

    @DisplayName("숫자 이외의 값을 입력할 경우 예외가 발생한다")
    @ValueSource(strings = {":", "가", "나"})
    @ParameterizedTest
    void valueOf_NotNumber_ExceptionThrown(String operand) {
        assertThatThrownBy(() -> Operand.valueOf(operand))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("숫자만 입력이 가능합니다.");
    }

    @DisplayName("toPositiveInt()는 음수일 경우 예외가 발생한다")
    @ValueSource(strings = {"-1", "-2", "-3"})
    @ParameterizedTest
    void toPositiveInt_NegativeInt_ExceptionThrown(String operand) {
        assertThatThrownBy(() -> Operand.valueOf(operand).toPositiveInt())
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("음수는 사용할 수 없습니다.");
    }

    @DisplayName("toNegativeInt()는 양수일 경우 예외가 발생한다")
    @ValueSource(strings = {"1", "2", "3"})
    @ParameterizedTest
    void toNegativeInt_PositiveInt_ExceptionThrown(String operand) {
        assertThatThrownBy(() -> Operand.valueOf(operand).toNegativeInt())
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("양수는 사용할 수 없습니다.");

    }
}
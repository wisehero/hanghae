package chap02.calculator

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

internal class CaculatorTest {

    private val sut = Calculator()

    @Test
    fun ` 더하기 `() {
        val reuslt: Int = Calculator.plus(1, 2);

        reuslt shouldBe 3
    }


}
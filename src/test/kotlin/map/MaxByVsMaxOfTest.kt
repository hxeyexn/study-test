package map

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.assertThrows

class MaxByVsMaxOfTest {
    @Test
    fun `maxBy와 maxOf의 차이점`() {
        val map =
            mutableMapOf(
                "a" to 1,
                "b" to 2,
                "c" to 3,
                "d" to 4,
            )

        // value 기준으로 최댓값을 구한 후 요소 전체(key, value)를 반환
        val actualMaxBy: Map.Entry<String, Int> = map.maxBy { it.value == map.values.max() }
        assertAll(
            { assertThat(actualMaxBy.key).isEqualTo("d") },
            { assertThat(actualMaxBy.value).isEqualTo(4) },
        )

        // value 기준으로 최댓값을 구한 후 value를 반환
        val actualMaxOf: Int = map.maxOf { it.value }
        assertThat(actualMaxOf).isEqualTo(4)
    }
}

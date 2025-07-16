package map

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class EntriesTest {

    @Test
    fun `entries`() {
        val map =
            mutableMapOf(
                "a" to 1,
                "b" to 2,
                "c" to 3,
                "d" to 4,
            )

        // value 기준으로 최댓값을 구한 후 요소 전체(key, value)를 반환
        val actual: Map.Entry<String, Int> = map.entries.first { it.value == map.values.max() }
        assertAll(
            { assertThat(actual.key).isEqualTo("d") },
            { assertThat(actual.value).isEqualTo(4) },
        )
    }
}

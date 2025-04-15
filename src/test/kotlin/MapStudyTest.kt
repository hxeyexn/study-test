import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class MapStudyTest {
    @Test
    fun `firstNotNullOf - null이 아닌 첫 번째 key를 반환`() {
        val map =
            mutableMapOf(
                null to 1,
                "a" to null,
                "b" to 2,
                "c" to 3,
                "d" to null,
            )

        val firstNonNullKey: String = map.firstNotNullOf { it.key }
        assertThat(firstNonNullKey).isEqualTo("a")
    }

    @Test
    fun `firstNotNullOf - null이 아닌 첫 번째 key 또는 value를 반환 1`() {
        val map =
            mutableMapOf(
                null to 1,
                "a" to null,
                "b" to 2,
                "c" to 3,
                "d" to null,
            )

        val firstNonNullKeyOrValue = map.firstNotNullOf { it.key ?: it.value }
        assertThat(firstNonNullKeyOrValue).isEqualTo(1)
    }

    @Test
    fun `firstNotNullOf - null이 아닌 첫 번째 key 또는 value를 반환 2`() {
        val map =
            mutableMapOf(
                null to null,
                "a" to null,
                "b" to 2,
                "c" to 3,
                "d" to null,
            )

        val firstNonNullKeyOrValue = map.firstNotNullOf { it.key ?: it.value }
        assertThat(firstNonNullKeyOrValue).isEqualTo("a")
    }

    @Test
    fun `firstNotNullOf - null이 아닌 첫 번째 value를 반환`() {
        val map =
            mutableMapOf(
                null to null,
                "a" to null,
                "b" to 2,
                "c" to 3,
                "d" to null,
            )

        val actual: Int = map.firstNotNullOf { it.value }
        assertThat(actual).isEqualTo(2)
    }

    @Test
    fun `firstNotNullOf - value 값이 모두 null이라면 NoSuchElementException 던짐`() {
        val map =
            mutableMapOf(
                null to null,
                "a" to null,
            )

        assertThrows<NoSuchElementException> { map.firstNotNullOf { it.value } }
    }

    @Test
    fun `firstNotNullOfOrNull - value 값이 모두 null이라면 null 반환`() {
        val map =
            mutableMapOf(
                null to null,
                "a" to null,
            )

        val actual: Int? = map.firstNotNullOfOrNull { it.value }
        assertThat(actual).isNull()
    }
}

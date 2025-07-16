package map

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class GetOrDefaultVsGetOrPutTest {
    @Test
    fun `getOrDefault는 key가 없을 경우 기본값을 반환하지만 map에는 저장하지 않는다`() {
        val map = mutableMapOf<String, MutableList<String>>()
        val hannah = map.getOrDefault(key = "해나", mutableListOf())

        assertAll(
            { assertThat(hannah).isEqualTo(mutableListOf<String>()) },
            { assertThat(map["해나"]).isNotEqualTo(mutableListOf<String>()) },
        )
    }

    @Test
    fun `getOrDefault로 얻은 리스트에 값을 추가해도 map에 값이 저장되지 않는다`() {
        val map = mutableMapOf<String, MutableList<String>>()
        val hannah = map.getOrDefault(key = "해나", mutableListOf())
        hannah.add("치킨")

        assertAll(
            { assertThat(map["해나"]).isNotEqualTo(listOf("치킨")) },
            { assertThat(hannah).isEqualTo(listOf("치킨")) },
        )
    }

    @Test
    fun `getOrPut은 key가 없을 경우 기본값을 map에 저장한다`() {
        val map = mutableMapOf<String, MutableList<String>>()
        val hannah = map.getOrPut(key = "해나") { mutableListOf() }

        assertAll(
            { assertThat(hannah).isEqualTo(mutableListOf<String>()) },
            { assertThat(map["해나"]).isEqualTo(mutableListOf<String>()) },
        )
    }

    @Test
    fun `getOrPut으로 얻은 리스트에 값을 추가하면 map에 값이 저장된다`() {
        val map = mutableMapOf<String, MutableList<String>>()
        val hannah = map.getOrPut(key = "해나") { mutableListOf() }
        hannah.add("치킨")

        assertAll(
            { assertThat(map["해나"]).isEqualTo(listOf("치킨")) },
            { assertThat(hannah).isEqualTo(listOf("치킨")) },
        )
    }
}

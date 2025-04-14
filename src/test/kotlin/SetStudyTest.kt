import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class SetStudyTest {
    @Test
    fun `Set 비교 테스트`() {
        val words = mutableListOf("banana", "apple", "orange", "apple", "strawberry", "strawberry")
        val set = words.toSet()

        assertAll(
            // Set끼리의 비교는 "같은 요소를 가지고 있는가"만을 봄
            // 같은 요소들을 모두 포함하고 있다면, 순서가 달라도 equal로 판정
            { assertThat(set).isEqualTo(setOf("apple", "banana", "orange", "strawberry")) },
            // 따라서 List로 변환하면 순서가 동일하지 않기 때문에 not equal로 판정
            { assertThat(set.toList()).isNotEqualTo(setOf("apple", "banana", "orange", "strawberry").toList()) },
        )
    }

    @Test
    fun `Set 정렬 후 비교 테스트`() {
        val words = mutableListOf("banana", "apple", "orange", "apple", "strawberry", "strawberry")
        words.sort()

        // toSet은 내부적으로 LinkedHashSet을 활용
        // LinkedHashSet은 HashSet + LinkedList 구조를 가짐
        // 따라서 중복 제거 기능은 Set처럼 순서는 List처럼 동작
        val sortedSet = words.toSet()
//        public fun <T> Iterable<T>.toSet(): Set<T> {
//            ...
//            return toCollection(LinkedHashSet<T>()).optimizeReadOnlySet()
//        }

        assertAll(
            { assertThat(sortedSet).isEqualTo(setOf("apple", "banana", "orange", "strawberry")) },
            // LinkedHashSet을 사용해서, 원래 컬렉션의 삽입 순서를 보존하기 때문에 equal로 판정
            { assertThat(sortedSet.toList()).isEqualTo(setOf("apple", "banana", "orange", "strawberry").toList()) },
        )
    }

    @Test
    fun `HashSet 정렬 후 비교 테스트`() {
        val words = mutableListOf("banana", "apple", "orange", "apple", "strawberry", "strawberry")

        // HashSet은 순서를 보장하지 않는 Set
        val hashSet = words.toHashSet()

        assertAll(
            // Set끼리의 비교는 "같은 요소를 가지고 있는가"만을 봄
            // 같은 요소들을 모두 포함하고 있다면, 순서가 달라도 equal로 판정
            { assertThat(hashSet).isEqualTo(setOf("apple", "banana", "orange", "strawberry")) },
            // 따라서 List로 변환하면 순서가 동일하지 않기 때문에 not equal로 판정
            { assertThat(hashSet.toList()).isNotEqualTo(setOf("apple", "banana", "orange", "strawberry").toList()) },
        )
    }
}

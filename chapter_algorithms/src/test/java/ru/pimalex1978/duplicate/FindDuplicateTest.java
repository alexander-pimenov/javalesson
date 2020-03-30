package ru.pimalex1978.duplicate;

import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/*
* Почему не запускаются тесты не пойму. Пока с этим вопросом не разбирался.
* */

class FindDuplicateTest {
    @Test
    public void shouldReturnListWithRepeatedValues() {
        // given
        FindDuplicate exercise = new FindDuplicate();

        // when
        List<Integer> result = exercise.findDuplicates(asList(-1, 1, 3, 2, 5, 6, -1, 3, 6), 2);

        // then
        assertThat(asList(-1, 3, 6), containsInAnyOrder(result.toArray()));
    }

    @Test
    public void nullValuesShouldBeOmitted() {
        // given
        FindDuplicate exercise = new FindDuplicate();

        // when
        List<Integer> result = exercise.findDuplicates(asList(1, 1, null, 2, 5, 6, 1, 3, 6, null), 2);

        // then
        assertEquals(singletonList(6), result);
    }

    @Test
    public void nullValuesShouldBeEmptyList() {
        // given
        FindDuplicate exercise = new FindDuplicate();

        // when
        List<Integer> result = exercise.findDuplicates(asList(null, null, 2, 5, 6, 1, 3), 2);

        // then
        assertEquals(emptyList(), result);
    }
}
package numberrangesummarizer;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;


import static org.junit.jupiter.api.Assertions.*;

class NumberRangeSummarizerImplTest {

    private final NumberRangeSummarizer summarizer =
            new NumberRangeSummarizerImpl();

    @Test
    void collectShouldParseCommaSeparatedNumbers() {
        assertEquals(
                Arrays.asList(1, 3, 6, 7, 8),
                summarizer.collect("1,3,6,7,8")
        );
    }

    @Test
    void collectShouldHandleWhitespace() {
        assertEquals(
                Arrays.asList(1, 3, 6),
                summarizer.collect(" 1, 3 , 6 ")
        );
    }

    @Test
    void collectShouldReturnEmptyCollectionForBlankInput() {
        assertEquals(
                Collections.emptyList(),
                summarizer.collect("   ")
        );
    }

    @Test
    void collectShouldReturnEmptyCollectionForNullInput() {
        assertEquals(
                Collections.emptyList(),
                summarizer.collect(null)
        );
    }

    @Test
    void collectShouldRejectInvalidNumbers() {
        assertThrows(
                NumberFormatException.class,
                () -> summarizer.collect("1,abc,3")
        );
    }


    @Test
    void summarizeShouldMatchProvidedExample() {
        Collection<Integer> input = summarizer.collect(
                "1,3,6,7,8,12,13,14,15,21,22,23,24,31"
        );

        assertEquals(
                "1, 3, 6-8, 12-15, 21-24, 31",
                summarizer.summarizeCollection(input)
        );
    }

    @Test
    void summarizeShouldHandleSingleNumber() {
        assertEquals(
                "5",
                summarizer.summarizeCollection(Arrays.asList(5))
        );
    }

    @Test
    void summarizeShouldHandleAllConsecutiveNumbers() {
        assertEquals(
                "1-5",
                summarizer.summarizeCollection(
                        Arrays.asList(1, 2, 3, 4, 5)
                )
        );
    }

    @Test
    void summarizeShouldHandleNonConsecutiveNumbers() {
        assertEquals(
                "1, 3, 5, 7",
                summarizer.summarizeCollection(
                        Arrays.asList(1, 3, 5, 7)
                )
        );
    }

    @Test
    void summarizeShouldSortUnorderedNumbers() {
        assertEquals(
                "1-3, 7-8",
                summarizer.summarizeCollection(
                        Arrays.asList(8, 2, 7, 1, 3)
                )
        );
    }

    @Test
    void summarizeShouldRemoveDuplicates() {
        assertEquals(
                "1-3, 5",
                summarizer.summarizeCollection(
                        Arrays.asList(1, 2, 2, 3, 5, 5)
                )
        );
    }

    @Test
    void summarizeShouldHandleNegativeNumbers() {
        assertEquals(
                "-3--1, 2-3",
                summarizer.summarizeCollection(
                        Arrays.asList(-3, -2, -1, 2, 3)
                )
        );
    }

    @Test
    void summarizeShouldHandleEmptyCollection() {
        assertEquals(
                "",
                summarizer.summarizeCollection(
                        Collections.emptyList()
                )
        );
    }

    @Test
    void summarizeShouldHandleNullCollection() {
        assertEquals(
                "",
                summarizer.summarizeCollection(null)
        );
    }

    @Test
    void summarizeShouldHandleIntegerBoundaries() {
        assertEquals(
                "-2147483648--2147483647, 2147483646-2147483647",
                summarizer.summarizeCollection(
                        Arrays.asList(
                                Integer.MIN_VALUE,
                                Integer.MIN_VALUE + 1,
                                Integer.MAX_VALUE - 1,
                                Integer.MAX_VALUE
                        )
                )
        );
    }


    @Test
    void collectShouldRejectTrailingComma() {
        assertThrows(
                NumberFormatException.class,
                () -> summarizer.collect("1,2,")
        );
    }


    @Test
    void summarizeShouldRejectNullElements() {
        assertThrows(
                IllegalArgumentException.class,
                () -> summarizer.summarizeCollection(
                        Arrays.asList(1, null, 3)
                )
        );
    }

    @Test
    void collectShouldRejectEmptyValues() {
        assertThrows(
                NumberFormatException.class,
                () -> summarizer.collect("1,2, ,4")
        );
    }


    @Test
    void summarizeShouldHandleTwoConsecutiveNumbers() {
        assertEquals(
                "5-6",
                summarizer.summarizeCollection(
                        Arrays.asList(5, 6)
                )
        );
    }




}

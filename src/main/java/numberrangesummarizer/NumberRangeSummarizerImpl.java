package numberrangesummarizer;

import java.util.List;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class NumberRangeSummarizerImpl implements NumberRangeSummarizer {

    @Override
    public Collection<Integer> collect(String input) {
        if (input == null || input.trim().isEmpty()) {
            return Collections.emptyList();
        }

        return Arrays.stream(input.split(",", -1))
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }



    @Override
    public String summarizeCollection(Collection<Integer> input) {
        if (input == null || input.isEmpty()) {
            return "";
        }

        if (input.contains(null)) {
            throw new IllegalArgumentException(
                    "Input collection must not contain null elements"
            );
        }


        List<Integer> numbers = input.stream()
                .sorted()
                .distinct()
                .collect(Collectors.toList());

        StringJoiner result = new StringJoiner(", ");

        int start = numbers.get(0);
        int end = start;

        for (int i = 1; i < numbers.size(); i++) {
            int current = numbers.get(i);

            if ((long) current - end == 1L) {
                end = current;
            } else {
                appendRange(result, start, end);
                start = current;
                end = current;
            }
        }

        appendRange(result, start, end);

        return result.toString();
    }


    private static void appendRange(StringJoiner result, int start, int end) {

        if (start == end) {
            result.add(String.valueOf(start));
        } else {
            result.add(start + "-" + end);
        }
    }


}

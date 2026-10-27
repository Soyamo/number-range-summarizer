# Number Range Summarizer

A Java application that converts a comma-separated list of integers into a summarised string by grouping consecutive numbers into ranges.

This project implements the provided `NumberRangeSummarizer` interface using Java 8-compatible features, with an emphasis on clean code, object-oriented design, robustness, and unit testing.

## Example

**Input:**
```
1,3,6,7,8,12,13,14,15,21,22,23,24,31
```

**Output:**
```
1, 3, 6-8, 12-15, 21-24, 31
```

Consecutive numbers are represented as ranges using a hyphen, while individual numbers remain unchanged.

## Tech Stack

- **Java 8** – Target language version (developed using JDK 19)
- **Maven** – Dependency management and build automation
- **JUnit 5** – Unit testing
- **Git** – Version control

No external runtime dependencies are required.

## Project Structure

```text
number-range-summarizer/
├── src/
│   ├── main/
│   │   └── java/
│   │       └── numberrangesummarizer/
│   │           ├── NumberRangeSummarizer.java
│   │           └── NumberRangeSummarizerImpl.java
│   └── test/
│       └── java/
│           └── numberrangesummarizer/
│               └── NumberRangeSummarizerImplTest.java
├── pom.xml
├── .gitignore
└── README.md
```

## Implementation

The solution implements the two methods defined in the supplied interface.

### `collect(String input)`

Parses a comma-separated string into a `Collection<Integer>`.

The implementation uses Java 8 Streams to split the input, remove surrounding whitespace, convert values to integers, and collect them into a list.

### `summarizeCollection(Collection<Integer> input)`

Groups consecutive integers into ranges and returns a comma-delimited string.

The algorithm:

1. Validates the input collection.
2. Sorts the numbers in ascending order and removes duplicates.
3. Initialises the start and end of the first range.
4. Iterates through the remaining numbers, extending the current range when consecutive values are found.
5. Appends completed ranges to a `StringJoiner`.
6. Returns the formatted string.

A private `appendRange()` helper separates range formatting from the range-detection logic.

### Complexity

For a collection containing `n` integers:

- **Time:** `O(n log n)` due to sorting, followed by a linear scan.
- **Auxiliary space:** `O(n)` for the intermediate collection and output representation.

## Assumptions and Input Handling

Since the provided interface does not specify every edge case, the following assumptions were made and covered by unit tests:

| Scenario | Behaviour |
|---|---|
| Unsorted numbers | Sorted in ascending order |
| Duplicate numbers | Removed before summarisation |
| Two consecutive numbers | Represented as a range, e.g. `5-6` |
| Single number | Returned unchanged |
| Negative numbers | Supported |
| Empty or blank string | Returns an empty collection |
| Null string | Returns an empty collection |
| Empty or null collection | Returns an empty string |
| Invalid numeric values | Throws `NumberFormatException` |
| Empty values between commas | Throws `NumberFormatException` |
| Trailing comma | Throws `NumberFormatException` |
| Null elements in a collection | Throws `IllegalArgumentException` |
| Integer boundary values | Supported without overflow in the consecutive-number check |

## Testing

Unit tests are implemented using JUnit 5.

The test suite contains **19 tests**, covering the supplied example, standard use cases, malformed input, and edge cases.

All 19 tests passed during local development.

### Running the Tests

**Prerequisites:**
- JDK 8 or newer
- Apache Maven 3

Clone the repository and navigate to its root directory.

Run:

```bash
mvn clean test
```

Alternatively, run the Maven `clean` and `test` lifecycle goals from IntelliJ IDEA's Maven tool window.

A successful execution should end with:

```text
Tests run: 19, Failures: 0, Errors: 0, Skipped: 0
BUILD SUCCESS
```

## Design Decisions

- **Interface-based design:** The implementation follows the supplied `NumberRangeSummarizer` contract without modifying its method signatures.
- **Java 8 Streams:** Used for parsing, sorting, and removing duplicate values.
- **Single-pass range detection:** Once the input is sorted, ranges are identified through one iteration.
- **StringJoiner:** Handles comma-separated formatting without introducing trailing separators.
- **Overflow-safe comparison:** Uses `long` arithmetic when checking whether two integers are consecutive.
- **Focused helper method:** Keeps range formatting separate from the main summarisation logic.

The implementation intentionally avoids unnecessary frameworks and dependencies to keep the solution straightforward, maintainable, and easy to test.
package service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StringProcessorServiceTest {
    public static Stream<Arguments> DuplicatedStringIntoCompressedStringTestData() {
        return Stream.of(Arguments.of("AAAAANNNMMMMYYYYuuuu\\\\\\\\\\\\\\UUUUaaaarWWLLLLJ888DDDDDDDDD", "5A3N4M4Y4u7\\\\4U4ar2W4LJ3\\8"));
    }

    public static Stream<Arguments> CompressedStringIntoDuplicatedStringTestData() {
        return Stream.of(Arguments.of("5A3N4M4Y4u7\\\\4U4ar2W4LJ3\\8", "AAAAANNNMMMMYYYYuuuu\\\\\\\\\\\\\\UUUUaaaarWWLLLLJ888DDDDDDDDD"));
    }

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @ParameterizedTest
    @MethodSource("DuplicatedStringIntoCompressedStringTestData")
    void convertDuplicatedStringIntoCompressedString(final String input, final String assertion) {
        final var stringProcessorService = new StringProcessorService();
        final var result = stringProcessorService.convertDuplicatedStringIntoCompressedString(input);
        assertEquals(assertion, result);
    }

    @ParameterizedTest
    @MethodSource("CompressedStringIntoDuplicatedStringTestData")
    void convertCompressedStringIntoDuplicatedString(final String input, final String assertion) {
        final var stringProcessorService = new StringProcessorService();
        final var result = stringProcessorService.convertCompressedStringIntoDuplicatedString(input);
        assertEquals(assertion, result);
    }
}

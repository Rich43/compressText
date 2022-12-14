package service;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StringProcessorServiceTest {
    public static Stream<Arguments> DuplicatedStringIntoCompressedStringTestData() {
        return Stream.of(
                Arguments.of("AAAAANNNMMMMYYYYuuuu\\\\\\\\\\\\\\UUUUaaaarWWLLLLJ888DDDDDDDDD", "5A3N4M4Y4u7\\\\4U4ar2W4LJ3\\89D"),
                Arguments.of(null, null),
                Arguments.of("Hello World", "He2lo World"),
                Arguments.of("Supercalifragilisticexpialidocious", "Supercalifragilisticexpialidocious"),
                Arguments.of(
                        "\u2E69⇂➋☋⍮╻∕ⲯⱲ⾑⠾⾽⣟⽖⤫⸝≫⢯❿\u2FDAⵋ⧧⫎\u2FFDⱢ❄⟾ⅱ⾔☚▷⟲\u2EF9⟝⋞▦ⅸ\u242F┟⮩⣄≂⭘Ⅲ⏋▻Ⳏ⤐⭚ⳣ⻪Ⱙ⪗⦪⎙┉⃬⍳⾑⦍∭⤚⛶⿋™☒⡀⣐\u2FFF⤑⾔Ⓖ⇁⁂⧸⸼♏\u2E79\u20FD⛱\u20C3\u200E⠒⛆⪑⳪⫽ⅇ⟼⫍⏪▄⺵◓⥷⇜⼳┦⃚⫢",
                        "\u2E69⇂➋☋⍮╻∕ⲯⱲ⾑⠾⾽⣟⽖⤫⸝≫⢯❿\u2FDAⵋ⧧⫎\u2FFDⱢ❄⟾ⅱ⾔☚▷⟲\u2EF9⟝⋞▦ⅸ\u242F┟⮩⣄≂⭘Ⅲ⏋▻Ⳏ⤐⭚ⳣ⻪Ⱙ⪗⦪⎙┉⃬⍳⾑⦍∭⤚⛶⿋™☒⡀⣐\u2FFF⤑⾔Ⓖ⇁⁂⧸⸼♏\u2E79\u20FD⛱\u20C3\u200E⠒⛆⪑⳪⫽ⅇ⟼⫍⏪▄⺵◓⥷⇜⼳┦⃚⫢"
                ),
                Arguments.of("◓◓◓◓◓", "5◓")
        );
    }

    public static Stream<Arguments> CompressedStringIntoDuplicatedStringTestData() {
        return Stream.of(
                Arguments.of("5A3N4M4Y4u7\\\\4U4ar2W4LJ3\\89D", "AAAAANNNMMMMYYYYuuuu\\\\\\\\\\\\\\UUUUaaaarWWLLLLJ888DDDDDDDDD"),
                Arguments.of(null, null),
                Arguments.of("He2lo World", "Hello World"),
                Arguments.of("Supercalifragilisticexpialidocious", "Supercalifragilisticexpialidocious"),
                Arguments.of(
                        "\u2E69⇂➋☋⍮╻∕ⲯⱲ⾑⠾⾽⣟⽖⤫⸝≫⢯❿\u2FDAⵋ⧧⫎\u2FFDⱢ❄⟾ⅱ⾔☚▷⟲\u2EF9⟝⋞▦ⅸ\u242F┟⮩⣄≂⭘Ⅲ⏋▻Ⳏ⤐⭚ⳣ⻪Ⱙ⪗⦪⎙┉⃬⍳⾑⦍∭⤚⛶⿋™☒⡀⣐\u2FFF⤑⾔Ⓖ⇁⁂⧸⸼♏\u2E79\u20FD⛱\u20C3\u200E⠒⛆⪑⳪⫽ⅇ⟼⫍⏪▄⺵◓⥷⇜⼳┦⃚⫢",
                        "\u2E69⇂➋☋⍮╻∕ⲯⱲ⾑⠾⾽⣟⽖⤫⸝≫⢯❿\u2FDAⵋ⧧⫎\u2FFDⱢ❄⟾ⅱ⾔☚▷⟲\u2EF9⟝⋞▦ⅸ\u242F┟⮩⣄≂⭘Ⅲ⏋▻Ⳏ⤐⭚ⳣ⻪Ⱙ⪗⦪⎙┉⃬⍳⾑⦍∭⤚⛶⿋™☒⡀⣐\u2FFF⤑⾔Ⓖ⇁⁂⧸⸼♏\u2E79\u20FD⛱\u20C3\u200E⠒⛆⪑⳪⫽ⅇ⟼⫍⏪▄⺵◓⥷⇜⼳┦⃚⫢"
                ),
                Arguments.of("5◓", "◓◓◓◓◓")
        );
    }

    @ParameterizedTest
    @MethodSource("DuplicatedStringIntoCompressedStringTestData")
    void convertDuplicatedStringIntoCompressedString(final String duplicatedString, final String compressedString) {
        final var stringProcessorService = new StringProcessorService();
        final var actualDuplicatedString = stringProcessorService.convertDuplicatedStringIntoCompressedString(duplicatedString);
        assertEquals(compressedString, actualDuplicatedString);
    }

    @ParameterizedTest
    @MethodSource("CompressedStringIntoDuplicatedStringTestData")
    void convertCompressedStringIntoDuplicatedString(final String compressedString, final String expectedDuplicatedString) {
        final var stringProcessorService = new StringProcessorService();
        final var actualDuplicatedString = stringProcessorService.convertCompressedStringIntoDuplicatedString(compressedString);
        assertEquals(expectedDuplicatedString, actualDuplicatedString);
    }
}

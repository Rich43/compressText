package service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.lang.Integer.parseInt;
import static java.util.Arrays.asList;
import static java.util.regex.Pattern.compile;

/**
 * Compresses and decompresses a string.
 */
public class StringProcessorService {
    /**
     * Converts duplicated string into compressed string.
     *
     * @param duplicatedString duplicated string
     * @return compressed string
     */
    public String convertDuplicatedStringIntoCompressedString(final String duplicatedString) {
        if (duplicatedString == null) {
            return null;
        }
        final List<String> listInputSplit = splitStringIntoList(duplicatedString);
        final var groups = getDuplicateStringGroups(listInputSplit);
        return compressGroupsIntoString(groups);
    }

    /**
     * Converts compressed string into duplicated string.
     *
     * @param compressedString compressed string
     * @return duplicated string
     */
    public String convertCompressedStringIntoDuplicatedString(final String compressedString) {
        if (compressedString == null) {
            return null;
        }
        final var resultString = new StringBuilder();
        final List<String> listInputSplit = splitStringIntoList(compressedString);
        var index = 0;
        while (index < listInputSplit.size()) {
            final var currentString = listInputSplit.get(index);
            final var nextString = getNextString(listInputSplit, index + 1);
            final var stringAfterNext = getNextString(listInputSplit, index + 2);
             if (nextString != null && nextString.equals("\\") && stringAfterNext.equals("\\")) {
                resultString.append(nextString.repeat(parseInt(currentString)));
                index += 3;
                continue;
            } else if (Character.isDigit(currentString.charAt(0)) && nextString != null && nextString.equals("\\") && Character.isDigit(stringAfterNext.charAt(0))) {
                resultString.append(stringAfterNext.repeat(parseInt(currentString)));
                index += 3;
                continue;
            } else if (Character.isDigit(currentString.charAt(0)) && nextString != null) {
                resultString.append(nextString.repeat(parseInt(currentString)));
                index += 2;
                continue;
            }
            resultString.append(currentString);
            index++;
        }
        return resultString.toString();
    }

    /**
     * Get next string in the list.
     *
     * @param listOfStrings list of strings
     * @param index         index of the string
     * @return string available at the index, null if it does not exist
     */
    private static String getNextString(final List<String> listOfStrings, int index) {
        String nextString = null;
        try {
            nextString = listOfStrings.get(index);
        } catch (IndexOutOfBoundsException ignored) {}
        return nextString;
    }

    /**
     * Splits string into list of strings.
     *
     * @param input string to split
     * @return list of strings
     */
    private static List<String> splitStringIntoList(String input) {
        final var inputSplit = input.split("");
        return new ArrayList<>(asList(inputSplit));
    }

    /**
     * Compress a list of strings into a single compressed string
     *
     * @param groups list of strings
     * @return compressed string
     */
    private static String compressGroupsIntoString(List<String> groups) {
        final var result = new StringBuilder();
        for (final var group : groups) {
            if (group.length() > 1) {
                if (compile("^\\d+$").matcher(group).matches()) {
                    result.append(group.length()).append("\\").append(group.charAt(0));
                } else if (compile("^\\\\+$").matcher(group).matches()) {
                    result.append(group.length()).append("\\\\");
                } else {
                    result.append(group.length()).append(group.charAt(0));
                }
            } else {
                result.append(group.charAt(0));
            }
        }
        return result.toString();
    }

    /**
     * Get a list of duplicated strings from a list of characters.
     *
     * @param listInputSplit List of characters
     * @return List of duplicated strings
     */
    private static List<String> getDuplicateStringGroups(final List<String> listInputSplit) {
        final var groups = new ArrayList<String>();
        Optional<String> leftOverString = listInputSplit.stream().reduce((a, b) -> {
            if (!a.endsWith(b)) {
                groups.add(a);
                return b;
            }
            return a + b;
        });
        leftOverString.ifPresent(groups::add);
        return groups;
    }
}

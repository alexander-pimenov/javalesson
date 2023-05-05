package ru.pimalex1978.utils.util.some;

/**
 * Handles whitespace characters for notes; eg tabs and new line.
 */
public class NoteTextHandler {

    private static final String NEW_LINE_CHARACTER_PATTERN = "\r\n|\n";
    private static final String NEW_LINE_CHARACTER_REPLACEMENT_VALUE = "<br>";
    private static final String TAB_CHARACTER_PATTERN = "\t";
    private static final String TAB_CHARACTER_REPLACEMENT_VALUE = "&emsp;";


    public static String handleWhitespaceCharacters(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }
        String result;
        result = handleNewLine(input);
        result = handleTabs(result);
        return result;
    }

    private static String handleNewLine(String input) {
        return input.replaceAll(NEW_LINE_CHARACTER_PATTERN, NEW_LINE_CHARACTER_REPLACEMENT_VALUE);
    }

    private static String handleTabs(String input) {
        return input.replaceAll(TAB_CHARACTER_PATTERN, TAB_CHARACTER_REPLACEMENT_VALUE);
    }
}

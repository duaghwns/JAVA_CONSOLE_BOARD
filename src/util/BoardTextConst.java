package util;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum BoardTextConst {
    BOARD_TITLE("title"),
    BOARD_NO("no"),
    BOARD_WRITER("writer"),
    BOARD_CONTENT("content"),
    BOARD_DATE("date");

    private final String label;

    BoardTextConst(String label){
        this.label = label;
    }

    public String label(){
        return label;
    }

    private static final Map<String, BoardTextConst> BY_LABEL =
            Stream.of(values()).collect(Collectors.toMap(BoardTextConst::label, Function.identity()));

    public static BoardTextConst valueOfLabel(String label){
        return BY_LABEL.get(label);
    }
}

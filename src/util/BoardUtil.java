package util;

public class BoardUtil {
    static final int boardTitleLength = 24;
    static final int boardNoLength = 3;
    static final int boardWriter = 8;
    static final int boardDate = 13;

    public static String sliceBoardText(String str, String type){
        switch (type){
            case "title":
                return returnOfStrType(str,boardTitleLength);
            case "no":
                return returnOfStrType(str,boardNoLength);
            case "writer":
                return returnOfStrType(str,boardWriter);
            case "date":
                return returnOfStrType(str,boardDate);
            default:
                throw new IllegalStateException("Unexpected value: " + type);
        }
    }

    private static String returnOfStrType(String str, int length){
        if(str.length() > length){
            return str.substring(0,length) + "..";
        } else {
            int spaceCount = length - str.length();
            for (int i = 0; i < spaceCount; i++) {
                str += " ";
            }
            return str + "  ";
        }
    }
}

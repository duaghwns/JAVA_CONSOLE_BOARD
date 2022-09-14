import controller.BoardController;

import java.io.IOException;

public class BoardStart {
    public static void main(String[] args) throws IOException {
        System.out.println("=======================JAVA CONSOLE BOARD=======================");
        new BoardController().mainView();
    }
}

import controller.BoardController;

import java.io.IOException;

public class BoardStart {
    public static void main(String[] args) throws IOException {
        BoardController board = new BoardController();
        System.out.println("=============================================");
        System.out.println("                   게시판                     ");
        System.out.println("=============================================");
        board.start();
    }
}

import controller.BoardController;

import java.io.IOException;

public class BoardStart {
    public static void main(String[] args) throws IOException {
        System.out.println("------------------------------");
        System.out.println("-------------게시판-------------");
        System.out.println("------------------------------");
        new BoardController().mainView();
    }
}

import controller.BoardController;
import db.DataBase;

import java.io.IOException;

public class BoardStart {
    public static void main(String[] args) throws IOException {
        BoardController bc = new BoardController();
        bc.mainView();
    }
}

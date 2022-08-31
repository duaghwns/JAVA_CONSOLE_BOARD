package dao;

import db.DataBase;
import vo.BoardVO;

public class BoardDAO {
    static BoardDAO instance;
    DataBase db = DataBase.getInstance();

    public static  BoardDAO getInstance() {
        if (instance == null) {
            instance = new BoardDAO();
        }
        return instance;
    }

    protected BoardDAO(){}


    public void insert(BoardVO board) {
        db.getBoardVO().add(board);
    }


}

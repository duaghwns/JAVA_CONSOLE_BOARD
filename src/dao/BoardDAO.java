package dao;

import db.DataBase;
import vo.BoardVO;

import java.util.List;

public class BoardDAO {
    private static BoardDAO instance;
    List<BoardVO> boardList = DataBase.getInstance().getBoardVO();

    public static  BoardDAO getInstance() {
        if (instance == null) {
            instance = new BoardDAO();
        }
        return instance;
    }

    protected BoardDAO(){}

    public void insert(BoardVO board) {
        boardList.add(board);
    }

    public int delete(BoardVO board) {
        if(boardList.remove(board)){
            return 1;
        }
        return 0;
    }

    public List<BoardVO> findAll() {
        return boardList;
    }
}

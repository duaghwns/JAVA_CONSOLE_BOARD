package service;

import dao.BoardDAO;
import db.DataBase;
import vo.BoardVO;

import java.time.LocalDateTime;

public class BoardService {
    DataBase db = DataBase.getInstance();

    public void insert(BoardVO board) {
        BoardDAO dao = BoardDAO.getInstance();
        long boardNo = db.getBoardVO().size() + 1;
        board.setNo(boardNo);
        board.setRegDate(LocalDateTime.now());
        dao.insert(board);


    }


}

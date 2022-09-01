package service;

import dao.BoardDAO;
import db.DataBase;
import vo.BoardVO;

import java.time.LocalDateTime;
import java.util.List;

public class BoardService {
    DataBase db = DataBase.getInstance();
    BoardDAO dao = BoardDAO.getInstance();

    public void insert(BoardVO board) {

        long boardNo = db.getBoardVO().get(db.getBoardVO().size()).getNo() + 1;

        if(db.getBoardVO().isEmpty()){
            board.setNo(1);
        } else {
            board.setNo(boardNo);
        }

        board.setRegDate(LocalDateTime.now());
        dao.insert(board);
    }

    public boolean delete(BoardVO board){

        if(dao.delete(board.getNo())==1){
            return true;
        }

        return false;
    }

    public BoardVO findBoard(long no){
        for (BoardVO board : dao.findAll()) {
            if (board.getNo() == no) {
                return board;
            }
        }
        return null;
    }

    public List<BoardVO> findAll(){
        return dao.findAll();
    }

}

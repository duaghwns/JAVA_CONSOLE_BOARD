package service;

import dao.BoardDAO;
import db.DataBase;
import vo.BoardVO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.List;

public class BoardService {
    BoardDAO dao = BoardDAO.getInstance();

    public void insert(BoardVO board) {
        long seq = getBoardSeq();

        board.setNo(++seq);
        setBoardSeq(seq);

        board.setRegDate(LocalDateTime.now().toLocalDate());

        dao.insert(board);
    }

    public boolean delete(long boardNo) {
        try {
            BoardVO board = findBoard(boardNo);
            if (dao.delete(board) == 1) {
                return true;
            }
            return false;

        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
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

    // user, seq 분리 x
    public String getUserName(){
        return dao.getUserName();
    }

    public void setUserName(String userName) {
        dao.setUserName(userName);
    }

    public long getBoardSeq(){
        return dao.getSeq();
    }

    public void setBoardSeq(long seq){
        dao.setSeq(seq);
    }

}

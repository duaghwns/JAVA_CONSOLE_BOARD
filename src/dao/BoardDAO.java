package dao;

import db.DataBase;
import vo.BoardVO;

import java.util.List;

public class BoardDAO {
    private static BoardDAO instance;
    List<BoardVO> boardList = DataBase.getInstance().getBoardVO();
    DataBase db = DataBase.getInstance();

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

    // 굳이 유저 DAO로 따로 분리 안함
    public String getUserName(){
        return db.getUserName();
    }

    public void setUserName(String userName) {
        db.setUserName(userName);
    }

    // 마찬가지
    public long getSeq() {
        return db.getBoardSeq();
    }

    public void setSeq(long seq){
        db.setBoardSeq(seq);
    }
}

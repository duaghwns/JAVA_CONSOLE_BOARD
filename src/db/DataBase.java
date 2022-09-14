package db;

import vo.BoardVO;

import java.util.ArrayList;
import java.util.List;

public class DataBase {
    private static DataBase instance;

    public static DataBase getInstance(){
        if (instance == null) {
            instance = new DataBase();
        }
        return instance;
    }

    protected DataBase(){}

    private List<BoardVO> boardVO = new ArrayList<>();
    private Long boardSeq;
    private String userName;

    public List<BoardVO> getBoardVO() {
        return boardVO;
    }

    public String getUserName() {
        return userName;
    }

    public Long getBoardSeq() {
        if(this.boardSeq == null){
            setBoardSeq(0);
        }
        return boardSeq;
    }

    public void setBoardSeq(long seq) { this.boardSeq = seq; }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
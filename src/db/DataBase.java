package db;

import vo.BoardVO;

import java.util.ArrayList;
import java.util.List;

public class DataBase {
    static DataBase instance;

    public static DataBase getInstance(){
        if (instance == null) {
            instance = new DataBase();
        }
        return instance;
    }

    protected DataBase(){
    }


    List<BoardVO> boardVO = new ArrayList<>();

    public List<BoardVO> getBoardVO() {
        return boardVO;
    }

    String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}

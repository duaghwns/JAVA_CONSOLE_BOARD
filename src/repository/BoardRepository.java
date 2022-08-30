package repository;

import entity.Board;

import java.util.List;

public class BoardRepository {
    private static BoardRepository instance = new BoardRepository();

    public static BoardRepository getInstance(){
        return instance;
    }

    private BoardRepository(){}

    public static List<Board> boardList;
}

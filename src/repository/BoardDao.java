package repository;

import entity.Board;

import java.util.List;

public class BoardDao implements BoardRepogitory{

    private static BoardRepogitory repogitory;

    public BoardDao() {

    }

    @Override
    public Board read(long id) {

        return null;
    }

    @Override
    public int insert(Board bd) {
        return 0;
    }

    @Override
    public int update(Board bd) {
        return 0;
    }

    @Override
    public List<Board> searchBoard() {
        return null;
    }
}

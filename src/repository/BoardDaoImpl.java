package repository;

import entity.Board;

import java.util.List;

public class BoardDaoImpl implements BoardDao {

    private static BoardDao repogitory;

    public BoardDaoImpl() {

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

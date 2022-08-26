package repository;

import entity.Board;

import java.util.List;

public interface BoardRepogitory {

    Board read(long id);

    int insert(Board bd);

    int update(Board bd);

    List<Board> searchBoard();
}

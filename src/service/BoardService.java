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
    DataBase db = DataBase.getInstance();
    BoardDAO dao = BoardDAO.getInstance();

    public void insert(BoardVO board) {
        if(db.getBoardVO().isEmpty()){
            board.setNo(1);
        } else {
            long max = 0;

            for(BoardVO bd : dao.findAll()){
                if(max < bd.getNo()){
                    max = bd.getNo();
                }
            }
            board.setNo(max+1);
        }

        board.setRegDate(LocalDateTime.now().toLocalDate());
        dao.insert(board);
    }

    public boolean delete(long boardNo) {
        try {
            BoardVO findedBoard = findBoard(boardNo);

            while (findedBoard != null) {
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                System.out.println("일치하는 게시글 번호가 없습니다 다시 입력해주세요.");
                boardNo = Long.parseLong(br.readLine());
                findedBoard = findBoard(boardNo);
                br.close();
            }

            if (dao.delete(findedBoard) == 1) {
                return true;
            }
            return false;
        } catch (IOException e) {
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

}

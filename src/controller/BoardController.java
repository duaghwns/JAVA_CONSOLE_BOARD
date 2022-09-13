package controller;

import db.DataBase;
import service.BoardService;
import util.BoardTextConst;
import vo.BoardVO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Optional;

import static util.BoardUtil.*;

public class BoardController {
    DataBase db = DataBase.getInstance();
    BoardService service = new BoardService();

    public void mainView() throws IOException {
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            int choiceNum = 4;
            setUserName(br);

            do {
                System.out.println("  번호  |\t\t\t 제목 \t\t\t| \t 작성자 \t | \t 작성일");

                for(BoardVO board : service.findAll()){
                    System.out.printf(" %s | %s | %s | %s \n"
                            , sliceBoardText(board.getNo()+"",BoardTextConst.BOARD_NO.label())
                            , sliceBoardText(board.getTitle(),BoardTextConst.BOARD_TITLE.label())
                            , sliceBoardText(board.getWriter(), BoardTextConst.BOARD_WRITER.label())
                            , sliceBoardText(board.getRegDate()+"", BoardTextConst.BOARD_DATE.label()));
                }

                System.out.println("\n1.등록 2.삭제 3.게시물보기 4.종료");

                String input = br.readLine();
                choiceNum = Integer.parseInt(input);
                choiceView(br, choiceNum);

            } while (choiceNum != 4);

            br.close();
        } catch (NumberFormatException nfe){
            System.out.println("숫자만 입력 가능합니다");
            mainView();
        }

    }
    void setUserName(BufferedReader br) throws IOException{
        if(db.getUserName() == null){
            System.out.print("유저 이름을 입력하세요 : ");
            db.setUserName(br.readLine());
        }
    }

    void choiceView(BufferedReader br, int input) throws IOException {
        switch (input){
            case 1:
                insert(br);
                break;
            case 2:
                delete(br);
                break;
            case 3:
                viewBoard(br);
                break;
            default:
                System.out.println("다시 입력해주세요");
        }
    }

    private void delete(BufferedReader br) throws IOException{
        try {
            System.out.print("삭제할 게시물 번호를 입력하세요 : ");

            if (service.delete(Long.parseLong(br.readLine()))) {
                System.out.println("삭제되었습니다.");
            }

        } catch (NumberFormatException numberFormatException) {
            System.out.println("숫자만 입력 가능합니다");
            delete(br);
        }
        
    }

    private void insert(BufferedReader br) throws IOException{
        try {
            BoardVO newBoard = new BoardVO();

            System.out.print("제목 : ");
            String title = br.readLine();
            System.out.println("내용을 입력하세요.");
            String content = br.readLine();

            newBoard.setTitle(title);
            newBoard.setContent(content);
            newBoard.setWriter(db.getUserName());

            service.insert(newBoard);
        } catch (NumberFormatException numberFormatException) {
            System.out.println("숫자만 입력 가능합니다");
            insert(br);
        }
    }

    private void viewBoard(BufferedReader br) throws IOException{
        try{
            System.out.println("보고싶은 게시글 번호를 입력하세요 : ");

            Long findBoardNo = Long.parseLong(br.readLine());

//            BoardVO board = service.findBoard(findBoardNo);

            Optional<BoardVO> board = Optional.ofNullable(service.findBoard(findBoardNo));

            System.out.println("------------------------------------------------");
            System.out.printf(" 제목 : %s\n",board.get().getTitle());
            System.out.println("------------------------------------------------");
            System.out.printf(" 작성자 : %s\n",board.get().getWriter());
            System.out.println("------------------------------------------------");
            System.out.printf(" 내용 : %s\n",board.get().getContent());
            System.out.println("------------------------------------------------");
            System.out.printf(" 작성일 : %s\n",board.get().getRegDate());
            System.out.println("------------------------------------------------");

        } catch (NumberFormatException numberFormatException){
            viewBoard(br);
        }
    }
}

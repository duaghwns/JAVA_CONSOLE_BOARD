package controller;

import db.DataBase;
import service.BoardService;
import util.BoardTextConst;
import vo.BoardVO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static util.BoardUtil.*;

public class BoardController {
    DataBase db = DataBase.getInstance();
    BoardService service = new BoardService();

    public void mainView() throws IOException {
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            // User 이름 입력받기
            if(db.getUserName() == null) {
                setUserName(br);
            }
            // 게시판 전체 목록 보여주기
            viewAllBoard(br);

            br.close();
        } catch (NumberFormatException nfe){
            System.out.println("숫자만 입력 가능합니다");
            mainView();
        }
    }

    private void viewAllBoard(BufferedReader br) throws IOException{
        try{
            int selectNumber;
            do {
                System.out.println("  번호  |\t\t\t 제목 \t\t\t| \t 작성자 \t | \t 작성일");

                for(BoardVO board : service.findAll()){
                    System.out.printf(" %s | %s | %s | %s \n"
                            , sliceBoardText(board.getNo()+"",BoardTextConst.BOARD_NO.label())
                            , sliceBoardText(board.getTitle(),BoardTextConst.BOARD_TITLE.label())
                            , sliceBoardText(board.getWriter(), BoardTextConst.BOARD_WRITER.label())
                            , sliceBoardText(board.getRegDate()+"", BoardTextConst.BOARD_DATE.label()));
                }

                System.out.println("\n1.등록 2.삭제 3.게시물보기 4.작성자변경 5.종료");
                selectNumber = Integer.parseInt(br.readLine());
                choiceView(br, selectNumber);

            } while (selectNumber != 5);
        } catch(NumberFormatException numberFormatException){
            System.out.println("숫자만 입력 가능합니다");
            viewAllBoard(br);
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
            case 4:
                setUserName(br);
                break;
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
            System.out.print("게시글 번호를 입력하세요 : ");

            BoardVO board = service.findBoard(Long.parseLong(br.readLine()));

            while (board == null) {
                System.out.println("찾을 수 없는 번호입니다.");
                System.out.print("다시 입력해주세요 : ");
                board = service.findBoard(Long.parseLong(br.readLine()));
            }

            if (board != null) {
                System.out.println("------------------------------------------------");
                System.out.printf(" 제목 : %s\n", board.getTitle());
                System.out.println("------------------------------------------------");
                System.out.printf(" 작성자 : %s\n", board.getWriter());
                System.out.println("------------------------------------------------");
                System.out.printf(" 내용 : %s\n", board.getContent());
                System.out.println("------------------------------------------------");
                System.out.printf(" 작성일 : %s\n", board.getRegDate());
                System.out.println("------------------------------------------------");
            }

        } catch (NumberFormatException numberFormatException){
            System.out.println("숫자만 입력 가능합니다.");
            viewBoard(br);
        }
    }

    void setUserName(BufferedReader br) throws IOException{
            System.out.print("유저 이름을 입력하세요 : ");
            db.setUserName(br.readLine());
    }
}

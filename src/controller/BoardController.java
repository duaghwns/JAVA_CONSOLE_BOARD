package controller;

import service.BoardService;
import util.BoardTextConst;
import vo.BoardVO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static util.BoardUtil.*;

public class BoardController {
    BoardService service = new BoardService();

    public void mainView() throws IOException {
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            if(service.getUserName() == null) {
                setUserName(br);
            }

            viewAllBoard(br);

            br.close();
        } catch (NumberFormatException nfe){
            System.out.println("숫자만 입력 가능합니다");
            mainView();
        }
    }

    /**
     *게시판 전체 목록 조회
     * @param br
     * @throws IOException
     */
    private void viewAllBoard(BufferedReader br) throws IOException{
        try{
            int selectNumber;

            do {
                System.out.println("  번호  |\t\t\t 제목 \t\t\t| \t 작성자 \t | \t 작성일");

                // 최신으로 생성된 게시물이 맨 위로 정렬되어 출력하기 위해 List 객체 복사
                List<BoardVO> boardList = new ArrayList<>(service.findAll());

                Collections.reverse(boardList);

                for(BoardVO board : boardList){
                    System.out.printf(" %s | %s | %s | %s \n"
                            , sliceBoardText(board.getNo()+"",BoardTextConst.BOARD_NO.label())
                            , sliceBoardText(board.getTitle(),BoardTextConst.BOARD_TITLE.label())
                            , sliceBoardText(board.getWriter(), BoardTextConst.BOARD_WRITER.label())
                            , sliceBoardText(board.getRegDate()+"", BoardTextConst.BOARD_DATE.label())
                    );
                }

                boardList.clear();

                System.out.println("\n1.등록 2.삭제 3.게시물보기 4.작성자변경 5.종료");
                selectNumber = Integer.parseInt(br.readLine());
                choiceView(br, selectNumber);

            } while (selectNumber != 5);
        } catch(NumberFormatException numberFormatException){
            System.out.println("숫자만 입력 가능합니다");
            viewAllBoard(br);
        }
    }

    /**
     * 전달받은 input 값으로 동작 수행
     * @param br BufferedReader
     * @param input int
     * @throws IOException
     */
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

    /**
     * 삭제 기능
     * @param br
     * @throws IOException
     */
    private void delete(BufferedReader br) throws IOException{
        try {
            System.out.print("삭제할 게시물 번호를 입력하세요 : ");

            if (service.delete(Long.parseLong(br.readLine()))) {
                System.out.println("삭제되었습니다.");
            } else {
                System.out.println("게시물을 찾을 수 없습니다.");
                delete(br);
            }
        } catch (NumberFormatException numberFormatException) {
            System.out.println("숫자만 입력 가능합니다");
            delete(br);
        }
        
    }

    /**
     * 게시물 생성
     * @param br
     * @throws IOException
     */
    private void insert(BufferedReader br) throws IOException{
        try {
            BoardVO newBoard = new BoardVO();

            System.out.print("제목 : ");
            String title = br.readLine();
            System.out.println("내용을 입력하세요.");
            String content = br.readLine();

            newBoard.setTitle(title);
            newBoard.setContent(content);
            newBoard.setWriter(service.getUserName());

            service.insert(newBoard);
        } catch (NumberFormatException numberFormatException) {
            System.out.println("숫자만 입력 가능합니다");
            insert(br);
        }
    }


    /**
     * 게시물 보기
     * @param br
     * @throws IOException
     */
    private void viewBoard(BufferedReader br) throws IOException{
        try{
            System.out.print("게시물 번호를 입력하세요 : ");

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

    /**
     * 유저 이름 설정
     * @param userName
     * @throws IOException
     */
    void setUserName(BufferedReader userName) throws IOException{
        System.out.print("유저 이름을 입력하세요 : ");
        service.setUserName(userName.readLine());
        System.out.printf("안녕하세요 %s님\n", service.getUserName());
    }
}

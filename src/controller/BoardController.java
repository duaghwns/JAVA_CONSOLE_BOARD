package controller;

import db.DataBase;
import service.BoardService;
import vo.BoardVO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class BoardController {
    DataBase db = DataBase.getInstance();
    BoardService service = new BoardService();

    public void mainView() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("------------------------------");
        System.out.println("-------------게시판-------------");
        System.out.println("------------------------------");

        if(db.getUserName() == null){
            System.out.print("유저 이름을 입력하세요 : ");
            db.setUserName(br.readLine());
        }
        int input = Integer.parseInt(br.readLine());

        do {
            System.out.println("1.등록 2.삭제 3.게시물보기");
            choiceView(input);
        } while (input!=3);

        br.close();
    }

    void choiceView(int input) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        switch (input){
            case 1:
                BoardVO board = new BoardVO();

                System.out.print("제목 : ");
                String title = br.readLine();
                System.out.println("내용을 입력하세요.");
                String content = br.readLine();

                board.setTitle(title);
                board.setContent(content);
                board.setWriter(db.getUserName());

                service.insert(board);
                break;

            case 2:
                System.out.print("삭제할 게시물 번호를 입력하세요 : ");
                long boardNo = Long.parseLong(br.readLine());
                BoardVO findedBoard;
                do{
                    System.out.println("일치하는 게시글 번호가 없습니다 다시 입력해주세요.");
                    boardNo = Long.parseLong(br.readLine());
                    findedBoard = service.findBoard(boardNo);

                } while(findedBoard != null);

                if(service.delete(findedBoard)) {
                    System.out.println("삭제되었습니다.");
                }
                break;

            case 3: break;
            default:
                System.out.println("다시 입력해주세요");
        }

        br.close();
    }




}

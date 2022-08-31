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

    public void mainView() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("------------------------------");
        System.out.println("-------------게시판-------------");
        System.out.println("------------------------------");

        if(db.getUserName() == null){
            System.out.print("유저 이름을 입력하세요 : ");
            db.setUserName(br.readLine());
        }

        br.close();
    }

    void choiceView() throws IOException {
        System.out.println("1.등록 2.삭제 3.게시물보기");

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int input = Integer.parseInt(br.readLine());

        switch (input){
            case 1:
                BoardService service = new BoardService();
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

            case 2: break;
            default:
                System.out.println("다시 입력해주세요");
        }

        br.close();
    }




}

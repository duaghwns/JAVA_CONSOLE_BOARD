package controller;

import entity.Board;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.Scanner;

public class BoardController {
    public void start() throws IOException{
        Scanner sc = new Scanner(System.in);
        int input = 0;

        do {
            System.out.println("1. 글쓰기 2. 게시물보기 3. 종료");
            input = sc.nextInt();
            switch (input){
                case 1: insert(); break;
                case 2: view(); break;
            }
        }
        while(input!=3);
        sc.close();
    }

    public void insert() throws IOException {
        Board board = new Board();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("제목 : ");
        String title = br.readLine();
        System.out.println("내용을 입력해주세요");
        String content = br.readLine();

        LocalDateTime now = LocalDateTime.now();
        board.setNo(0);
        board.setTitle(title);
        board.setContent(content);
        board.setRegDate(now);
        br.close();
    }

    public void update(){

    }

    public void delete(){

    }

    public void view() {

    }

    public void readAll() {

    }

}

package controller;

import entity.Board;
import entity.BoardNoSequence;
import repository.BoardDao;
import repository.BoardRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.Scanner;

public class BoardController {

    BoardRepository repository = BoardRepository.getInstance();
    BoardNoSequence sequence = BoardNoSequence.getInstance();

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
        try {
            Board board = new Board();
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("제목 : ");
            String title = br.readLine();
            System.out.println("내용을 입력해주세요");
            String content = br.readLine();


            board.setNo(sequence.getNo());
            board.setTitle(title);
            board.setContent(content);
            board.setRegDate(LocalDateTime.now());
            sequence.setNo(sequence.getNo()+1);
            repository.boardList.add(board);

            br.close();
        } catch (Exception e) {
            System.out.println(e);
        }
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

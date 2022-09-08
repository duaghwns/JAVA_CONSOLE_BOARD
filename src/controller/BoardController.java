package controller;

import db.DataBase;
import service.BoardService;
import vo.BoardVO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BoardController {
    DataBase db = DataBase.getInstance();
    BoardService service = new BoardService();

    public void mainView() {
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("------------------------------");
            System.out.println("-------------게시판-------------");
            System.out.println("------------------------------");
            int choiceNum=4;
            if(db.getUserName() == null){
                System.out.print("유저 이름을 입력하세요 : ");
                db.setUserName(br.readLine());
            }


            do {
                System.out.println("번호 | \t\t 제목 \t\t | \t 작성자 \t| \t 작성일");

                for(BoardVO board : service.findAll()){
                    System.out.printf("| %s | %s | %s | %s \n", board.getNo(), board.getTitle(), board.getWriter(), board.getRegDate());
                }

                System.out.println("\n1.등록 2.삭제 3.게시물보기 4.종료");

                String input = br.readLine();


                choiceNum = Integer.parseInt(input);
                choiceView(choiceNum);
            } while (choiceNum != 4);

            br.close();
        } catch(IOException ioe){
            System.out.println(ioe);
        } catch (NumberFormatException nfe){
            System.out.println(nfe);
        }

    }

    void choiceView(int input) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        switch (input){
            case 1:
                BoardVO newBoard = new BoardVO();

                System.out.print("제목 : ");
                String title = br.readLine();
                System.out.println("내용을 입력하세요.");
                String content = br.readLine();

                newBoard.setTitle(title);
                newBoard.setContent(content);
                newBoard.setWriter(db.getUserName());

                service.insert(newBoard);
                break;

            case 2:
                System.out.print("삭제할 게시물 번호를 입력하세요 : ");
                if(service.delete(Long.parseLong(br.readLine()))) {
                    System.out.println("삭제되었습니다.");
                } else{
                    System.out.print("다시 입력해주세요 : ");


                }

                break;

            case 3:
                System.out.println("보고싶은 게시글 번호를 입력하세요 : ");

                BoardVO board = service.findBoard(Long.parseLong(br.readLine()));
                System.out.println("------------------------------------------------");
                System.out.printf("| 제목 : %s\n",board.getTitle());
                System.out.println("------------------------------------------------");
                System.out.printf("| 작성자 : %s\n",board.getWriter());
                System.out.println("------------------------------------------------");
                System.out.printf("| 내용 : %s\n",board.getContent());
                System.out.println("------------------------------------------------");
                System.out.printf("| 작성일 : %s\n",board.getRegDate());
                System.out.println("------------------------------------------------");
                break;
            default:
                System.out.println("다시 입력해주세요");
        }
    }




}

import controller.BoardController;

public class BoardStart {
    public static void main(String[] args) {
        System.out.println("------------------------------");
        System.out.println("-------------게시판-------------");
        System.out.println("------------------------------");
        new BoardController().mainView();
    }
}

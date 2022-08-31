package vo;

import java.time.LocalDateTime;

public class BoardVO {
    long no;
    String title;
    String writer;
    String content;
    LocalDateTime regDate;

    public BoardVO(){}

    public BoardVO(long no, String title, String writer, String content, LocalDateTime regDate) {
        this.no = no;
        this.title = title;
        this.writer = writer;
        this.content = content;
        this.regDate = regDate;
    }
    @Override
    public String toString() {
        return "BoardVO{" +
                "no=" + no +
                ", title='" + title + '\'' +
                ", writer='" + writer + '\'' +
                ", content='" + content + '\'' +
                ", regDate=" + regDate +
                '}';
    }

    public long getNo() {
        return no;
    }

    public void setNo(long no) {
        this.no = no;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getRegDate() {
        return regDate;
    }

    public void setRegDate(LocalDateTime regDate) {
        this.regDate = regDate;
    }
}

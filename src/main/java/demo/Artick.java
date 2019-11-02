package demo;

public class Artick {
    private String title;
    private String content;
    private String author;
    private String time;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String  getTime() {
        return time;
    }

    public void setTime(String  time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Artick{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", author='" + author + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}

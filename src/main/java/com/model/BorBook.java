package LibMangeSystem.src.main.java.com.model;

public class BorBook {
    private int bookId;
    private int stuId;

    public void setBorBook(int bookId, int stuId) {
        this.bookId = bookId;
        this.stuId = stuId;
    }

    public int getBookId() {
        return this.bookId;
    }

    public int getStuId() {
        return this.stuId;
    }
}

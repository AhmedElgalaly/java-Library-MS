public class Book {
    private String title;
    private String author;
    private int year;
    private int pages;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }
    public Book(String title, String author, int year) {
        this(title, author); // this calls the constructor with 2 parameters (title and author)
        this.year = year;
    }
    public Book(String title, String author, int year, int pages) {
        this(title, author, year); // this calls the constructor with 3 parameters (title, author and year)
        this.pages = pages;
    }


    // Getters and Setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    // toString
    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", year=" + year +
                ", pages=" + pages +
                '}';
    }

}

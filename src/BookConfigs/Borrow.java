package BookConfigs;

import java.time.LocalDate;

// We used LocalDate to store the date of borrowing and returning the book
// The late fine is calculated based on how late the book is returned

public class Borrow {
    // Attributes of the Borrowed Books
    private Book book;
    private LocalDate borrowDate;
    private LocalDate returnDate;
    private Double lateFee;
    private Boolean isReturned;

    // Default constructor
    public Borrow() {
        this.book = new Book();
        this.borrowDate = LocalDate.now();
        this.returnDate = LocalDate.now();
        this.lateFee = 0.0;
        this.isReturned = false;
    }

    // Parameterized constructor
    public Borrow(Book book) {
        this.book = book;
        this.borrowDate = LocalDate.now();
        this.returnDate = LocalDate.now().plusDays(7);
        this.lateFee = 0.0;
        this.isReturned = false;
    }
    public Borrow(Book book, LocalDate borrowDate) {
        this.book = book;
        this.borrowDate = borrowDate;
        this.returnDate = borrowDate.plusDays(7);
    }
    public Borrow(Book book, LocalDate borrowDate, LocalDate returnDate) {
        this(book, borrowDate);
        this.returnDate = returnDate;
        this.isReturned = false;
    }
    public Borrow(Book book, LocalDate borrowDate, LocalDate returnDate, Double lateFee) {
        this(book, borrowDate, returnDate);
        this.lateFee = lateFee;
        this.isReturned = false;
    }

    // Getters

    public Book getBook() {
        return this.book;
    }

    public LocalDate getBorrowDate() {
        return this.borrowDate;
    }

    public LocalDate getReturnDate() {
        return this.returnDate;
    }

    public Double getLateFee() {
        calculateLateFee();
        return this.lateFee;
    }

    public Boolean getIsReturned() {
        return this.isReturned;
    }

    // Setters
    public void setBook(Book book) {
        this.book = book;
    }

    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public void setLateFee(Double lateFee) {
        this.lateFee = lateFee;
    }

    public void setIsReturned(Boolean isReturned) {
        this.isReturned = isReturned;
    }

    // Method to calculate the late fee for function getLateFee
    public void calculateLateFee() {
        LocalDate currentDate = LocalDate.now();
        if (this.returnDate.isAfter(currentDate)) {
            lateFee = 0.0;
        } else {
            long days = this.returnDate.toEpochDay() - currentDate.toEpochDay();
            lateFee = days * 0.5;
        }
    }

    @Override
    public String toString() {
        return book.toString() + String.format(
                """

                        Borrow Date: %s
                        Late fees: %f""",
                this.borrowDate.toString(),
                this.lateFee
        );
    }
}

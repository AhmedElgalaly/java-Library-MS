package BookConfigs;

import java.time.LocalDate;

// Class representing a borrowed book
// Uses LocalDate to store borrowing and returning dates
// Late fee is calculated based on how late the book is returned

public class Borrow {
    // Attributes of the Borrowed Books
    private Book book;
    private LocalDate borrowDate;
    private LocalDate returnDate;
    private Double lateFee;
    private Boolean isReturned;

    // Default constructor
    public Borrow() {
        // Initialize with default values
        this.book = new Book();
        this.borrowDate = LocalDate.now();
        this.returnDate = LocalDate.now();
        this.lateFee = 0.0;
        this.isReturned = false;
    }

    // Parameterized constructor
    public Borrow(Book book) {
        // Initialize with default borrow date and 7 days return date
        this.book = book;
        this.borrowDate = LocalDate.now();
        this.returnDate = LocalDate.now().plusDays(7);
        this.lateFee = 0.0;
        this.isReturned = false;
    }

    // Parameterized constructor with provided borrow date
    public Borrow(Book book, LocalDate borrowDate) {
        // Initialize with provided borrow date and 7 days return date
        this.book = book;
        this.borrowDate = borrowDate;
        this.returnDate = borrowDate.plusDays(7);
        this.lateFee = 0.0;
        this.isReturned = false;
    }

    // Parameterized constructor with provided borrow and return dates
    public Borrow(Book book, LocalDate borrowDate, LocalDate returnDate) {
        // Initialize with provided borrow and return dates
        this(book, borrowDate); // Call parameterized constructor with borrow date (line 52
        this.returnDate = returnDate;
        this.lateFee = 0.0;
        this.isReturned = false;
    }

    // Parameterized constructor with provided borrow, return dates, and late fee
    public Borrow(Book book, LocalDate borrowDate, LocalDate returnDate, Double lateFee) {
        // Initialize with provided borrow, return dates, and late fee
        this(book, borrowDate, returnDate); // Call parameterized constructor with borrow and return dates
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
        // Calculate late fee and return
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


    // Method to calculate the late fee for getLateFee method
    public void calculateLateFee() {

        LocalDate currentDate = LocalDate.now();

        if (this.returnDate.isAfter(currentDate)) {

            // No late fee if returned on time
            lateFee = 0.0;
        } else {

            // Calculate late fee based on days overdue
            long days = this.returnDate.toEpochDay() - currentDate.toEpochDay();
            lateFee = days * 0.5; // $0.5 late fee per day
        }
    }

    // Override toString method
    @Override
    public String toString() {
        // Return string representation of borrowed book details including borrow date and late fee
        return book.toString() + String.format(
                """

                        Borrow Date: %s
                        Late fees: %f""",
                this.borrowDate.toString(),
                this.lateFee
        );
    }
}

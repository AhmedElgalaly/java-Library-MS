package UsersInfo;
import java.time.LocalDate;

abstract public class User{
    protected String username;
    protected String email;
    protected String password;
    protected String phone;
    protected String address;
    protected LocalDate dateOfBirth;
    protected char gender;


    public User(String username, String email, String password, String phone, String address, LocalDate dateOfBirth, char gender) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
    }

    public User() {
        this.username = null;
        this.email = null;
        this.password = null;
        this.phone = null;
        this.address = null;
        this.dateOfBirth = null;
        this.gender = 0;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public void displayInfo(){
        System.out.println("Username: " + username);
        System.out.println("Email: " + email);
        System.out.println("Phone: " + phone);
        System.out.println("Address: " + address);
        System.out.println("Date of Birth: " + dateOfBirth);
        System.out.println("Gender: " + gender);
    }

}

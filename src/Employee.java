
public class Employee {
    // Final fields are initialized only once, either via an initializer or an assignment statement.
    private final String name;
    private final String position;
    private final int year;
    // Constructors
    public Employee(String name, String position, int year) {
        this.name = name;
        this.position = position;
        this.year = year;
    }

    public Employee(String name, String position) {
        this(name, position, 0); // this calls the constructor with 3 parameters (name, position and year)
    }

    public Employee(String name) {
        this(name, "Default"); // this calls the constructor with 2 parameters (name and position)
    }
    // Getters
    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    public int getYear() {
        return year;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", position='" + position + '\'' +
                ", year=" + year +
                '}';
    }
}

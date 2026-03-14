package Cap.Spring_Test_1;



public class Employee {

    private int id;
    private String name;
    private String email;
    private String password;
    private int age;
    private double salary;
    private Address address;

    public Employee(Address address) {
        this.address = address;
    }

    public Employee() {}

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public void setPassword(String password) { this.password = password; }

    public void setAge(int age) { this.age = age; }

    public void setSalary(double salary) { this.salary = salary; }

    public int getAge() { return age; }

    public double getSalary() { return salary; }

    public Address getAddress() { return address; }

}

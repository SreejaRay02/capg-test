package Cap.Spring_Test_1;

public class EmployeeServiceImpl implements EmployeeService {

    private Employee employee;

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public void addEmployee() {
        System.out.println("Employee Added");
        print();
    }

    public void updateEmployee() {
        System.out.println("Employee Updated");
        print();
    }

    public void deleteEmployee() {
        System.out.println("Employee Deleted");
    }

    public void getEmployee() {
        System.out.println("Employee Details:");
        print();
    }

    private void print() {

        Address a = employee.getAddress();

        System.out.println("Employee ID: " + employee.getId());
        System.out.println("Name: " + employee.getName());
        System.out.println("Email: " + employee.getEmail());
        System.out.println("Age: " + employee.getAge());
        System.out.println("Salary: " + employee.getSalary());

        System.out.println("City: " + a.getCity());
        System.out.println("State: " + a.getState());
        System.out.println("Country: " + a.getCountry());
        System.out.println("Pincode: " + a.getPincode());
    }

}

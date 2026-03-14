package Cap.Spring_Test_1;



import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestApp {

    public static void main(String[] args) {

        ApplicationContext context =
                new ClassPathXmlApplicationContext("applicationContext.xml");

        EmployeeService service =
                (EmployeeService) context.getBean("empService");

        service.getEmployee();

    }

}

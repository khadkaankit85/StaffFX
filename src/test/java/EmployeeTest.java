import com.example.stafffx.Model.Employee;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmployeeTest {
    @Test
    public void testYearlySalary() {
        Employee employee=new Employee();
        double monthlySalary=50;
        employee.setAmount(monthlySalary);

        double yearlySalary=employee.getYearlySalary();
        double expectedSalary=monthlySalary*12;

        assertEquals(expectedSalary,yearlySalary,0.01,"The yearly salary amount is incorrect");

    }
}

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.stafffx.Controller.SignupController;

public class FormValidationTest {

    @Test
    public  void formValidatorTest(){
        SignupController signupController = new SignupController();

        String name = "admin";
        String email = "admin@admin.com";
        String password = "admin";
        String password2 = "admin";

        boolean expectedValidation=false;

        assertEquals(expectedValidation,signupController.formDataIsValid(name,email,password,password2),"Form data is invalid when "+ "name is "+name+" and email is "+email+" and password is "+password+" and password2 is "+password2 );

        name = "ankit";
        email = "ankit";
        password = "password!";
        password2 = "password!";
        expectedValidation=false;
        Assertions.assertEquals(expectedValidation, signupController.formDataIsValid(name, email, password, password2),
                "Form data should be valid when name is " + name + " and email is " + email + " and password is " + password + " and password2 is " + password2);
    }

}

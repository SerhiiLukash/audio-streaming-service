package web.app.api.tests;

import org.junit.jupiter.api.Test;
import web.app.api.objects.User;
import web.app.api.utils.UserService;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserCreationTest {
    @Test
    public void testGetStudentWithId () {

        UserService userService = new UserService();

        User user = userService.getUserById(31);

        assertEquals("testFirstName", user.getFirstName());
    }

    @Test
    public void testCreateUser() {
        String endpoint = "http://localhost:8080/api/users";
        User body = new User("testFirstName", "testLastName", "test_email@gmail.com");
        given().
                header("Content-Type", "application/json").
                body(body).
                when().
                post(endpoint).
                then().
                assertThat().
                statusCode(200).
                body("firstName", equalTo("testFirstName"));
    }

}

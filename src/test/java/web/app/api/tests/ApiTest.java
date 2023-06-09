package web.app.api.tests;

import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.Test;
import web.app.api.objects.User;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ApiTest {

    @Test
    public void testGetStudents() {
        String endpoint = "http://localhost:8080/students";
        given().when().get(endpoint).then().assertThat().statusCode(200);
    }

    @Test
    public void testGetStudentsWithId() {
        String endpoint = "http://localhost:8080/students";
        given().
                queryParam("id", 1).
                when().
                get(endpoint).
                then().
                assertThat().
                statusCode(200).
                body("id", equalTo(1)).
                body("firstName", equalTo("James")).
                body("lastName", equalTo("Smith")).
                body("email", equalTo("james_smith@anywhere.school"));
    }

    @Test
    public void testCreateStudent() {
        String endpoint = "http://localhost:8080/students";
        User body = new User("Sergio", "Teacher", "sergio_teacher@anywhere.school");
        given().
                header("Content-Type", "application/json").
                body(body).
                when().
                post(endpoint).
                then().
                assertThat().
                statusCode(200).
                body("firstName", equalTo("Sergio")).
                body("lastName", equalTo("Teacher")).
                body("email", equalTo("sergio_teacher@anywhere.school"));
    }


    @Test
    public void testDeleteStudent() {
        String emdpoint = "http://localhost:8080/students/{id}";
        int id = 5;
        given().
                when().
                delete(emdpoint, id).
                then().
                assertThat().
                statusCode(200).
                body("id", equalTo(id));
    }
    @Test
    public void testUpdateStudent() {
        String emdpoint = "http://localhost:8080/students/{id}";
        int id = 5;
        User body = new User("Sergio", "Student", "sergio_student@anywhere.school");
        given().
                header("Content-Type", "application/json").
                body(body).
                when().
                put(emdpoint, id).
                then().
                assertThat().
                statusCode(200).
                body("firstName", equalTo("Sergio")).
                body("lastName", equalTo("Student")).
                body("email", equalTo("sergio_student@anywhere.school"));
    }
}

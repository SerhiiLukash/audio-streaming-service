package web.app.api.utils;

import io.restassured.response.Response;
import web.app.api.objects.User;

import static io.restassured.RestAssured.given;

public class UserService {
    private String endpoint = "http://localhost:8080/api/users/{id}";

    public User getUserById(int id) {

        Response response = given()
                .pathParam("id", id)
                .when()
                .get(endpoint);

        User user = response.as(User.class);

        return user;
    }
}

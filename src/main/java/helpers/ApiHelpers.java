package helpers;

import config.App;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class ApiHelpers {

    private static String authToken;
    private static String refreshToken;

    public static void setAuthToken(String authToken) {
        ApiHelpers.authToken = authToken;
    }

    public static String getAuthToken() {
        return authToken;
    }

    public static String getRefreshToken() {
        return refreshToken;
    }

    public static void setRefreshToken(String refreshToken) {
        ApiHelpers.refreshToken = refreshToken;
    }

    @Step("Регистрация тестового пользователя через API")
    public static void registerUser(HashMap<String, String> userInfo) {
        ValidatableResponse resp = given().
                contentType(ContentType.JSON).
                body(userInfo).
                when().
                post(App.BASE_URL + "/api/auth/register").
                then();

        setAuthToken(resp.extract().path("accessToken"));
        setRefreshToken(resp.extract().path("refreshToken"));
    }

    @Step("Удаление тестового пользователя через API")
    public static void deleteUser(String authToken) {
        given().
                contentType(ContentType.JSON).
                header("authorization", authToken).
                when().
                delete(App.BASE_URL + "/api/auth/user").
                then();

        setAuthToken("");
        setRefreshToken("");
    }




}

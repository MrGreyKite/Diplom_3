package helpers;

import com.github.javafaker.Faker;

import java.util.HashMap;

public class UserInfoConstructor {
    Faker faker = new Faker();

    public HashMap<String, String> constructUser() {
        HashMap<String, String> userInfo = new HashMap<>();
        userInfo.put("email", faker.internet().emailAddress());
        userInfo.put("password", faker.internet().password());
        userInfo.put("name", faker.name().username());
        return userInfo;
    }

}

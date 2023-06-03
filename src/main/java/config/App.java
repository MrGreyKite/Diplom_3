package config;

public class App {
    private static final String BASE_URL = "https://stellarburgers.nomoreparties.site";

    private static final String CONSTRUCTOR_URL = BASE_URL + "/";
    private static final String LOGIN_URL = BASE_URL + "/login";
    private static final String REGISTRATION_URL = BASE_URL + "/register";
    private static final String PROFILE_URL = BASE_URL + "/account";
    private static final String RESTORATION_URL = BASE_URL + "/forgot-password";

    public static String getBaseUrl() {
        return BASE_URL;
    }

    public static String getConstructorUrl() {
        return CONSTRUCTOR_URL;
    }

    public static String getLoginUrl() {
        return LOGIN_URL;
    }

    public static String getRegistrationUrl() {
        return REGISTRATION_URL;
    }

    public static String getProfileUrl() {
        return PROFILE_URL;
    }

    public static String getRestorationUrl() {
        return RESTORATION_URL;
    }

}

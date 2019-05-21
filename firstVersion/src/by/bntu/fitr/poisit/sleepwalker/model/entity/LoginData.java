package by.bntu.fitr.poisit.sleepwalker.model.entity;

import by.bntu.fitr.poisit.sleepwalker.util.JsonWorker;

import java.io.FileNotFoundException;
import java.io.IOException;

public class LoginData {

//    public final static String DEFAULT_DATA = "admin";

    private static LoginData loginData;

    private String login;
    private String password;

    private LoginData(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public static LoginData getInstance() throws FileNotFoundException {
        if (loginData == null) {
            loginData = JsonWorker.readToLoginData();
        }
        return loginData;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) throws IOException {
        this.login = login;
        JsonWorker.write(loginData);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) throws IOException {
        this.password = password;
        JsonWorker.write(loginData);
    }
}

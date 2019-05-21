package by.bntu.fitr.poisit.sleepwalker.model.entity;

import by.bntu.fitr.poisit.sleepwalker.util.JsonWorker;

import java.io.FileNotFoundException;
import java.io.IOException;

public class LoginData {

    public final static String PATH_TO_FILE_OF_LOGIN_DATA
            = "d:\\BNTU\\OOP\\kursa4\\firstVersion\\loginData.json";

    private static LoginData loginData;

    private String login;
    private String password;

    private LoginData(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public static LoginData getInstance() throws FileNotFoundException {
        if (loginData == null) {
            loginData = JsonWorker.readToLoginData(PATH_TO_FILE_OF_LOGIN_DATA);
        }
        return loginData;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) throws IOException {
        this.login = login;
        JsonWorker.write(loginData, PATH_TO_FILE_OF_LOGIN_DATA);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) throws IOException {
        this.password = password;
        JsonWorker.write(loginData, PATH_TO_FILE_OF_LOGIN_DATA);
    }

    public void setLoginAndPassword(String login, String password) throws IOException {
        setLogin(login);
        setPassword(password);
    }
}

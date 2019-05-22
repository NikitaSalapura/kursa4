package by.bntu.fitr.poisit.sleepwalker.util;

import by.bntu.fitr.poisit.sleepwalker.controller.GoodCreator;
import by.bntu.fitr.poisit.sleepwalker.controller.Login;
import by.bntu.fitr.poisit.sleepwalker.model.entity.*;
import org.junit.jupiter.api.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class JsonWorkerTest {

    static String login;
    static String password;
    final static String PATH;

    static {
        try {
            login = LoginData.getInstance().getLogin();
            password = LoginData.getInstance().getPassword();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        PATH = GoodContainer.PATH_TO_FILE_OF_GOOD_CONTAINER;
        GoodContainer.PATH_TO_FILE_OF_GOOD_CONTAINER
                = "d:\\BNTU\\OOP\\kursa4\\firstVersion\\testContainer.json";
    }

    @AfterAll
    static void setLogDataPrevious() throws Exception {
        LoginData.getInstance().setPassword(password);
        LoginData.getInstance().setLogin(login);
        GoodContainer.PATH_TO_FILE_OF_GOOD_CONTAINER = PATH;
    }

    @Test
    void readToLoginDataPass() throws Exception {
        String expected = "qq";
        LoginData.getInstance().setPassword("qq");
        String result = LoginData.getInstance().getPassword();
        assertEquals(expected, result);
    }

    @Test
    void readToLoginDataLoginAnotherValues() throws Exception {
        String expected = "login2";
        LoginData.getInstance().setLogin("login2");
        String result = LoginData.getInstance().getLogin();
        assertEquals(expected, result);
    }


    @Test
    void writeAndReadGoodContainerEmpty() throws Exception {
        GoodContainer expected = new GoodContainer();
        expected.saveInJson();
        GoodContainer result = JsonWorker
                .readToGoodContainer(GoodContainer.PATH_TO_FILE_OF_GOOD_CONTAINER);
        assertEquals(expected, result);
    }

    @Test
    void writeAndReadGoodContainerAll() throws Exception {
        GoodContainer expected = new GoodContainer();
        expected.saveInJson();
        GoodContainer result = JsonWorker
                .readToGoodContainer(GoodContainer.PATH_TO_FILE_OF_GOOD_CONTAINER);
        assertEquals(expected.getAll(), result.getAll());
    }

    @Test
    void writeContainer() throws Exception {
        GoodContainer expected = new GoodContainer();
        JsonWorker.write(expected, GoodContainer.PATH_TO_FILE_OF_GOOD_CONTAINER);
        GoodContainer result = JsonWorker.readToGoodContainer
                (GoodContainer.PATH_TO_FILE_OF_GOOD_CONTAINER);
        assertEquals(expected, result);
    }
}
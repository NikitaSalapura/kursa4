package by.bntu.fitr.poisit.sleepwalker.util;

import by.bntu.fitr.poisit.sleepwalker.model.entity.GoodContainer;
import by.bntu.fitr.poisit.sleepwalker.model.entity.LoginData;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class JsonWorker {

    private final static String PATH_TO_FILE_OF_GOOD_CONTAINER
            //= "d:\\BNTU\\OOP\\kursa4\\firstVersion\\catalogItems.json";
            = "./catalogItems.json";

    private final static String PATH_TO_FILE_OF_LOGIN_DATA
            //= "d:\\BNTU\\OOP\\kursa4\\firstVersion\\loginData.json";
            = "./loginData.json";

    public static LoginData readToLoginData() throws FileNotFoundException {
        JsonReader reader = new JsonReader(new FileReader(PATH_TO_FILE_OF_LOGIN_DATA));
        return new Gson().fromJson(reader, LoginData.class);
    }

    public static GoodContainer readToGoodContainer() throws FileNotFoundException {
        JsonReader reader = new JsonReader(new FileReader(PATH_TO_FILE_OF_GOOD_CONTAINER));
        return new Gson().fromJson(reader, GoodContainer.class);
    }

    public static void write(GoodContainer context) throws IOException {
        Gson gson = new Gson();
        FileWriter writer = new FileWriter(PATH_TO_FILE_OF_GOOD_CONTAINER);
        String string = gson.toJson(context);
        writer.write(string);
        writer.close();
    }

    public static void write(LoginData loginData) throws IOException {
        Gson gson = new Gson();
        FileWriter writer = new FileWriter(PATH_TO_FILE_OF_LOGIN_DATA);
        String string = gson.toJson(loginData);
        writer.write(string);
        writer.close();
    }
}

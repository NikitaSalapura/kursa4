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

    public static LoginData readToLoginData(String path) throws FileNotFoundException {
        JsonReader reader = new JsonReader(new FileReader(path));
        return new Gson().fromJson(reader, LoginData.class);
    }

    public static GoodContainer readToGoodContainer(String path) throws FileNotFoundException {
        JsonReader reader = new JsonReader(new FileReader(path));
        return new Gson().fromJson(reader, GoodContainer.class);
    }

    public static void write(Object object, String path) throws IOException {
        Gson gson = new Gson();
        FileWriter writer = new FileWriter(path);
        String string = gson.toJson(object);
        writer.write(string);
        writer.close();
    }
}

package by.bntu.fitr.poisit.sleepwalker.util;

import by.bntu.fitr.poisit.sleepwalker.model.entity.GoodContext;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class JsonWorker {

    private final static String PATH_TO_FILE
            = "d:\\BNTU\\OOP\\kursa4\\firstVersion\\catalogItems.json";

    public static GoodContext read() throws FileNotFoundException {
        JsonReader reader = new JsonReader(new FileReader(PATH_TO_FILE));
        return new Gson().fromJson(reader, GoodContext.class);
    }

    public static void write(GoodContext context) throws IOException {
        Gson gson = new Gson();
        FileWriter writer = new FileWriter(PATH_TO_FILE);
        String string = gson.toJson(context);
        writer.write(string);
        writer.close();
    }
}

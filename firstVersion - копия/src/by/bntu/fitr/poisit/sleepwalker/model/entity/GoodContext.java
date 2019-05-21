package by.bntu.fitr.poisit.sleepwalker.model.entity;

import by.bntu.fitr.poisit.sleepwalker.util.JsonWorker;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GoodContext {
    private List<Suit> suitList;
    private List<Footwear> footwearList;

    public GoodContext() {
        suitList = new ArrayList<>();
        footwearList = new ArrayList<>();
    }

    public void saveInJson() throws IOException {
        JsonWorker.write(this);
    }
}

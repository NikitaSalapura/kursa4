package by.bntu.fitr.poisit.sleepwalker.model.entity;


import javafx.beans.property.SimpleStringProperty;

public class Footwear extends WorkingWear {

    public static final String DEFAULT_SOLE = "hard";

    protected SimpleStringProperty sole;

    public Footwear() {
        initDefault();
    }


    public Footwear(double price) {
        super(price);
        initFields(DEFAULT_SOLE);
    }

    public Footwear(String sole) {
        super.initDefault();
        initFields(sole);
    }

    public Footwear(String color,
                    String brand, String category, String material, String sole) {
        super(color, brand, category, material);
        initFields(sole);
    }

    public Footwear(double price, String color,
                    String brand, String category, String material, String sole) {
        super(price, color, brand, category, material);
        initFields(sole);
    }

    public Footwear(Footwear footwear) {
        if (checkForNonNull(footwear)) {
            copyFields(footwear);
        } else {
            initDefault();
        }
    }

    public String getSole() {
        return sole.get();
    }

    public void setSole(String sole) {
        this.sole.set(sole);
    }

    private boolean checkForNonNull(Footwear footwear) {
        return footwear != null;
    }

    protected void initDefault() {
        super.initDefault();
        sole = new SimpleStringProperty(DEFAULT_SOLE);
    }

    private void initFields(String sole) {
        this.sole = new SimpleStringProperty(sole);
    }

    protected void copyFields(Footwear footwear){
        super.copyFields(footwear);
        initFields(String.valueOf(footwear.sole));
    }

    @Override
    public String toString() {
        return "Footwear{" +
                super.toString() +
                "sole='" + sole + '\'' +
                '}';
    }
}


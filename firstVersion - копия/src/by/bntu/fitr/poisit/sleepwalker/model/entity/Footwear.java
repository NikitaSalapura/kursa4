package by.bntu.fitr.poisit.sleepwalker.model.entity;


public class Footwear extends WorkingWear {

    public static final String DEFAULT_SOLE = "undefined";

    protected String sole;

    public Footwear() {
        initDefault();
    }


    public Footwear(double price) {
        super(price);
    }

    public Footwear(String sole) {
        super.initDefault();
        initFields(sole);
    }

    public Footwear(int size, String color,
                    String brand, String category, String material, String sole) {
        super(size, color, brand, category, material);
        initFields(sole);
    }

    public Footwear(double price, int size, String color,
                    String brand, String category, String material, String sole) {
        super(price, size, color, brand, category, material);
        initFields(sole);
    }

    public Footwear(Footwear footwear) {
        if (checkForNonNull(footwear)) {
            copyWorkingWear(footwear);
        } else {
            initDefault();
        }
    }

    public String getSole() {
        return sole;
    }

    public void setSole(String sole) {
        this.sole = sole;
    }

    private boolean checkForNonNull(Footwear footwear) {
        return footwear != null;
    }

    protected void initDefault() {
        super.initDefault();
        sole = DEFAULT_SOLE;
    }

    private void initFields(String sole) {
        this.sole = sole;
    }

    @Override
    public String toString() {
        return "Footwear{" +
                super.toString() +
                "sole='" + sole + '\'' +
                '}';
    }
}


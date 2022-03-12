import java.util.ArrayList;

public class Product {
    private static int cnt = 0;
    private int id; // unique for each product
    private String name;
    private float price;
    private ArrayList<Integer> ratings; // default empty

    public int getId() {
        return id;
    }

    public float getPrice() {
        return price;
    }

    public Product(String name, float price) {
        cnt++;
        this.id = cnt;
        this.name = name;
        this.price = price;
        this.ratings = new ArrayList<>();
    }

    public void setRating(int rating) {
        ratings.add(rating);
    }

    public float getAvgRating() {
        float total = 0;
        for (Integer rating : ratings)
            total += rating;
        return total / ratings.size();
    }

    @Override
    public String toString() {
        return "Product ID " + id + ", '" + name + "', RMB " + price + ", Rating " + String.format("%.1f", getAvgRating());
    }
}

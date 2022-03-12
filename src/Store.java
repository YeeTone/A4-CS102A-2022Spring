import java.util.ArrayList;

public class Store {
    private static int cnt = 0;
    private int id; // unique for each store
    private String name;
    private ArrayList<Product> productList;

    public Store(String name, ArrayList<Product> productList) {
        cnt++;
        this.id = id;
        this.name = name;
        this.productList = productList;
    }

    public boolean addProduct(Product product) {
        if (hasProduct(product))
            return false;
        productList.add(product);
        return true;
    }

    public boolean removeProduct(Product product) {
        if (!hasProduct(product))
            return false;
        productList.remove(product); //
        return true;
    }

    public ArrayList<Product> getProductList() {
        return productList;
    }

    public boolean hasProduct(Product product) {
        for (Product value : productList) {
            if (value.getId() == product.getId())
                return true;
        }
        return false;
    }
}

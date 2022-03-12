import java.util.ArrayList;

public class Customer {
    private static int cnt = 0;
    private int id;  // unique for each customer
    private String name;
    private ArrayList<Product> shoppingCart; // default empty

    public Customer(String name) {
        cnt++;
        this.id = cnt;
        this.name = name;
        this.shoppingCart = new ArrayList<>();
    }

    public void rateProduct(Product product, int rating) {
        product.setRating(rating);
    }

    public boolean purchaseProduct(Store store, Product product) {
        if (!store.hasProduct(product))
            return false;
        store.removeProduct(product);
        shoppingCart.add(product);
        return true;
    }

    public void viewShoppingCart(SortBy sortMethod) {
        Product[] sortList = sort(sortMethod);
        for (Product product : sortList)
            System.out.println(product.toString());
    }

    public Product[] sort(SortBy sortMethod) {
        Product[] sortList = new Product[shoppingCart.size()];
        for (int i = 0; i < shoppingCart.size(); i++)
            sortList[i] = shoppingCart.get(i);
        for (int i = 1; i < sortList.length; i++) {
            for (int j = 0; j < sortList.length - 1; j++) {
                if ((sortMethod == SortBy.Rating && sortList[j].getAvgRating() > sortList[j + 1].getAvgRating())
                        || (sortMethod == SortBy.Price && sortList[j].getPrice() > sortList[j + 1].getPrice())) {
                    Product temp = sortList[j];
                    sortList[j] = sortList[j + 1];
                    sortList[j + 1] = temp;
                }
            }
        }
        return sortList;
    }
}


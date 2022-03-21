# Assignment 4
## Contributor List:
> 
> Design: YeeTone WANG, Yida TAO
> 
> JUnit: YeeTone WANG, Dingyuan XUE
> 
> Document: Yida TAO
> 
> Solution & Test: Siyi WANG, Sicen LIU

## Question 1. BigBinary [Medium, 40 + 8 Points]

Computers store data in the binary form.

YeeTone is a student of CS major but not good at solving binary number arithmetic problems, so he asks you for help. 
Fortunately, he is very familiar with Java programming.
If you can help YeeTone solve problems by designing a class in Java, he will be very happy to give you extra bonus points on your assignment!

Please design a Java class named BigBinary to give a hand to YeeTone. 
Notice that in order to let YeeTone really trust you have the ability to help him solve the problems, YeeTone does not want you to use some classes in the libraries of Java such as ```java.math.BigInteger``` in this problem. 

### Constructor
```java
public BigBinary(int[] bits, boolean positive);
```

Design a constructor with two parameters:
* An int array ```bits``` consisting of 0s and 1s, meaning the corresponding bit values. ```0 <= bits.length <= 1,5000```. Notice that in order to ensure the data security, you should not copy the reference but the values of ```bits```.
* A boolean ```positive```, indicating whether the sign of BigBinary object is positive.

In the following examples, ```^``` means power in math instead of XOR. For instance, 3^5 = 3\*3\*3\*3\*3=243.

**Example 1**
```java
bits = {0,1,0,1,1,0}, positive = false
```
The bigBinary can express the binary number ```-10110```, whose decimal number is ```-(2^4 + 2^2 + 2^1) = -22```.

**Example 2**
```java
bits = {}, positive = true
```
The bigBinary can express the binary number ```0```, whose decimal number is ```0```.

**Example 3**
```java
bits = {1,0,0,0,1,1}, positive = true
```
The bigBinary can express the binary number ```100011```, whose decimal number is ```2^5 + 2^1 + 2^0 = 35```.


### Methods
- ```public String toString();```

This method can return the BigBinary in a binary format. Notice that if there exists at least one ```1``` in the binary form, then the leading 0s should not be output; otherwise return ```"0"```.
For example, if the BigBinary is ```-0011011```, the ```toString()``` method should return ```"-11011"```; if the BigBinary is ```-00000```, the ```toString()``` method should return ```"0"```.

**Sample code:**
```java
public static void main(String[] args) {
    int[] b1 = {0, 0, 1, 0, 1};
    int[] b2 = {1, 1, 0};
    int[] b3 = {};
    int[] b4 = {1, 1, 1, 0, 0};
    int[] b5 = {0, 1};

    BigBinary bigBinary1 = new BigBinary(b1, true);
    BigBinary bigBinary2 = new BigBinary(b2, false);
    BigBinary bigBinary3 = new BigBinary(b3, false);
    BigBinary bigBinary4 = new BigBinary(b4, true);
    BigBinary bigBinary5 = new BigBinary(b5, false);

    System.out.println(bigBinary1);
    System.out.println(bigBinary2);
    System.out.println(bigBinary3);
    System.out.println(bigBinary4);
    System.out.println(bigBinary5);
}
```

**Sample Output:**
```java
101
-110
0
11100
-1
```

- ```public BigBinary add(BigBinary bigbinary)```

After executing the method, the original BigBinary and the return value should be the sum of this BigBinary and the parameter `bigbinary`(i.e. this BigBinary + `bigBinary`).

- ```public BigBinary minus(BigBinary bigbinary)```

After executing the method, the original BigBinary and the return value should be the difference between this BigBinary and the parameter `bigbinary`(i.e. this BigBinary - `bigBinary`).

- ```public BigBinary multiply(BigBinary bigbinary)```

**(Bonus)** After executing the method, the original BigBinary and the return value should be the product of this BigBinary and the parameter `bigbinary`(i.e. this BigBinary * `bigBinary`).

- ```public static BigBinary add(BigBinary b1, BigBinary b2)```

After executing the method, the return value should be the sum of BigBinary ```b1``` and BigBinary ```b2```(i.e. ```b1``` + ```b2```). BigBinary ```b1``` and ```b2``` should not be changed.

- ```public static BigBinary minus(BigBinary b1, BigBinary b2)```

After executing the method, the return value should be the difference between BigBinary ```b1``` and BigBinary ```b2```(i.e. ```b1``` - ```b2```). BigBinary ```b1``` and ```b2``` should not be changed.


- ```public static BigBinary multiply(BigBinary b1, BigBinary b2)```

**(Bonus)** After executing the method, the return value should be the product of BigBinary ```b1``` and BigBinary ```b2```(i.e. ```b1``` * ```b2```). BigBinary ```b1``` and ```b2``` should not be changed.

**Hint for Bonus** Considering the data scale, the simple implementation may cause **TLE(Time Limit Exceeded)** and cannot pass the testcases. Please think about how to improve the efficiency of multiplication!

## Question 2. Online Store [Medium, 60 + 12 Points]

Online Store is one of the project topics in the course OOAD(Object-Oriented Analysis and Design) in 2021Fall semester. In the basic part of project requirement, customers could go to different online stores, each of which provides a list of products for customers to buy. In this question, you'll contribute to the project by designing three classes, `Customer`, `Store`, and `Product`, to implement the online shopping process. The details of each class is described below.

### 2.1 Product
#### Attributes
```java
private static int cnt; //intialized to 0, and will increase by 1 when the constructor is called.
private int id;  // unique for each product and the value is from cnt.
private String name;
private float price;
private ArrayList<Integer> ratings; // default empty
```

#### Constructor
```java
public Product(String name, float price);
```
The id of the first product is 1.

#### Methods
```java
public boolean setRating(int rating);
public float getAvgRating();
public String toString();
```
`public boolean setRating(int rating);`

A customer can rate a product using this method. The `rating` will be added to this product's rating list `ratings`. Note that
* A `rating` should be within the range [1,5]; in other words, there are only 5 possible values for `rating`. If `rating` is not in this range, then do not add into `rating` and return false.
* For simplicity, *a customer can give different ratings to the same product multiple times*.

`public float getAvgRating();`

Return the average rating of this product, which is computed as the average rating given all the ratings its has received so far.   
Again, for simplicity, *a customer can give different ratings to the same product multiple times*. For example, if a product has got two different ratings, 5 and 4, from Alice and Bob respectively, then its rating is 4.5; if a product has got two different ratings, 5 and 4, both from Alice, then its rating is still 4.5.

`public String toString();`

Return a string description of this product, in the format of `Product ID id, name, RMB price, Rating rating`, e.g., `Product ID 12345, Laptop, RMB 10000.0, Rating 4.50`.   
Note that for `price`, let's keep 1 decimal place. For `rating`, keep 2 decimal place.

### 2.2 Store
#### Attributes
```java
private static int cnt; //intialized to 0, and will increase by 1 when the constructor is called.
private int id;  // unique for each store and the value is from cnt.
private String name;
private ArrayList<Product> productList;
private float income;
```

#### Constructors
```java
public Store(String name, ArrayList<Product> productList);
```
The id of the first store is 1.
#### Methods
```java
public boolean addProduct(Product product);
public boolean removeProduct(Product product);
public ArrayList<Product> getProductList();
public boolean hasProduct(Product product);
public float getIncome();
public boolean setIncome(float income);
```

`public boolean addProduct(Product product);`

Add `product` to the `productList`; return a boolean indicating whether the operation succeeds. Note that:
* For simplicity, suppose each product, which is uniquely identified by its `id`, could only appear once in the `productList`.
* So if `product` already exists in `productList` (i.e., there is a product in `productList` that has the same `id` as `product`), return `false` and `productList` remains the same; otherwise, add `product` to the end of `productList` and return `true`.

`public boolean removeProduct(Product product);`

Remove `product` from `productList`; return a boolean indicating whether the operation succeeds. Note that:
* If `product` exists in `productList`, remove it from `productList` and return `true`.
* Otherwise, return `false` and `productList` remains the same.

`public ArrayList<Product> getProductList();`

Return `productList`.

`public boolean hasProduct(Product product);`

Return `true` if this store has the given `product`; otherwise, return `false`.

`public float getIncome();`

Get the current income of this store.

`public boolean setIncome(float income);`

Update the income. Return `false` if the income is not valid.

### 2.3 Customer
#### Attributes
```java
private static int cnt; //intialized to 0, and will increase by 1 when the constructor is called.
private int id;  // unique for each customer and the value is from cnt.
private String name;
private ArrayList<Product> shoppingCart; // default empty
private float wallet; 
```
#### Constructor
```java
public Customer(String name);
public Customer(String name, float wallet);
```
The id of the first customer is 1.
#### Methods
```java
public boolean rateProduct(Product product, int rating);
public boolean purchaseProduct(Store store, Product product);
public boolean updateWallet(float amount);
public void viewShoppingCart(SortBy sortMethod);
public boolean refundProduct(Product product); 
```
`public boolean rateProduct(Product product, int rating)`

Set the rating of the given `product` to `rating`. For the invalid argument, return false; otherwise return true.

`public boolean purchaseProduct(Store store, Product product)`

Purchase `product` from `store`; return `true` if the `store` has this `product` and the customer has enough money in the wallet to purchase this product; return `false` otherwise. Note that the shopping cart of this customer as well as the product list for the store should be updated accordingly.

For simplicity, **suppose a customer can purchase the same product only once**.

`public boolean updateWallet(float amount);`

Update the wallet of this customer. The amount could be positive (gaining money) or negative (consuming money).

`public void viewShoppingCart(SortBy sortMethod);`

Display the purchased products in the shopping cart of this customer. The order of displaying is specified by `sortMethod`. We provide an Enum `SortBy`, which says that sorting could be performed by the purchase time, rating, or the price of products.

```Java
public enum SortBy {
    PurchaseTime, Rating, Price
}
```

Suppose a customer Alice has purchase a few products from different stores.
```java
Customer alice = new Customer(1, "Alice");
// code for creating stores and products are ommitted
alice.purchaseProduct(store1, product_laptop);
alice.purchaseProduct(store1, product_table);
alice.purchaseProduct(store2, product_mouse);
alice.purchaseProduct(store3, product_phone);
```
Then, calling
```java
alice.viewShoppingCart(SortBy.PurchaseTime);
```
will display (see `Product.toString()` for the format)
```
Product ID 2, Laptop, RMB 10000.0, Rating 4.50
Product ID 4, Table, RMB 300.0, Rating 5.50
Product ID 3, Mouse, RMB 100.0, Rating 3.00
Product ID 1, Phone, RMB 7000.0, Rating 4.50
```
Calling
```java
alice.viewShoppingCart(SortBy.Rating);
```
will display
```
Product ID 3, Mouse, RMB 100.0, Rating 3.00
Product ID 2, Laptop, RMB 10000.0, Rating 4.50
Product ID 1, Phone, RMB 7000.0, Rating 4.50
Product ID 4, Table, RMB 300.0, Rating 5.50
```
Note that when having the same rating, the purchased products will be displayed by the purchase time.   
Calling
```java
alice.viewShoppingCart(SortBy.Price);
```
will display
```
Product ID 3, Mouse, RMB 100.0, Rating 3.00
Product ID 4, Table, RMB 300.0, Rating 5.50
Product ID 1, Phone, RMB 7000.0, Rating 4.50
Product ID 2, Laptop, RMB 10000.0, Rating 4.50
```
Note that when having the same price, the purchased products will be displayed by the purchase time.

`public boolean refundProduct(Product product)`

**Bonus** Return the product to its store and get the money back. Return `true` if this customer has indeed purchased this product before. Note that the `shoppingCart` and `wallet` of this customer should be updated accordingly; In addition, the `productList` and `income` of the corresponding store should also be updated (suppose that the store adds this product back to its product list and could re-sell this product).

Tip: to associate a product and its corresponding store, you may use a `HashMap` to map their IDs (`<productID, storeID>`).

## Submission
You need to submit `BigBinary.java` for question 1, `Product.java`, `Store.java`, `Customer.java` and `SortBy.java` for question 2.

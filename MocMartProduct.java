/*
Alexander Marston
1314670
Section CSC3280-002 
amarston@mocs.flsouthern.edu
*/
public class MocMartProduct {

    // all data fields 
    private int itemNum;
    private String itemName;
    private double itemPrice;
    private int quantity;
    private int restockQuantity;
    private static int numProducts;

    // defualt constructor
    public MocMartProduct() {
    }

    // constructor with all data memebers 
    public MocMartProduct(int itemNum, String itemName, double itemPrice, int quantity, int restockQuantity) {
        this.itemNum = itemNum;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.quantity = quantity;
        this.restockQuantity = restockQuantity;
    }
    
    // get item number
    public int getItemNum() {
        return itemNum;
    }
    
    // set item number
    public void setItemNum(int itemNum) {
        this.itemNum = itemNum;
    }
    
    // get item name
    public String getItemName() {
        return itemName;
    }
    
    // set item name
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
    
    // get item price
    public double getItemPrice() {
        return itemPrice;
    }
    
    // set item price
    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }
    
    // get quantity
    public int getQuantity() {
        return quantity;
    }

    // set quantity
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // get restock quantity 
    public int getRestockQuantity() {
        return restockQuantity;
    }

    // set restock quantity 
    public void setRestockQuantity(int restockQuantity) {
        this.restockQuantity = restockQuantity;
    }
    
    // get number of products 
     public static int getNumProducts() {
        return numProducts;
    }
     
    // set num of products
    public static void setNumProducts(int aNumProducts) {
        numProducts = aNumProducts;
    }
    
}

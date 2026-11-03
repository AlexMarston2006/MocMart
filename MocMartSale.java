/*
Alexander Marston
1314670
Section CSC3280-002 
amarston@mocs.flsouthern.edu
*/
public class MocMartSale {

    
    // all data memebers 
    private String firstName;
    private String lastName;
    private int numItemsOnList;
    private int[] itemsPurchased;
    private static int numSales;
    
    // default constructor 
    public MocMartSale() {
    }
    
    // constructor with all data memebers 
    public MocMartSale(String firstName, String lastName, int numItemsOnList, int[] itemsPurchased) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.numItemsOnList = numItemsOnList;
        this.itemsPurchased = itemsPurchased;
    }

    // get first name
    public String getFirstName() {
        return firstName;
    }
    
    // set first name
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    // get last name
    public String getLastName() {
        return lastName;
    }
    
    // set last name 
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    // get num items on list
    public int getNumItemsOnList() {
        return numItemsOnList;
    }
    
    // set num items on list
    public void setNumItemsOnList(int numItemsOnList) {
        this.numItemsOnList = numItemsOnList;
    }
    
    // get items purchases
    public int[] getItemsPurchased() {
        return itemsPurchased;
    }
    
    // set items purchased
    public void setItemsPurchased(int[] itemsPurchased) {
        this.itemsPurchased = itemsPurchased;
    }
    
    
    
    
}

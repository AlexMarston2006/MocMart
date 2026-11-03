/*
: I will practice academic and personal integrity and excellence of
character and expect the same from others.
Alexander Marston
1314670
Section CSC3280-002 
amarston@mocs.flsouthern.edu
Program 1
9/9/26

 */

import java.util.*;

public class MocMart {

    public static int addItem(int currentNumOfProducts, MocMartProduct[] products, int itemNum, String itemName, double unitPrice, int stockQty, int restockQty) {
        // this command adds the new product with the relevant information and by order of item number
        // create new product object with the entered information
        MocMartProduct product = new MocMartProduct(itemNum, itemName, unitPrice, stockQty, restockQty);

        // if we found an inserted place thats not the largest product num
        boolean inserted = false;

        // if first item in array to avoid a null
        if (currentNumOfProducts == 0) {
            products[0] = product;
            // if at least one product in the array
        } else {
            // add the new product to the array at the appropiate index

            // determine if current product is smaller in item number than current smallest
            if (product.getItemNum() < products[0].getItemNum()) {
                // shift everything to the right
                for (int i = currentNumOfProducts; i > 0; i--) {
                    products[i] = products[i - 1];
                }
                products[0] = product;

                // if product itemNum is not smaller than the current smallest item num
            } else {
                // loop over based on num of products
                for (int i = 0; i < currentNumOfProducts; i++) {
                    // check if product number is smaller then item were checking
                    if (product.getItemNum() < products[i].getItemNum()) {
                        // index where item should be inserted
                        int insertIndex = i;
                        for (int j = currentNumOfProducts; j > insertIndex; j--) {
                            // shift items from where we need to
                            products[j] = products[j - 1];

                        }
                        // insert the product
                        products[insertIndex] = product;

                        // updated that we found an insertion point
                        inserted = true;

                        // stop searching for a insertion place
                        break;
                    }
                }
                // if we got here it means the product number is the current largest
                if (inserted == false) {
                    // add product as the current largest product by item number
                    products[currentNumOfProducts] = product;
                }

            }
        }
        // increase current number of products in array NOT stock quantiy of the item
        currentNumOfProducts++;
        // let user know the desired item has been added to the inventory
        System.out.printf("    Item %d, %s, with a cost of $%.2f and initial stock of %d, has been added to the product database.\n", itemNum, itemName, unitPrice, stockQty);
        // for spacing
        System.out.println();
        // send back how many items are in the array
        return currentNumOfProducts;
    }

    public static void FINDITEM(int currentNumOfProducts, int numSales, MocMartSale[] sales, MocMartProduct[] products, int itemNum) {
        // this command will find the index the product is
        // print what indexes were searched
        // and the item information
        // this will be done using a binary search

        // where we checked
        String indexesSearched = " ";
        // the low of search area
        int low = 0;
        // high of search area
        int high = currentNumOfProducts - 1;
        // the mid (current index at)
        int mid;

        // total amount of product sold in terms of money
        double totalAmountMoney = 0;

        // while we are still searching
        while (low <= high) {
            // find the mid of the search area
            mid = (low + high) / 2;
            // add what indexes we searched
            indexesSearched += mid + " ";

            // if we find the desired product
            if (products[mid].getItemNum() == itemNum) {
                // get amount sold for that item
                int unitsSold = 0;
                // get all sales
                for (int i = 0; i < numSales; i++) {
                    // everything that has been purchased
                    int[] itemsPurchased = sales[i].getItemsPurchased();
                    // check each sale indiviudally only checking for item number 
                    for (int j = 0; j < itemsPurchased.length; j += 2) {
                        // check if current sale is item searching for
                        if (itemsPurchased[j] == itemNum) {
                            // since in the array the first number is item number, next is quantity
                            unitsSold += itemsPurchased[j + 1];
                        }
                    }
                }
                // calculate amount sold in dollars
                totalAmountMoney = products[mid].getItemPrice() * unitsSold;
                // print what indexes were searched
                System.out.printf("    Performing Binary Search...(Indices viewed: %s )\n", indexesSearched);
                // print item name
                System.out.printf("    Item #%d (%s)\n", itemNum, products[mid].getItemName());
                // print item price
                System.out.printf("    Price            :  $%.2f\n", products[mid].getItemPrice());
                // print current quantity
                System.out.printf("    Current Quantity :  %d\n", products[mid].getQuantity());
                // print units sold
                System.out.printf("    Units Sold       :  %d\n", unitsSold);
                // print amount of item sold in terms of money
                System.out.printf("    Total Amount     :  $%.2f\n", totalAmountMoney);
                // for spacing 
                System.out.println();
                // stop searching for item
                break;

            } // if product number is smaller than the mid
            else if (itemNum < products[mid].getItemNum()) {
                // update high
                high = mid - 1;
            } // if product number is bigger than the mid
            else if (itemNum > products[mid].getItemNum()) {
                // update low
                low = mid + 1;
            }

        }
        // if product not found
        if (low > high) {
            // still print indexes searched
            System.out.printf("    Performing Binary Search...(Indices viewed: %s )\n", indexesSearched);
            // print item was not found 
            System.out.printf("    Item #%d was not found in the product database.\n", itemNum);
            // space lines
            System.out.println();

        }
    }

    public static void restock(int currentNumOfProducts, MocMartProduct[] products) {
        // this method will restock any products with a current stock of 0

        // used to check if something was stocked
        boolean somethingGotRestocked = false;

        // loop over all products
        for (int i = 0; i < currentNumOfProducts; i++) {
            // check if stock is 0
            if (products[i].getQuantity() == 0) {
                // restock using restock quantity 
                products[i].setQuantity(products[i].getRestockQuantity());
                // print that the item has been restocked
                System.out.printf("     Item %d, %s, restocked to a quantity of %d.\n", products[i].getItemNum(), products[i].getItemName(), products[i].getRestockQuantity());
                // update something was found, this aids with if no items need restocked
                somethingGotRestocked = true;
            }

        }
        // check if nothing needed restocked
        if (somethingGotRestocked == false) {
            // print nothing needed restocked
            System.out.println("    There are no items to restock.");
        }
        // for spacing
        System.out.println();
    }

    public static int customer(int currentNumOfProducts, int numSales, MocMartSale[] sales, MocMartProduct[] products, String customerFirstName, String customerLastName, int n, int[] shoppingList) {
        // this command is used to process a customer purchase with thier name
        // item number and how much of that item they want

        // if sale was made
        boolean saleMade = false;
        // units customer bought
        int unitsBought = 0;

        // for items purchased if items are avaialbe 
        int[] itemsPurchases = new int[n];
        // loop over user shopping list
        // += 2 so we can look at just the item number
        for (int i = 0; i < shoppingList.length; i += 2) {
            // the item number we're shoppoing for 
            int itemNum = shoppingList[i];
            // how many of the item customer wants
            int quantityWanted = shoppingList[i + 1];

            // search the inventory 
            for (int j = 0; j < currentNumOfProducts; j++) {
                // find the product
                if (itemNum == products[j].getItemNum()) {
                    // check if enough stock 
                    if (products[j].getQuantity() >= quantityWanted) {
                        // add item number to items purchased
                        itemsPurchases[i] = itemNum;
                        // add amount bought
                        itemsPurchases[i + 1] = quantityWanted;
                        // add to amount of items bought
                        unitsBought += quantityWanted;

                        // update sale made
                        saleMade = true;

                        // reduce inventory 
                        products[j].setQuantity(products[j].getQuantity() - quantityWanted);
                    } // if not enough stock
                    else if (products[j].getQuantity() < quantityWanted && products[j].getQuantity() >= 1) {
                        // add item to itemspurchased
                        itemsPurchases[i] = itemNum;
                        // add amount of item bought
                        itemsPurchases[i + 1] = products[j].getQuantity();
                        // add to amount of items bought
                        unitsBought += products[j].getQuantity();
                        // set quantitiy to 0 since all were bought
                        products[j].setQuantity(0);
                        // update that a sale was made 
                        saleMade = true;
                    }
                }
            }
        }

        // if sale was made
        if (saleMade) {
            // add all information of sale that occured
            MocMartSale sale = new MocMartSale(customerFirstName, customerLastName, unitsBought, itemsPurchases);

            // add sale to sales
            for (int i = 0; i < sales.length; i++) {
                // once we reach a blank part in sales array we had current sale
                if (sales[i] == null) {
                    sales[i] = sale;
                    break;
                }
            }
            // print that the customer visted
            System.out.printf("     Customer %s %s came and made some purchases.\n", customerFirstName, customerLastName);
            // for spacing
            System.out.println();
            // update how many sales have been made
            numSales++;
        } else {
            // sale not made
            System.out.printf("     Customer %s %s came and made no purchases.\n", customerFirstName, customerLastName);
            // for spacing 
            System.out.println();
        }
        // send back how many sales have been made
        return numSales;
    }

    public static void inventory(MocMartProduct[] products, int currentNumProducts) {
        // this method simply prints each item added to inventory 
        // includes name, item number, price and quantity 
        // print basic message
        System.out.println("    Contains the following items:");
        // loop over the products and print the approapoate information 
        for (int i = 0; i < currentNumProducts; i++) {
            System.out.printf("    | Item   %d | %s           | $   %.2f |    %d unit(s) |\n", products[i].getItemNum(), products[i].getItemName(), products[i].getItemPrice(), products[i].getQuantity());
        }
        // for spacing
        System.out.println();
    }

    public static void printSalesSummary(int currentNumOfProducts, MocMartProduct[] products, MocMartSale[] sales, int numSales) {
        // this method will print a summary of all sales that occured

        // print basic message
        System.out.println("Moc Mart Sales Report:");
        // print what sale, customer name, num items purchased for each sale
        // what item number purchased, item name, item price and quantity 
        // then print grand total of sales

        // how much total has been saled
        double grandTotal = 0;

        // loop over each sale
        for (int i = 0; i < numSales; i++) {
            MocMartSale sale = sales[i];
            // first name of sale
            String firstName = sale.getFirstName();
            // last name of sale
            String lastName = sale.getLastName();
            // how much the customer bought
            int numItems = sale.getNumItemsOnList();
            // what the customer bought
            int[] itemsPurchases = sale.getItemsPurchased();
            // print what customer bought and sale number
            System.out.printf("    Sale #%d, %s %s purchased %d item(s):\n", i + 1, firstName, lastName, numItems);
            // sale total for a particular customer
            double saleTotal = 0;
            // loop over items person purchased
            for (int j = 0; j < itemsPurchases.length; j += 2) {
                // get item number
                int itemNumber = itemsPurchases[j];
                // get amount purchased
                int purcashedQuantity = itemsPurchases[j + 1];

                // get product information
                for (int k = 0; k < currentNumOfProducts; k++) {
                    // check if current item number is desired item number
                    if (products[k].getItemNum() == itemNumber) {
                        // get that item name
                        String itemName = products[k].getItemName();
                        // get that item price
                        double itemPrice = products[k].getItemPrice();
                        // add to saleTotal
                        saleTotal += purcashedQuantity * itemPrice;

                        // print item informatiom, price and quantity
                        System.out.printf("        | Item   %d | %s             | $  %.2f (x   %d) |\n", itemNumber, itemName, itemPrice, purcashedQuantity);
                    }
                }

            }
            // print total sale fot the customer
            System.out.printf("        Total: $%.2f\n", saleTotal);
            // add saleTotal to grand total 
            grandTotal += saleTotal;
        }
        // print grand total
        System.out.printf("    Grand Total: $%.2f\n", grandTotal);
        // for spacing
        System.out.println();
    }

    public static void main(String[] args) {

        // create scanner object
        Scanner in = new Scanner(System.in);
        // max products being added in this set of input
        int maxProducts = in.nextInt();
        // max sales being made in this set of input
        int maxSales = in.nextInt();
        // get rest of user input
        String userInput = in.nextLine();

        // current number of product objects in array
        int currentNumOfProducts = 0;
        // current number of sales in array
        int numSales = 0;

        // array to store product objects
        MocMartProduct[] products = new MocMartProduct[maxProducts];
        // array to store sale objects
        MocMartSale[] sales = new MocMartSale[maxSales];
        
        // keep looping until quit
        while (true) {
            // able to read specific input data
            String[] splitInput = userInput.split(" ");
            // call appropiate method
            if (splitInput[0].equals("ADDITEM")) {
                // print command user entered
                System.out.println("ADDITEM:");
                
                // get item number
                String itemNumAsString = splitInput[1];
                // get item name
                String itemName = splitInput[2];
                // get item price
                String unitPriceAsString = splitInput[3];
                // get stock quantity
                String stockQtyAsString = splitInput[4];
                // get restock quantity
                String restockQtyAsString = splitInput[5];
                
                // convert item number to integer
                int itemNum = Integer.parseInt(itemNumAsString);
                // convert price to double
                double unitPrice = Double.parseDouble(unitPriceAsString);
                // convert stock quantity to integer
                int stockQty = Integer.parseInt(stockQtyAsString);
                // convert restock quantity to integer
                int restockQty = Integer.parseInt(restockQtyAsString);
                
                // call method with all of these variables and get back how many products
                // are in the inventory 
                currentNumOfProducts = addItem(currentNumOfProducts, products, itemNum, itemName, unitPrice, stockQty, restockQty);
            } else if (splitInput[0].equals("FINDITEM")) {

                // access the users item they're looking for 
                String itemNumAsString = splitInput[1];
                // convert to int
                int itemNum = Integer.parseInt(itemNumAsString);

                // print entered command
                System.out.println("FINDITEM: ");
                // if no items have been added
                if (currentNumOfProducts == 0) {
                    // let user know theres no items in the inventory
                    System.out.println("    Cannot perform command; there are no items in the product database.");
                    // for spacing
                    System.out.println();
                } else {
                    // call the FINDITEM method
                    FINDITEM(currentNumOfProducts, numSales, sales, products, itemNum);

                }
            } else if (splitInput[0].equals("RESTOCK")) {
                // print command user entered
                System.out.println("RESTOCK:");
                
                // if no items in inventory 
                if (currentNumOfProducts == 0) {
                    // let users know no items to restock
                    System.out.println("    There are no items to restock.");
                    // blank line for spacing reasons
                    System.out.println();
                } else {
                    // call restock method
                    restock(currentNumOfProducts, products);
                }
            } else if (splitInput[0].equals("CUSTOMER")) {
                // print command entered
                System.out.println("CUSTOMER:");

                // set first name
                String customerFirstName = splitInput[1];
                // set last name
                String customerLastName = splitInput[2];
                // set n 
                int n = Integer.parseInt(splitInput[3]);
                // create user's shopping list
                int[] shoppingList = new int[n];
                // add item numbers and quantites to shopping list
                for (int i = 0; i < shoppingList.length; i++) {
                    shoppingList[i] = Integer.parseInt(splitInput[4 + i]);
                }
                // call customer method
                numSales = customer(currentNumOfProducts, numSales, sales, products, customerFirstName, customerLastName, n, shoppingList);

            } else if (splitInput[0].equals("INVENTORY")) {
                // print command user entered
                System.out.println("INVENTORY:");
                // check if there even is an inventory
                if (currentNumOfProducts == 0) {
                    // print if no items in inventory
                    System.out.println("    Contains no items.");
                    // for spacing 
                    System.out.println();
                    // if at least one item in the inventory
                } else {
                    // call inventory method
                    inventory(products, currentNumOfProducts);
                }
            } else if (splitInput[0].equals("PRINTSALESSUMMARY")) {
                // print command user entered
                System.out.println("PRINTSALESSUMMARY:");
                // call print sales summary method
                printSalesSummary(currentNumOfProducts, products, sales, numSales);
            } else if (splitInput[0].equals("QUIT")) {
                // stop checking for commands
                break;
            }
            // read next command entered
            userInput = in.nextLine();

        }
        // print goodbye message
        System.out.println("Goodbye.");

    }

}

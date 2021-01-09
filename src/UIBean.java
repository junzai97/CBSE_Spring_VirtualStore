import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class UIBean {
    static Scanner scanner;
    private StoreBean storeBean;

    static {
        scanner = new Scanner(System.in);
    }

    public UIBean() {
    }

    public void setStoreBean(StoreBean storeBean) {
        this.storeBean = storeBean;
    }

    public void displayProducts() {
        Map<Product, Integer> products = this.storeBean.getAllProductsStock();
        System.out.println("\n------------------");
        System.out.println(" AVAILABLE PRODUCTS");
        System.out.println("--------------------\n");
        System.out.println("----------------------------------------------------------------");
        System.out.println("No.\t\tITEM\t\t\tPRICE\t\tSTOCK");
        System.out.println("----------------------------------------------------------------\n");
        int productIndex = 0;
        Iterator var4 = products.keySet().iterator();

        while(var4.hasNext()) {
            Product product = (Product)var4.next();
            ++productIndex;
            System.out.printf("%1$d.\t\t%2$s\t\t$%3$,.2f\t\t%4$d\n", productIndex, product.getName(), product.getPrice(), products.get(product));
        }

    }

    public Product selectItemToBuy() {
        List<Product> products = this.storeBean.getAvailableProducts();
        System.out.println("\n---------");
        System.out.println(" CATALOG");
        System.out.println("---------\n");
        System.out.println("-------------------------------------------------");
        System.out.println("No.\t\tITEM\t\t\tPRICE");
        System.out.println("-------------------------------------------------\n");
        int productIndex = 0;
        Iterator var4 = products.iterator();

        while(var4.hasNext()) {
            Product product = (Product)var4.next();
            ++productIndex;
            System.out.printf("%1$d.\t\t%2$s\t\t$%3$,.2f\n", productIndex, product.getName(), product.getPrice());
        }

        System.out.println("\nPlease select item no. to buy:");
        int selectedIndex = scanner.nextInt();
        if (selectedIndex > 0 && selectedIndex <= products.size()) {
            Product selectedProduct = (Product)products.get(selectedIndex - 1);
            return selectedProduct;
        } else {
            System.out.println("ERROR - Invalid product selection");
            return null;
        }
    }

    public int selectQuantityToBuy(Product product) {
        System.out.println("How many \"" + product.getName() + "\" do you want? ");
        int quantity = scanner.nextInt();
        return quantity;
    }

    public void purchaseSuccess(Product product, int quantity) {
        System.out.println("SUCCESS - " + quantity + " quantities of " + product.getName() + " added to shopping cart!");
        this.displayShoppingCart();
    }

    public void purchaseFail(Product product, int stock) {
        System.out.println("ERROR - Insufficient Stock; only " + stock + " quantities of " + product.getName() + " are available");
        this.displayShoppingCart();
    }

    public Product[] displayShoppingCart() {
        Map<Product, Integer> shoppedProducts = this.storeBean.getShoppedProducts();
        System.out.println("\n---------------------------------");
        System.out.println(" DISPLAYING ITEMS IN SHOPPING CART");
        System.out.println("-----------------------------------\n");
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("No.    ITEM                     UNIT-PRICE        QUANTITY      SUB-TOTAL");
        System.out.println("--------------------------------------------------------------------------------\n");
        int itemIndex = 0;
        double totalPrice = 0.0D;
        Product[] products = new Product[shoppedProducts.size()];
        Iterator var7 = shoppedProducts.keySet().iterator();

        while(var7.hasNext()) {
            Product product = (Product)var7.next();
            products[itemIndex] = product;
            ++itemIndex;
            double unitPrice = product.getPrice();
            int quantity = (Integer)shoppedProducts.get(product);
            double subTotal = unitPrice * (double)quantity;
            totalPrice += subTotal;
            System.out.printf("%1$2d%2$-5s%3$-25s$%4$,-17.2f%5$-14d$%6$,-8.2f\n", itemIndex, ".", product.getName(), product.getPrice(), quantity, subTotal);
        }

        System.out.println("                                                -----------------------------");
        System.out.printf("                                                 TOTAL PRICE:   $%1$,-8.2f\n", totalPrice);
        System.out.println("                                                -----------------------------\n");
        return products;
    }

    public Product selectItemToRemove() {
        Product[] products = this.displayShoppingCart();
        System.out.println("\nPlease select item no. to remove:");
        int selectedIndex = scanner.nextInt();
        if (selectedIndex > 0 && selectedIndex <= products.length) {
            Product product = products[selectedIndex - 1];
            return product;
        } else {
            System.out.println("ERROR - Invalid item specification\n");
            return null;
        }
    }

    public int selectQuantityToRemove(Product product) {
        System.out.println("How many " + product.getName() + " would you like to remove?");
        Map<Product, Integer> shoppedProducts = this.storeBean.getShoppedProducts();
        int currentQuantity = (Integer)shoppedProducts.get(product);
        System.out.println("Currently Shopping Cart contains : " + currentQuantity);
        int removalQty = scanner.nextInt();
        if (removalQty > 0 && removalQty <= currentQuantity) {
            return removalQty;
        } else {
            System.out.println("ERROR - Inappropriate quantity to remove");
            return 0;
        }
    }

    public void removalSuccess(Product product, int quantity) {
        System.out.println("SUCCESS - " + quantity + " quantities of " + product.getName() + " removed from shopping cart!");
        this.displayShoppingCart();
    }

    public void displayCheckOut() {
        double totalPrice = this.storeBean.getShoppingCartPrice();
        System.out.printf("Please Pay $%1$,-8.2f\n", totalPrice);
        System.out.println("Thank you for your patronage! Please visit again!");
    }

    public void addToReviewBoard(Map<Product, Integer> products){
        Iterator var7 = products.keySet().iterator();

        while(var7.hasNext()) {
            Product product = (Product)var7.next();
            storeBean.addItemsToReview(product.getName());
        }
    }

    public String[] displayReview(){
        Map<String, String> reviews = this.storeBean.getAllReviews();
        System.out.println("\n---------");
        System.out.println(" REVIEW");
        System.out.println("---------\n");
        System.out.println("----------------------------------------------------------------");
        System.out.println("No.\t\tITEM\t\t\t\tReview");
        System.out.println("----------------------------------------------------------------\n");
        int productIndex = 0;
        Iterator var5 = reviews.keySet().iterator();
        String[] products = new String[reviews.size()];

        while(var5.hasNext()) {
            String product = (String) var5.next();
            products[productIndex] = product;
            ++productIndex;
            String reviewMessage = reviews.get(product) == null? "Waiting to be review" : reviews.get(product);
            System.out.printf("%1$d.\t\t%2$s\t\t\t%3$s\n", productIndex, product, reviewMessage);
        }

        return products;
    }

    public void selectItemToReview(){
        String[] products = displayReview();
        System.out.print("\nPlease select item no. to review or insert 0 to exit: ");
        int selectedIndex = scanner.nextInt();
        scanner.nextLine();
        if(selectedIndex == 0){
            System.out.println("Back to main menu");
        } else if (selectedIndex > 0 && selectedIndex <= products.length) {
            String selectedProduct = products[selectedIndex-1];
            System.out.println("\nPlease write your review: ");
            String message = scanner.nextLine();
            storeBean.reviewItem(selectedProduct, message);
        } else {
            System.out.println("ERROR - Invalid item specification\n");
        }
    }

    public int mainMenu() {
        System.out.println("\n-------------------");
        System.out.println(" WELCOME TO eSTORE!");
        System.out.println("-------------------\n");
        System.out.println("1. View Inventory");
        System.out.println("2. Add item to shopping cart");
        System.out.println("3. Remove item from shopping cart");
        System.out.println("4. Check Out");
        System.out.println("5. Review purchased item");
        System.out.println("6. Exit");
        System.out.println("\nChoose an option:");
        return scanner.nextInt();
    }

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
        StoreBean storeBean = (StoreBean) context.getBean("storeBean");
        UIBean uiBean = (UIBean) context.getBean("uiBean");

        for(int userChoice = uiBean.mainMenu(); userChoice > 0 && userChoice <= 5; userChoice = uiBean.mainMenu()) {
            switch(userChoice) {
                case 1:
                    uiBean.displayProducts();
                    break;
                case 2:
                    Product selectedProduct = uiBean.selectItemToBuy();
                    if (selectedProduct != null) {
                        int quantity = uiBean.selectQuantityToBuy(selectedProduct);
                        storeBean.addItemToCart(selectedProduct, quantity);
                    }
                    break;
                case 3:
                    Product product = uiBean.selectItemToRemove();
                    if (product == null) {
                        return;
                    }

                    int quantity = uiBean.selectQuantityToRemove(product);
                    storeBean.removeItemsFromCart(product, quantity);
                    uiBean.displayShoppingCart();
                    break;
                case 4:
                    uiBean.displayCheckOut();
                    Map<Product, Integer> products = storeBean.getShoppedProducts();
                    uiBean.addToReviewBoard(products);
                    storeBean.checkOut();
                    break;
                case 5:
                    uiBean.selectItemToReview();
                    break;
            }
        }

    }
}

import java.util.List;
import java.util.Map;

public class Store {
    Inventory inventory = new Inventory();
    ShoppingCart shoppingCart = new ShoppingCart();
    UI ui;

    public Store(UI ui) {
        this.ui = ui;
        this.init();
    }

    public void init() {
        Product iPadProduct = new Product("New IPad", 400.0D);
        this.inventory.addProduct(iPadProduct, 100);
        Product iPhoneProduct = new Product("IPhone 5", 500.0D);
        this.inventory.addProduct(iPhoneProduct, 200);
        Product galaxyPadProduct = new Product("Galaxy Tab 2", 300.0D);
        this.inventory.addProduct(galaxyPadProduct, 300);
    }

    public List<Product> getAvailableProducts() {
        return this.inventory.getAvailableProducts();
    }

    public Map<Product, Integer> getShoppedProducts() {
        return this.shoppingCart.getShoppedProducts();
    }

    public Map<Product, Integer> getAllProductsStock() {
        return this.inventory.getAllProductsStock();
    }

    public void addItemToCart(Product product, int quantity) {
        if (product != null) {
            if (this.inventory.getStock(product) >= quantity) {
                this.shoppingCart.addItem(product, quantity);
                this.inventory.reduceStock(product, quantity);
                this.ui.purchaseSuccess(product, quantity);
            } else {
                this.ui.purchaseFail(product, this.inventory.getStock(product));
            }

        }
    }

    public void removeItemsFromCart(Product product, int quantity) {
        if (product != null) {
            if (quantity != 0) {
                this.inventory.increaseStock(product, quantity);
                this.shoppingCart.removeItem(product, quantity);
            }
        }
    }

    public double getShoppingCartPrice() {
        return this.shoppingCart.getPrice();
    }

    public void checkOut() {
        this.shoppingCart.empty();
    }
}

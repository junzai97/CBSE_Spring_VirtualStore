import java.util.List;
import java.util.Map;

public class StoreBean {
    InventoryBean inventoryBean;
    ShoppingCartBean shoppingCartBean;
    UIBean uiBean;

    public StoreBean(InventoryBean inventoryBean, ShoppingCartBean shoppingCartBean, UIBean uiBean) {
        this.inventoryBean = inventoryBean;
        this.shoppingCartBean = shoppingCartBean;
        this.uiBean = uiBean;
        this.init();
    }

    public void init() {
        Product iPadProduct = new Product("New IPad", 400.0D);
        this.inventoryBean.addProduct(iPadProduct, 100);
        Product iPhoneProduct = new Product("IPhone 5", 500.0D);
        this.inventoryBean.addProduct(iPhoneProduct, 200);
        Product galaxyPadProduct = new Product("Galaxy Tab 2", 300.0D);
        this.inventoryBean.addProduct(galaxyPadProduct, 300);
    }

    public List<Product> getAvailableProducts() {
        return this.inventoryBean.getAvailableProducts();
    }

    public Map<Product, Integer> getShoppedProducts() {
        return this.shoppingCartBean.getShoppedProducts();
    }

    public Map<Product, Integer> getAllProductsStock() {
        return this.inventoryBean.getAllProductsStock();
    }

    public void addItemToCart(Product product, int quantity) {
        if (product != null) {
            if (this.inventoryBean.getStock(product) >= quantity) {
                this.shoppingCartBean.addItem(product, quantity);
                this.inventoryBean.reduceStock(product, quantity);
                this.uiBean.purchaseSuccess(product, quantity);
            } else {
                this.uiBean.purchaseFail(product, this.inventoryBean.getStock(product));
            }

        }
    }

    public void removeItemsFromCart(Product product, int quantity) {
        if (product != null) {
            if (quantity != 0) {
                this.inventoryBean.increaseStock(product, quantity);
                this.shoppingCartBean.removeItem(product, quantity);
            }
        }
    }

    public double getShoppingCartPrice() {
        return this.shoppingCartBean.getPrice();
    }

    public void checkOut() {
        this.shoppingCartBean.empty();
    }
}

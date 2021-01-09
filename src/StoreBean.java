import java.util.List;
import java.util.Map;

public class StoreBean {
    InventoryBean inventoryBean;
    ShoppingCartBean shoppingCartBean;
    ReviewBoardBean reviewBoardBean;
    UIBean uiBean;

    public StoreBean(InventoryBean inventoryBean, ShoppingCartBean shoppingCartBean, ReviewBoardBean reviewBoardBean, UIBean uiBean) {
        this.inventoryBean = inventoryBean;
        this.shoppingCartBean = shoppingCartBean;
        this.uiBean = uiBean;
        this.reviewBoardBean = reviewBoardBean;
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

    public Map<String, String> getAllReviews() {
        return this.reviewBoardBean.getReviews();
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

    public void addItemsToReview(String product) {
        if(product != null) {
            this.reviewBoardBean.addReview(product);
        }
        System.out.println(reviewBoardBean);
    }

    public void reviewItem(String product, String reviewMessage) {
        if(product != null){
            if(reviewMessage != null){
                this.reviewBoardBean.setReview(product, reviewMessage);
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

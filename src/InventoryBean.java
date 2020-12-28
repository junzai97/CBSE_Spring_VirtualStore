import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class InventoryBean {
    Map<Product, Integer> products = new HashMap();

    public InventoryBean() {
    }

    public void addProduct(Product product, int stock) {
        this.products.put(product, stock);
    }

    public void reduceStock(Product product, int quantity) {
        int oldStock = (Integer)this.products.get(product);
        int newStock = oldStock - quantity;
        this.products.put(product, newStock);
    }

    public void increaseStock(Product product, int quantity) {
        int oldStock = (Integer)this.products.get(product);
        int newStock = oldStock + quantity;
        this.products.put(product, newStock);
    }

    public List<Product> getAvailableProducts() {
        List<Product> availableProducts = new ArrayList();
        Iterator var3 = this.products.keySet().iterator();

        while(var3.hasNext()) {
            Product product = (Product)var3.next();
            if ((Integer)this.products.get(product) > 0) {
                availableProducts.add(product);
            }
        }

        return availableProducts;
    }

    public int getStock(Product product) {
        return (Integer)this.products.get(product);
    }

    public Map<Product, Integer> getAllProductsStock() {
        return this.products;
    }
}

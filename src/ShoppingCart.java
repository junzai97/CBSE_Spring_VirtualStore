import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ShoppingCart {
    private Map<Product, Integer> shoppedProducts = new HashMap();

    public ShoppingCart() {
    }

    public void addItem(Product product, int quantity) {
        if (this.shoppedProducts.containsKey(product)) {
            quantity += (Integer)this.shoppedProducts.get(product);
        }

        this.shoppedProducts.put(product, quantity);
    }

    public Map<Product, Integer> getShoppedProducts() {
        return this.shoppedProducts;
    }

    public void removeItem(Product product, int quantity) {
        if (this.shoppedProducts.containsKey(product)) {
            int oldQuantity = (Integer)this.shoppedProducts.get(product);
            int newQuantity = oldQuantity - quantity;
            if (newQuantity > 0) {
                this.shoppedProducts.put(product, newQuantity);
            } else {
                this.shoppedProducts.remove(product);
            }

        }
    }

    public void empty() {
        this.shoppedProducts.clear();
    }

    public double getPrice() {
        double totalPrice = 0.0D;

        double subTotal;
        for(Iterator var4 = this.shoppedProducts.keySet().iterator(); var4.hasNext(); totalPrice += subTotal) {
            Product product = (Product)var4.next();
            double unitPrice = product.getPrice();
            int quantity = (Integer)this.shoppedProducts.get(product);
            subTotal = unitPrice * (double)quantity;
        }

        return totalPrice;
    }
}

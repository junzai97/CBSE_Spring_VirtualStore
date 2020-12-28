import java.util.HashMap;
import java.util.Map;

public class ReviewBoardBean {
    Map<String, String> reviews = new HashMap<>();

    public ReviewBoardBean(){
    }

    public void addReview(String product){
        reviews.put(product, null);
    }

    public void setReview(String product, String reviewMessage){
        if(reviews.containsKey(product)){
            reviews.put(product, reviewMessage);
        }
    }

    public Map<String, String> getReviews() {
        return this.reviews;
    }

    @Override
    public String toString() {
        return "ReviewBoard{" +
                "reviews=" + reviews +
                '}';
    }
}

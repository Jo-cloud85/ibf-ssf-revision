package ibf2023.ibf.revision.model;

import java.util.Date;

public class Product{
    private Integer id;
    private String title;
    private String description;
    private Integer price;
    private Double discountPercentage;
    private Double rating;
    private Integer stock;
    private String brand;
    private String category;
    private Date dated;
    private Integer buyCount;

    public Product() {}


    public Product(Integer id, String title, String description, Integer price, Double discountPercentage,
            Double rating, Integer stock, String brand, String category, Date dated, Integer buyCount) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.discountPercentage = discountPercentage;
        this.rating = rating;
        this.stock = stock;
        this.brand = brand;
        this.category = category;
        this.dated = dated;
        this.buyCount = buyCount;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Double getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(Double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Date getDated() {
        return dated;
    }

    public void setDated(Date dated) {
        this.dated = dated;
    }

    public Integer getBuyCount() {
        return buyCount;
    }
    
    public void setBuyCount(Integer buyCount) {
        this.buyCount = buyCount;
    }

    @Override
    public String toString() {
        return "Product [id=" + id + ", title=" + title + ", description=" + description + ", price=" + price
                + ", discountPercentage=" + discountPercentage + ", rating=" + rating + ", stock=" + stock + ", brand="
                + brand + ", category=" + category + ", dated=" + dated + "]";
    }
}

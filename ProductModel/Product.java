package ProductModel;

import java.io.Serializable;

public class Product implements Serializable {
    static final long serialVersionUID = -7034897190745766939L;
    private int id;
    private double price;
    private String name;
    private String description;
    protected String type;

    public Product(int price, String name, String description) {
        this.price = price;
        this.name = name;
        this.description = description;
    }

    public Product(double price, String name, String description, String type) {
        this.price = price;
        this.name = name;
        this.description = description;
        this.type = type;
    }

    public Product() {
        this.name = "";
        this.price = 0;
        this.description = "";
        this.type = "";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", price=" + price +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}

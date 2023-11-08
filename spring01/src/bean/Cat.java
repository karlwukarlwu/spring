package bean;

/**
 * Karl Rules!
 * 2023/10/31
 * now File Encoding is UTF-8
 */
public class Cat {
    Integer catId;
    String name;
    String price;
    public Cat() {
    }
    public Cat(Integer catId, String name, String price) {
        this.catId = catId;
        this.name = name;
        this.price = price;
    }
    public Integer getCatId() {
        return catId;
    }
    public void setCatId(Integer catId) {
        this.catId = catId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    String getPrice() {
        return price;
    }
    public void setPrice(String price) {
        this.price = price;
    }
    @Override
    public String toString() {
        return "Cat{" +
                "catId=" + catId +
                ", name='" + name + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}

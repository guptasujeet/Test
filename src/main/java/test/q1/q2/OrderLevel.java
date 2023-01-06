package test.q1.q2;

public class OrderLevel {

    private double price;
    private int orders;
    private int size;


    public OrderLevel(double price, int orders, int size) {
        this.price = price;
        this.orders = orders;
        this.size = size;
    }


    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    public int getOrders() {
        return orders;
    }

    public void setOrders(int orders) {
        this.orders = orders;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}

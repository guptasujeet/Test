package test.q1.q2;

class Order {
    private final int orderId;
    private final char side;

    private int size;
    private double price;

    public Order(int orderId, char side) {
        this.orderId = orderId;
        this.side = side;
    }

    public Order(int orderId, char side, int size, double price) {
        this.orderId = orderId;
        this.side = side;
        this.size = size;
        this.price = price;
    }

    public int getOrderId() {
        return orderId;
    }

    public char getSide() {
        return side;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
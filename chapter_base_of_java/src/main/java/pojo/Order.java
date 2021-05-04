package pojo;

import java.util.Objects;
import java.util.StringJoiner;

/**
 * Java Beans – это классы хранители информации. Т.к. результатом
 * работы приложения является манипуляция информацией то и есть
 * смысл выделить ответственность для группы классов, которые и
 * будут хранить эту информацию.
 *
 */
public class Order extends Entity {
    private long orderId;
    private double amount;

    public Order() {
    }

    public Order(long orderId, double amount) {
        this.orderId = orderId;
        this.amount = amount;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return orderId == order.orderId && Double.compare(order.amount, amount) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, amount);
    }

    //1-й вариант toString()
//    @Override
//    public String toString() {
//        return "Order{" +
//                "orderId=" + orderId +
//                ", amount=" + amount +
//                '}';
//    }

    //или 2-й вариант toString()
    @Override
    public String toString() {
        return new StringJoiner(",", Order.class.getSimpleName()
                + "[", "]")
                .add("orderId=" + orderId)
                .add("amount=" + amount)
                .toString();
    }
}

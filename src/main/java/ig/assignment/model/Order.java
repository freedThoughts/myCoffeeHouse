package ig.assignment.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Prakash on 26-09-2018.
 */
public class Order implements Serializable{

    private Integer customerId;
    private String orderId;
    private Set<Item> orders = new HashSet<>();
    private String timestamp;
    private Double totalCost;

    public Order() {
    }

    public Order(Integer customerId, String orderId) {
        this.customerId = customerId;
        this.orderId = orderId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Set<Item> getOrders() {
        return orders;
    }

    public void setOrders(Set<Item> orders) {
        this.orders = orders;
    }


    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public Double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;

        Order order = (Order) o;

        if (!getCustomerId().equals(order.getCustomerId())) return false;
        if (getOrderId() != null ? !getOrderId().equals(order.getOrderId()) : order.getOrderId() != null) return false;
        if (!getOrders().equals(order.getOrders())) return false;
        if (getTimestamp() != null ? !getTimestamp().equals(order.getTimestamp()) : order.getTimestamp() != null)
            return false;
        return getTotalCost() != null ? getTotalCost().equals(order.getTotalCost()) : order.getTotalCost() == null;
    }

    @Override
    public int hashCode() {
        int result = getCustomerId().hashCode();
        result = 31 * result + (getOrderId() != null ? getOrderId().hashCode() : 0);
        result = 31 * result + getOrders().hashCode();
        result = 31 * result + (getTimestamp() != null ? getTimestamp().hashCode() : 0);
        result = 31 * result + (getTotalCost() != null ? getTotalCost().hashCode() : 0);
        return result;
    }
}

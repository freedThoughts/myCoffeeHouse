package ig.assignment.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Prakash on 26-09-2018.
 */
public class Receipt implements Serializable{
    private Integer customerId;
    private String orderId;
    private Set<Item> items;
    private Double totalCost = 0.0;
    private String timestamp;

    public Receipt() {
    }

    public Receipt(Order order){
        this.customerId = order.getCustomerId();
        this.orderId = order.getOrderId();
        this.timestamp = order.getTimestamp();
        this.items = order.getOrders();
        this.items.stream().forEach(item -> {
            item.setCost(item.getQuantities() * item.getCostPerUnit());
            this.totalCost+= item.getCost();
        });
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

    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }

    public Double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Receipt)) return false;

        Receipt receipt = (Receipt) o;

        if (!getCustomerId().equals(receipt.getCustomerId())) return false;
        if (!getOrderId().equals(receipt.getOrderId())) return false;
        if (!getItems().equals(receipt.getItems())) return false;
        if (getTotalCost() != null ? !getTotalCost().equals(receipt.getTotalCost()) : receipt.getTotalCost() != null)
            return false;
        return timestamp != null ? timestamp.equals(receipt.timestamp) : receipt.timestamp == null;
    }

    @Override
    public int hashCode() {
        int result = getCustomerId().hashCode();
        result = 31 * result + getOrderId().hashCode();
        result = 31 * result + getItems().hashCode();
        result = 31 * result + (getTotalCost() != null ? getTotalCost().hashCode() : 0);
        result = 31 * result + (timestamp != null ? timestamp.hashCode() : 0);
        return result;
    }


}

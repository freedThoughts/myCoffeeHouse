package ig.assignment.model;

import java.io.Serializable;

/**
 * Created by Prakash on 27-09-2018.
 */
public class Item implements Serializable {
    private String coffeeName;
    private Integer quantities;
    private Double costPerUnit;
    private Double cost;

    public Item() {
    }

    public Item(String coffeeName, Integer quantities) {
        this.coffeeName = coffeeName;
        this.quantities = quantities;
    }

    public String getCoffeeName() {
        return coffeeName;
    }

    public void setCoffeeName(String coffeeName) {
        this.coffeeName = coffeeName;
    }

    public Integer getQuantities() {
        return quantities;
    }

    public void setQuantities(Integer quantities) {
        this.quantities = quantities;
    }

    public Double getCostPerUnit() {
        return costPerUnit;
    }

    public void setCostPerUnit(Double costPerUnit) {
        this.costPerUnit = costPerUnit;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item)) return false;

        Item item = (Item) o;

        if (!getCoffeeName().equals(item.getCoffeeName())) return false;
        if (!getQuantities().equals(item.getQuantities())) return false;
        if (getCostPerUnit() != null ? !getCostPerUnit().equals(item.getCostPerUnit()) : item.getCostPerUnit() != null)
            return false;
        return getCost() != null ? getCost().equals(item.getCost()) : item.getCost() == null;
    }

    @Override
    public int hashCode() {
        int result = getCoffeeName().hashCode();
        result = 31 * result + getQuantities().hashCode();
        result = 31 * result + (getCostPerUnit() != null ? getCostPerUnit().hashCode() : 0);
        result = 31 * result + (getCost() != null ? getCost().hashCode() : 0);
        return result;
    }
}

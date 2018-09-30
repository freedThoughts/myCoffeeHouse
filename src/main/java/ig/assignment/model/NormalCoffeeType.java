package ig.assignment.model;

import java.io.Serializable;

/**
 * Created by Prakash on 25-09-2018.
 */
public class NormalCoffeeType implements ICoffeeType, Serializable{

    private String coffeeName;
    private String coffeeDescription;
    private Integer totalServing;
    private Integer totalServed = 0;
    private Double costPerUnit;

    public NormalCoffeeType() {}

    public NormalCoffeeType(String coffeeName, String coffeeDescription, Integer totalServing, Double costPerUnit) {
        this.coffeeName = coffeeName;
        this.coffeeDescription = coffeeDescription;
        this.totalServing = totalServing;
        this.costPerUnit = costPerUnit;
    }

    public String getCoffeeName() {
        return coffeeName;
    }

    public void setCoffeeName(String coffeeName) {
        this.coffeeName = coffeeName;
    }

    public String getCoffeeDescription() {
        return coffeeDescription;
    }

    public void setCoffeeDescription(String coffeeDescription) {
        this.coffeeDescription = coffeeDescription;
    }

    public Integer getTotalServing() {
        return totalServing;
    }

    public void setTotalServing(Integer totalServing) {
        this.totalServing = totalServing;
    }

    public Integer getTotalServed() {
        return totalServed;
    }

    public void setTotalServed(Integer totalServed) {
        this.totalServed = totalServed;
    }

    public Double getCostPerUnit() {
        return costPerUnit;
    }

    public void setCostPerUnit(Double costPerUnit) {
        this.costPerUnit = costPerUnit;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NormalCoffeeType)) return false;

        NormalCoffeeType that = (NormalCoffeeType) o;

        return getCoffeeName().equals(that.getCoffeeName());
    }

    @Override
    public int hashCode() {
        return getCoffeeName().hashCode();
    }
}

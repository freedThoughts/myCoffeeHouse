package ig.assignment.model;

/**
 * Created by Prakash on 25-09-2018.
 */
public interface ICoffeeType {
    String getCoffeeName();
    Double getCostPerUnit();
    Integer getTotalServing();
    Integer getTotalServed();
    void setTotalServed(Integer totalServed);
    void setCostPerUnit(Double costPerUnit);
}

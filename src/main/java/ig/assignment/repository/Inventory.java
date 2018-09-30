package ig.assignment.repository;

import com.sun.org.apache.regexp.internal.RE;
import ig.assignment.model.*;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Prakash on 25-09-2018.
 */
public class Inventory {
    private List<ICustomer> customerList = new ArrayList<>();
    private Map<String, ICoffeeType> coffeeTypeMap = new HashMap<>();
    private List<Order> orderList = new ArrayList<>();
    private Map<String, Receipt> orderReceiptMap = new HashMap<>();
    private Map<Integer, Receipt> customerIdReceiptMap = new HashMap<>();

    {
        // Added Sample customers
        ICustomer customer1 = new NormalCustomer(100001, "Prakash", 12345);
        ICustomer customer2 = new NormalCustomer(100002, "Manish", 987654);
        customerList.add(customer1);
        customerList.add(customer2);

        // Added Sample coffeeTypes
        ICoffeeType coffeeType1 = new NormalCoffeeType("Latte", "Description - Latte", 10, 100.00);
        ICoffeeType coffeeType2 = new NormalCoffeeType("Cappuccino", "Description - Cappuccino", 20, 102.00);
        coffeeTypeMap.put(coffeeType1.getCoffeeName(), coffeeType1);
        coffeeTypeMap.put(coffeeType2.getCoffeeName(), coffeeType2);

        // Added Sample Order
        Order order = new Order(customer1.getCustomerId(), "ORDER-1001");
        Set<Item> itemSet = new HashSet<>();
        order.setOrders(itemSet);

        // Added Item-1
        Item item1 = new Item("Latte", 2);
        ICoffeeType coffeeTypeA = this.getCoffeeTypeMap().get(item1.getCoffeeName());
        coffeeTypeA.setTotalServed(coffeeTypeA.getTotalServed() + item1.getQuantities());
        item1.setCostPerUnit(coffeeTypeA.getCostPerUnit());
        item1.setCost(item1.getQuantities() * item1.getCostPerUnit());

        // Added Item-2
        Item item2 = new Item("Cappuccino", 3);
        ICoffeeType coffeeTypeB = this.getCoffeeTypeMap().get(item2.getCoffeeName());
        coffeeTypeB.setTotalServed(coffeeTypeB.getTotalServed() + item2.getQuantities());
        item2.setCostPerUnit(coffeeTypeB.getCostPerUnit());
        item2.setCost(item2.getQuantities() * item2.getCostPerUnit());

        itemSet.add(item1);
        itemSet.add(item2);
        order.setTotalCost(order.getOrders().stream().mapToDouble(item -> item.getCost()).sum());
        order.setTimestamp(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        orderList.add(order);
        Receipt receipt = new Receipt(order);
        orderReceiptMap.put(order.getOrderId(), receipt);
        customerIdReceiptMap.put(order.getCustomerId(), receipt);
    }

    public List<ICustomer> getCustomerList() {
        return customerList;
    }

    public void setCustomerList(List<ICustomer> customerList) {
        this.customerList = customerList;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }


    public Map<String, Receipt> getOrderReceiptMap() {
        return orderReceiptMap;
    }

    public void setOrderReceiptMap(Map<String, Receipt> orderReceiptMap) {
        this.orderReceiptMap = orderReceiptMap;
    }


    public Map<String, ICoffeeType> getCoffeeTypeMap() {
        return coffeeTypeMap;
    }

    public void setCoffeeTypeMap(Map<String, ICoffeeType> coffeeTypeMap) {
        this.coffeeTypeMap = coffeeTypeMap;
    }

    public Map<Integer, Receipt> getCustomerIdReceiptMap() {
        return customerIdReceiptMap;
    }

    public void setCustomerIdReceiptMap(Map<Integer, Receipt> customerIdReceiptMap) {
        this.customerIdReceiptMap = customerIdReceiptMap;
    }
}

package ig.assignment.service;

import ig.assignment.model.ICoffeeType;
import ig.assignment.model.ICustomer;
import ig.assignment.model.Order;
import ig.assignment.model.Receipt;
import ig.assignment.repository.Inventory;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Prakash on 25-09-2018.
 */
@Service("coffeeHouseService")
public class CoffeeHouseServiceImpl implements ICoffeeHouseService{

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private Inventory inventory = new Inventory();
    public ICustomer getCustomerByName(String name){
        Optional<ICustomer> optional =  inventory.getCustomerList().stream().filter( cus ->
            cus.getCustomerName().equals(name)).findFirst();
        return optional.isPresent() ? optional.get(): null;
    }

    public ICustomer getCustomerByPhoneNumber(Integer phoneNumber){
        Optional<ICustomer> optional =  inventory.getCustomerList().stream().filter( cus ->
                cus.getPhoneNumber().equals(phoneNumber)).findFirst();
        return optional.isPresent() ? optional.get() : null;
    }

    public void addCustomer(ICustomer customer) {
        inventory.getCustomerList().add(customer);
    }

    public void addCoffeeType(ICoffeeType coffeeType) {
        inventory.getCoffeeTypeMap().put(coffeeType.getCoffeeName(), coffeeType);
    }

    public ICoffeeType getCoffeeDetailsByName(String coffeeName){
        return inventory.getCoffeeTypeMap().get(coffeeName);
    }

    public String addOrder(Order order){
        order.setTimestamp(simpleDateFormat.format(new Date()));
        if (order.getOrderId() == null){
            order.setOrderId(UUID.randomUUID().toString());
        }
        order.getOrders().stream().forEach(
                item -> {
                    ICoffeeType coffeeType = inventory.getCoffeeTypeMap().get(item.getCoffeeName());
                    coffeeType.setTotalServed(coffeeType.getTotalServed() + item.getQuantities());
                    item.setCostPerUnit(coffeeType.getCostPerUnit());
                    item.setCost(item.getQuantities() * item.getCostPerUnit());
                });
        order.setTotalCost(order.getOrders().stream().mapToDouble(item -> item.getCost()).sum());
        if(!inventory.getOrderList().add(order))
            return null;
        Receipt receipt = new Receipt(order);
        inventory.getOrderReceiptMap().put(order.getOrderId(), receipt);
        inventory.getCustomerIdReceiptMap().put(order.getCustomerId(), receipt);
        return order.getOrderId();
    }

    public Order getOrderByOrderId(String orderId){
        Optional<Order> optional = inventory.getOrderList().stream().filter(order -> order.getOrderId().equals(orderId)).findFirst();
        return optional.isPresent() ? optional.get(): null;
    }

    public Receipt getReceiptByOrderId(String orderId){
        return inventory.getOrderReceiptMap().get(orderId);
    }

    public Receipt getReceiptByCustomerId(Integer customerId) {
        return inventory.getCustomerIdReceiptMap().get(customerId);
    }

    public boolean isCustomerDataAvailable(Integer customerId){
        return inventory.getCustomerList().stream().filter(customer -> customer.getCustomerId().equals(customerId)).count() == 1;
    }

    public boolean isCoffeeTypeAvailable(String coffeeName){
        return inventory.getCoffeeTypeMap().get(coffeeName) != null;
    }

    public Collection<ICoffeeType> generateReport(){
        return inventory.getCoffeeTypeMap().values();
    }

}

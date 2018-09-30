package ig.assignment.service;

import ig.assignment.model.ICoffeeType;
import ig.assignment.model.ICustomer;
import ig.assignment.model.Order;
import ig.assignment.model.Receipt;

import java.util.Collection;
import java.util.Set;

/**
 * Created by Prakash on 25-09-2018.
 */
public interface ICoffeeHouseService {
    ICustomer getCustomerByName(String name);
    ICustomer getCustomerByPhoneNumber(Integer phoneNumber);
    void addCustomer(ICustomer customer);
    void addCoffeeType(ICoffeeType coffeeType);
    ICoffeeType getCoffeeDetailsByName(String coffeeName);
    String addOrder(Order order);
    Order getOrderByOrderId(String orderId);
    Receipt getReceiptByOrderId(String orderId);
    Receipt getReceiptByCustomerId(Integer customerId);
    boolean isCustomerDataAvailable(Integer customerId);
    boolean isCoffeeTypeAvailable(String coffeeName);
    Collection<ICoffeeType> generateReport();
}

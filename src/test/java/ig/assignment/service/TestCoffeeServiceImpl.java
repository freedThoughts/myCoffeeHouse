package ig.assignment.service;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Prakash on 28-09-2018.
 */

public class TestCoffeeServiceImpl {


    private CoffeeHouseServiceImpl coffeeHouseServiceImpl;

    @Before
    public void runBeforeTestMethod(){
        this.coffeeHouseServiceImpl = new CoffeeHouseServiceImpl();
    }

    @Test
    public void getCustomerByName(){
        String name = "Prakash";
        assertNotNull(coffeeHouseServiceImpl.getCustomerByName(name));

        name = "XYZ";
        assertNull(coffeeHouseServiceImpl.getCustomerByName(name));
    }

    @Test
    public void getCustomerByPhoneNumber(){
        Integer phoneNumber = 12345;
        assertNotNull(coffeeHouseServiceImpl.getCustomerByPhoneNumber(phoneNumber));

        phoneNumber = 000000;
        assertNull(coffeeHouseServiceImpl.getCustomerByPhoneNumber(phoneNumber));
    }

    @Test
    public void addCustomer(){
        //TODO:
    }

    @Test
    public void addCoffeeType(){
        // TODO :
    }

    @Test
    public void getCoffeeDetailsByName(){
        String coffeeName = "Latte";
        assertNotNull(coffeeHouseServiceImpl.getCoffeeDetailsByName(coffeeName));

        coffeeName = "XYZ";
        assertNull(coffeeHouseServiceImpl.getCustomerByName(coffeeName));
    }

    @Test
    public void addOrder(){
        // TODO :
    }

    @Test
    public void getOrderByOrderId(){
        String orderId = "ORDER-1001";
        assertNotNull(coffeeHouseServiceImpl.getOrderByOrderId(orderId));

        orderId = "XYZ";
        assertNull(coffeeHouseServiceImpl.getOrderByOrderId(orderId));
    }

    @Test
    public void getReceiptByOrderId(){
        String orderId = "ORDER-1001";
        assertNotNull(coffeeHouseServiceImpl.getReceiptByOrderId(orderId));

        orderId = "XYZ";
        assertNull(coffeeHouseServiceImpl.getReceiptByOrderId(orderId));
    }

    @Test
    public void getReceiptByCustomerId(){
        Integer customerId = 100001;
        assertNotNull(coffeeHouseServiceImpl.getReceiptByCustomerId(customerId));

        customerId = 1026566;
        assertNull(coffeeHouseServiceImpl.getReceiptByCustomerId(customerId));
    }

    @Test
    public void isCustomerDataAvailable(){
        Integer customerId = 100001;
        assertTrue(coffeeHouseServiceImpl.isCustomerDataAvailable(customerId));

        customerId = 15656436;
        assertFalse(coffeeHouseServiceImpl.isCustomerDataAvailable(customerId));
    }

    @Test
    public void isCoffeeTypeAvailable(){
        String coffeeName = "Latte";
        assertTrue(coffeeHouseServiceImpl.isCoffeeTypeAvailable(coffeeName));

        coffeeName = "XYZ";
        assertFalse(coffeeHouseServiceImpl.isCoffeeTypeAvailable(coffeeName));
    }

    @Test
    public void generateReport(){
        assertNotNull(coffeeHouseServiceImpl.generateReport());
    }
}

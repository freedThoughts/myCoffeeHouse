package ig.assignment.controller;

import ig.assignment.model.*;
import ig.assignment.service.ICoffeeHouseService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collection;

/**
 * Created by Prakash on 25-09-2018.
 */

@RequestMapping("/coffeeHouseController")
@RestController
public class CoffeeHouseController {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(CoffeeHouseController.class);

    @Autowired
    ICoffeeHouseService coffeeHouseService;

    @RequestMapping(method = RequestMethod.GET, value = "/customerByName/{customerName}")
    public ResponseEntity<ICustomer> getCustomerByName(@PathVariable("customerName") String customerName) {
        ICustomer customer =  coffeeHouseService.getCustomerByName(customerName);
        if(customer == null) {
            logger.info("No Customer available with given customerName");
            return new ResponseEntity<ICustomer>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<ICustomer>(customer, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/customerByPhoneNumber/{phoneNumber}")
    public ResponseEntity<ICustomer> getCustomerByPhoneNumber(@PathVariable("phoneNumber") Integer phoneNumber) {
        ICustomer customer =  coffeeHouseService.getCustomerByPhoneNumber(phoneNumber);
        if(customer == null){
            logger.info("No Customer available with given phoneNumber");
            return new ResponseEntity<ICustomer>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<ICustomer>(customer, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/addCustomer")
    public ResponseEntity<String> addCustomer(@RequestBody NormalCustomer customer, UriComponentsBuilder ucBuilder){
        HttpHeaders httpHeaders = new HttpHeaders();
        if (customer.getCustomerId() == null || customer.getCustomerName() == null || customer.getPhoneNumber() == null){
            logger.info("Customer details are not complete");
            return new ResponseEntity<String>(httpHeaders, HttpStatus.NOT_ACCEPTABLE);
        }
        if(coffeeHouseService.isCustomerDataAvailable(customer.getCustomerId())){
            logger.info("A Customer is already available with given customerId");
            return new ResponseEntity<String>(httpHeaders, HttpStatus.CONFLICT);
        }
        coffeeHouseService.addCustomer(customer);
        httpHeaders.setLocation(ucBuilder.path("/coffeeHouseController/customerByName/{customerName}").
                buildAndExpand(customer.getCustomerName()).toUri());
        return new ResponseEntity<String>(httpHeaders, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/coffeeTypeByName/{coffeeName}")
    public ResponseEntity<ICoffeeType> getCoffeeTypeByName(@PathVariable("coffeeName") String coffeeName){
        ICoffeeType coffeeType = coffeeHouseService.getCoffeeDetailsByName(coffeeName);
        if(coffeeType == null) {
            logger.info("No coffeeType available with given name");
            return new ResponseEntity<ICoffeeType>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<ICoffeeType>(coffeeType, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/addCoffeeType")
    public ResponseEntity<String> addCoffeeType(@RequestBody NormalCoffeeType coffeeType, UriComponentsBuilder uriComponentsBuilder){
        HttpHeaders httpHeaders = new HttpHeaders();
        if (coffeeType.getCoffeeName() == null || coffeeType.getTotalServing() == null || coffeeType.getCostPerUnit() == null){
            logger.info("CoffeeType details are not complete");
            return new ResponseEntity<String>(HttpStatus.NOT_ACCEPTABLE);
        }
        if (coffeeHouseService.isCoffeeTypeAvailable(coffeeType.getCoffeeName())){
            logger.info("A coffeeType is already available with given name");
            return new ResponseEntity<String>(httpHeaders, HttpStatus.CONFLICT);
        }
        coffeeHouseService.addCoffeeType(coffeeType);

        httpHeaders.setLocation(uriComponentsBuilder.path("/coffeeHouseController/coffeeTypeByName/{coffeeName}").
                buildAndExpand(coffeeType.getCoffeeName()).toUri());
        return new ResponseEntity<String>(httpHeaders, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/orderByOrderId/{orderId}")
    public ResponseEntity<Order> getOrderByOrderId(@PathVariable("orderId") String orderId){
        Order order = coffeeHouseService.getOrderByOrderId(orderId);
        if (order == null) {
            logger.info("No Order available with  given orderId");
            return new ResponseEntity<Order>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<Order>(order, HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.POST, value = "/addOrder")
    public ResponseEntity<String> addOrder(@RequestBody Order order, UriComponentsBuilder uriComponentsBuilder) {
        HttpHeaders httpHeaders = new HttpHeaders();
        if (order.getCustomerId() == null || order.getOrders().isEmpty()) {
            logger.info("Order details are not complete");
            return new ResponseEntity<String>(httpHeaders, HttpStatus.NOT_ACCEPTABLE);
        }
        if (!coffeeHouseService.isCustomerDataAvailable(order.getCustomerId())){
            logger.info("Customer data is not available. Please create the customer first");
            httpHeaders.setLocation(uriComponentsBuilder.path("/addCustomer").buildAndExpand().toUri());
            return new ResponseEntity<String>(httpHeaders, HttpStatus.NOT_ACCEPTABLE);
        }
        String orderId = coffeeHouseService.addOrder(order);
        if (orderId == null){
            logger.info("Order is not created. Please check the details provided.");
            return new ResponseEntity<String>(httpHeaders, HttpStatus.NOT_ACCEPTABLE);
        }

        httpHeaders.setLocation(uriComponentsBuilder.path("/coffeeHouseController/orderByOrderId/{orderId}")
        .buildAndExpand(orderId).toUri());
        return new ResponseEntity<String>(httpHeaders, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/receiptByOrderId/{orderId}")
    public ResponseEntity<Receipt> getReceiptByOrderId(@PathVariable("orderId") String orderId){
        Receipt receipt = coffeeHouseService.getReceiptByOrderId(orderId);
        if(receipt == null){
            logger.info("No receipt found against given orderId. Check whether order is created");
            return new ResponseEntity<Receipt>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<Receipt>(receipt, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/receiptByCustomerId/{customerId}")
    public ResponseEntity<Receipt> getReceiptByCustomerId(@PathVariable("customerId") Integer customerId) {
        Receipt receipt = coffeeHouseService.getReceiptByCustomerId(customerId);
        if(receipt == null){
            logger.info("No receipt found against given customerId. Check whether order is created");
            return new ResponseEntity<Receipt>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Receipt>(receipt, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/generateReport")
    public ResponseEntity<Collection<ICoffeeType>> generateReport(){
        return new ResponseEntity<Collection<ICoffeeType>>(coffeeHouseService.generateReport(), HttpStatus.OK);
    }
}

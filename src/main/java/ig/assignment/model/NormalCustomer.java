package ig.assignment.model;

import java.io.Serializable;

/**
 * Created by Prakash on 25-09-2018.
 */
public class NormalCustomer implements ICustomer, Serializable {

    private Integer customerId;
    private String customerName;
    private Integer phoneNumber;
    public NormalCustomer() {}
    public NormalCustomer(Integer customerId, String customerName, Integer phoneNumber) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.phoneNumber = phoneNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Integer getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NormalCustomer)) return false;

        NormalCustomer that = (NormalCustomer) o;

        if (!getCustomerId().equals(that.getCustomerId())) return false;
        if (!getCustomerName().equals(that.getCustomerName())) return false;
        return getPhoneNumber() != null ? getPhoneNumber().equals(that.getPhoneNumber()) : that.getPhoneNumber() == null;
    }

    @Override
    public int hashCode() {
        int result = getCustomerId().hashCode();
        result = 31 * result + getCustomerName().hashCode();
        result = 31 * result + (getPhoneNumber() != null ? getPhoneNumber().hashCode() : 0);
        return result;
    }

}

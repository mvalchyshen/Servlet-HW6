package ua.goit.dao.model;

public class CustomersDAO {
    private long customerId;
    private String customerName;

    public CustomersDAO() {
    }

    public CustomersDAO(long customerId, String customerName) {
        this.customerId = customerId;
        this.customerName = customerName;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    @Override
    public String toString() {
        return "Customers{" +
                "customerId=" + customerId +
                ", customerName='" + customerName + '\'' +
                '}';
    }
}

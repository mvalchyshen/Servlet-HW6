package ua.goit.dto;

import java.util.List;

public class CustomersDTO {
    private long customerId;
    private String customerName;
    private List<Long> projectIds;

    public CustomersDTO() {
    }

    public CustomersDTO(long customerId, String customerName) {
        this.customerId = customerId;
        this.customerName = customerName;
    }

    public CustomersDTO(long customerId, String customerName, List<Long> projectIds) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.projectIds = projectIds;
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

    public List<Long> getProjectIds() {
        return projectIds;
    }

    public void setProjectIds(List<Long> projectIds) {
        this.projectIds = projectIds;
    }

    @Override
    public String toString() {
        return "CustomersDTO{" +
                "customerId=" + customerId +
                ", customerName='" + customerName + '\'' +
                ", projectId=" + projectIds +
                '}';
    }
}

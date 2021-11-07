package ua.goit.dao.model;

public class CustomersAndCompaniesDAO {
    private long companyId;
    private long customerId;
    private long projectId;

    public CustomersAndCompaniesDAO() {
    }

    public CustomersAndCompaniesDAO(long companyId, long customerId, long projectId) {
        this.companyId = companyId;
        this.customerId = customerId;
        this.projectId = projectId;
    }

    public long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(long companyId) {
        this.companyId = companyId;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    @Override
    public String toString() {
        return "CustomersAndCompaniesDAO{" +
                "companyId=" + companyId +
                ", customerId=" + customerId +
                ", projectId=" + projectId +
                '}';
    }
}

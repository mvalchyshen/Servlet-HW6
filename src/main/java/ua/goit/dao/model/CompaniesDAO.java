package ua.goit.dao.model;

public class CompaniesDAO {
    private long companyId;
    private String companyName;
    private int numberOfDevelopers;

    public CompaniesDAO() {
    }

    public CompaniesDAO(long companyId, String companyName, int numberOfDevelopers) {
        this.companyId = companyId;
        this.companyName = companyName;
        this.numberOfDevelopers = numberOfDevelopers;
    }

    public long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(long companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public int getNumberOfDevelopers() {
        return numberOfDevelopers;
    }

    public void setNumberOfDevelopers(int numberOfDevelopers) {
        this.numberOfDevelopers = numberOfDevelopers;
    }

    @Override
    public String toString() {
        return "Companies{" +
                "company_id=" + companyId +
                ", company_name='" + companyName + '\'' +
                ", number_of_developers=" + numberOfDevelopers +
                '}';
    }
}

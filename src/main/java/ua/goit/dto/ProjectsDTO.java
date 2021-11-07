package ua.goit.dto;

import java.time.LocalDate;
import java.util.List;

public class ProjectsDTO {
    private long projectId;
    private String projectName;
    private String stage;
    private int timePeriod;
    private int coast;
    private int numberOfDevelopers;
    private LocalDate dateOfBeginning;
    private List<Long> developerIds;
    private List<Long> companyIds;
    private List<Long> customerIds;


    public ProjectsDTO() {
    }

    public ProjectsDTO(long projectId, String projectName, String stage, int timePeriod, int coast,
                       int numberOfDevelopers, LocalDate dateOfBeginning) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.stage = stage;
        this.timePeriod = timePeriod;
        this.coast = coast;
        this.numberOfDevelopers = numberOfDevelopers;
        this.dateOfBeginning = dateOfBeginning;
    }

    public ProjectsDTO(long projectId, String projectName, String stage, int timePeriod, int coast,
                       int numberOfDevelopers, LocalDate dateOfBeginning, List<Long> developerIds, List<Long> companyIds,
                       List<Long> customerIds) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.stage = stage;
        this.timePeriod = timePeriod;
        this.coast = coast;
        this.numberOfDevelopers = numberOfDevelopers;
        this.dateOfBeginning = dateOfBeginning;
        this.developerIds = developerIds;
        this.companyIds = companyIds;
        this.customerIds = customerIds;
    }

    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public int getTimePeriod() {
        return timePeriod;
    }

    public void setTimePeriod(int timePeriod) {
        this.timePeriod = timePeriod;
    }

    public int getCoast() {
        return coast;
    }

    public void setCoast(int coast) {
        this.coast = coast;
    }

    public int getNumberOfDevelopers() {
        return numberOfDevelopers;
    }

    public void setNumberOfDevelopers(int numberOfDevelopers) {
        this.numberOfDevelopers = numberOfDevelopers;
    }

    public LocalDate getDateOfBeginning() {
        return dateOfBeginning;
    }

    public void setDateOfBeginning(LocalDate dateOfBeginning) {
        this.dateOfBeginning = dateOfBeginning;
    }

    public List<Long> getDeveloperIds() {
        return developerIds;
    }

    public void setDeveloperIds(List<Long> developerIds) {
        this.developerIds = developerIds;
    }

    public List<Long> getCompanyIds() {
        return companyIds;
    }

    public void setCompanyIds(List<Long> companyIds) {
        this.companyIds = companyIds;
    }

    public List<Long> getCustomerIds() {
        return customerIds;
    }

    public void setCustomerIds(List<Long> customerIds) {
        this.customerIds = customerIds;
    }

    @Override
    public String toString() {
        return "ProjectsDTO{" +
                "projectId=" + projectId +
                ", projectName='" + projectName + '\'' +
                ", stage='" + stage + '\'' +
                ", timePeriod=" + timePeriod +
                ", coast=" + coast +
                ", numberOfDevelopers=" + numberOfDevelopers +
                ", dateOfBeginning=" + dateOfBeginning +
                ", developerId=" + developerIds +
                ", companyId=" + companyIds +
                ", customerId=" + customerIds +
                '}';
    }
}

package ua.goit.dao.model;

import java.time.LocalDate;

public class ProjectsDAO {
    private long projectId;
    private String projectName;
    private String stage;
    private int timePeriod;
    private int coast;
    private int numberOfDevelopers;
    private LocalDate dateOfBeginning;

    public ProjectsDAO() {
    }

    public ProjectsDAO(long projectId, String projectName, String stage, int timePeriod, int coast,
                       int numberOfDevelopers, LocalDate dateOfBeginning) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.stage = stage;
        this.timePeriod = timePeriod;
        this.coast = coast;
        this.numberOfDevelopers = numberOfDevelopers;
        this.dateOfBeginning = dateOfBeginning;
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

    @Override
    public String toString() {
        return "Projects{" +
                "projectId=" + projectId +
                ", projectName='" + projectName + '\'' +
                ", stage='" + stage + '\'' +
                ", timePeriod=" + timePeriod +
                ", coast=" + coast +
                ", numberOfDevelopers=" + numberOfDevelopers +
                ", dateOfBegining=" + dateOfBeginning +
                '}';
    }
}

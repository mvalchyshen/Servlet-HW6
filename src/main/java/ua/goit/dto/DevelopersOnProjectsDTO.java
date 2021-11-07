package ua.goit.dto;

public class DevelopersOnProjectsDTO {
    private long developerId;
    private long projectId;

    public DevelopersOnProjectsDTO() {
    }

    public DevelopersOnProjectsDTO(long developerId, long projectId) {
        this.developerId = developerId;
        this.projectId = projectId;
    }

    public long getDeveloperId() {
        return developerId;
    }

    public void setDeveloperId(long developerId) {
        this.developerId = developerId;
    }

    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    @Override
    public String toString() {
        return "DevelopersOnProjectsDTO{" +
                "developerId=" + developerId +
                ", projectId=" + projectId +
                '}';
    }
}

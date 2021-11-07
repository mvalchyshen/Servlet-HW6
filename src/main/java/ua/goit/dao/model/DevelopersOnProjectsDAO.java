package ua.goit.dao.model;

public class DevelopersOnProjectsDAO {
    private long developerId;
    private long projectId;

    public DevelopersOnProjectsDAO() {
    }

    public DevelopersOnProjectsDAO(long developerId, long projectId) {
        this.developerId = developerId;
        this.projectId = projectId;
    }

    public long getDeveloperId() {
        return developerId;
    }

    public void setDeveloperId(long developerId) {
        this.developerId = developerId;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    @Override
    public String toString() {
        return "DevelopersOnProjectsDAO{" +
                "developerId=" + developerId +
                ", projectId=" + projectId +
                '}';
    }
}

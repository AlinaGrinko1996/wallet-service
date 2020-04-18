package ua.nure.hrynko.walletservice.dto;

public class SonarPayloadDTO {
    private String serverUrl;
    private String taskId;
    private String status;
    private String analysedAt;
    private String revision;
    private String changedAt;
    private ProjectDTO project;

    public String getServerUrl() {
        return serverUrl;
    }

    public void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAnalysedAt() {
        return analysedAt;
    }

    public void setAnalysedAt(String analysedAt) {
        this.analysedAt = analysedAt;
    }

    public String getRevision() {
        return revision;
    }

    public void setRevision(String revision) {
        this.revision = revision;
    }

    public String getChangedAt() {
        return changedAt;
    }

    public void setChangedAt(String changedAt) {
        this.changedAt = changedAt;
    }

    public ProjectDTO getProject() {
        return project;
    }

    public void setProject(ProjectDTO project) {
        this.project = project;
    }
}

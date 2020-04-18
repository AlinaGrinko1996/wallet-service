package ua.nure.hrynko.walletservice.entities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SonarIssue {
    String discoveredCommit;
    String key;

    String rule;
    String severity;
    String component;
    String project;
    int line;
    int startLine;
    int endLine;
    String status;
    String message;
    String effort;
    String debt;

    //timestamp "2014-08-30T02:35:15+0200"
    // String creationDate;
    // String updateDate;
    long creationDate;
    long updateDate;

    String type;

    public SonarIssue(String discoveredCommit, String key, String rule, String severity, String component,
                      String project, int line, int startLine, int endLine, String status, String message,
                      String effort, String debt, String creationDate, String updateDate, String type)
            throws ParseException {

        this.discoveredCommit = discoveredCommit;
        this.key = key;
        this.rule = rule;
        this.severity = severity;
        this.component = component;
        this.project = project;
        this.line = line;
        this.startLine = startLine;
        this.endLine = endLine;
        this.status = status;
        this.message = message;
        this.effort = effort;
        this.debt = debt;
        this.creationDate = getMillisecondsFromString(creationDate);
        this.updateDate = getMillisecondsFromString(updateDate);
        this.type = type;
    }

    public SonarIssue(String discoveredCommit) {
        this.discoveredCommit = discoveredCommit;
    }

    public String getDiscoveredCommit() {
        return discoveredCommit;
    }

    public void setDiscoveredCommit(String discoveredCommit) {
        this.discoveredCommit = discoveredCommit;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public int getStartLine() {
        return startLine;
    }

    public void setStartLine(int startLine) {
        this.startLine = startLine;
    }

    public int getEndLine() {
        return endLine;
    }

    public void setEndLine(int entLine) {
        this.endLine = entLine;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getEffort() {
        return effort;
    }

    public void setEffort(String effort) {
        this.effort = effort;
    }

    public String getDebt() {
        return debt;
    }

    public void setDebt(String debt) {
        this.debt = debt;
    }


    public long getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) throws ParseException {
        this.creationDate = getMillisecondsFromString(creationDate);
    }

    public long getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) throws ParseException {
        this.updateDate = getMillisecondsFromString(updateDate);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private static long getMillisecondsFromString(String creationDate) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date date = format.parse(creationDate);
        return date.getTime();
    }
}

package org.automation.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by SSarker on 9/2/2018.
 */
public class Bug {
    private Long id;
    private String title;
    private String summary;
    private String exceptions;
    private String comments;
    private String foundDateTime;
    private String tags;
    private String priority;
    private String servility;
    private String attachmentPath;
    private String description;

    public Bug(Long id, String title, String summary, String exceptions, String comments, String foundDateTime, String tags, String priority, String servility, String attachmentPath, String description) {
        this.id = id;
        this.title = title;
        this.summary = summary;
        this.exceptions = exceptions;
        this.comments = comments;
        this.foundDateTime = foundDateTime;
        this.tags = tags;
        this.priority = priority;
        this.servility = servility;
        this.attachmentPath = attachmentPath;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Bug{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", summary='" + summary + '\'' +
                ", exceptions='" + exceptions + '\'' +
                ", comments='" + comments + '\'' +
                ", foundDateTime='" + foundDateTime + '\'' +
                ", tags='" + tags + '\'' +
                ", priority='" + priority + '\'' +
                ", servility='" + servility + '\'' +
                ", attachmentPath='" + attachmentPath + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public Bug() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getExceptions() {
        return exceptions;
    }

    public void setExceptions(String exceptions) {
        this.exceptions = exceptions;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getFoundDateTime() {
        return foundDateTime;
    }

    public void setFoundDateTime(String foundDateTime) {
        this.foundDateTime = foundDateTime;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getServility() {
        return servility;
    }

    public void setServility(String servility) {
        this.servility = servility;
    }

    public String getAttachmentPath() {
        return attachmentPath;
    }

    public void setAttachmentPath(String attachmentPath) {
        this.attachmentPath = attachmentPath;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static Bug getABug() {
        return new Bug(null, "title", "summary", "description", "/home/shantonu", "RuntimeException", "from code", "Today", "rest assure", "NORM", "BLOCKER");
    }


    public boolean equalValueWithoutID(Object o) {

        Bug bug = (Bug) o;
        if (!getTitle().equals(bug.getTitle())) return false;
        if (!getSummary().equals(bug.getSummary())) return false;
        if (!getExceptions().equals(bug.getExceptions())) return false;
        if (!getComments().equals(bug.getComments())) return false;
        if (!getFoundDateTime().equals(bug.getFoundDateTime())) return false;
        if (!getTags().equals(bug.getTags())) return false;
        if (!getPriority().equals(bug.getPriority())) return false;
        if (!getServility().equals(bug.getServility())) return false;
        if (!getAttachmentPath().equals(bug.getAttachmentPath())) return false;
        return getDescription().equals(bug.getDescription());
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + getTitle().hashCode();
        result = 31 * result + getSummary().hashCode();
        result = 31 * result + getExceptions().hashCode();
        result = 31 * result + getComments().hashCode();
        result = 31 * result + getFoundDateTime().hashCode();
        result = 31 * result + getTags().hashCode();
        result = 31 * result + getPriority().hashCode();
        result = 31 * result + getServility().hashCode();
        result = 31 * result + getAttachmentPath().hashCode();
        result = 31 * result + getDescription().hashCode();
        return result;
    }
}

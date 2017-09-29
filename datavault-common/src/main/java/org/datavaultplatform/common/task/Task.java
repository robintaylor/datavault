package org.datavaultplatform.common.task;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import java.util.Map;
import org.datavaultplatform.common.model.FileStore;
import org.datavaultplatform.common.model.ArchiveStore;
import org.datavaultplatform.common.model.Job;

// A generic task container

@JsonIgnoreProperties(ignoreUnknown = true)
public class Task {

    protected String taskClass;
    protected String jobID;
    Map<String, String> properties;
    protected FileStore userFileStore;
    protected List<ArchiveStore> archiveFileStores;

    private boolean isRedeliver;

    public Task() {};
    public Task(Job job,
                Map<String, String> properties,
                FileStore userFileStore,
                List<ArchiveStore> archiveFileStores) {

        this.jobID = job.getID();
        this.taskClass = job.getTaskClass();
        this.properties = properties;
        this.userFileStore = userFileStore;
        this.archiveFileStores = archiveFileStores;
    }

    public String getJobID() { return jobID; }

    public void setJobID(String jobID) { this.jobID = jobID; }

    public String getTaskClass() {
        return taskClass;
    }

    public void setTaskClass(String taskClass) {
        this.taskClass = taskClass;
    }

    public Map<String, String> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, String> properties) {
        this.properties = properties;
    }

    public FileStore getUserFileStore() {
        return userFileStore;
    }

    public void setUserFileStore(FileStore userFileStore) {
        this.userFileStore = userFileStore;
    }

    public List<ArchiveStore> getArchiveFileStores() {
        return archiveFileStores;
    }

    public void setArchiveFileStores(List<ArchiveStore> archiveFileStores) {
        this.archiveFileStores = archiveFileStores;
    }

    public boolean isRedeliver() {
        return isRedeliver;
    }

    public void setIsRedeliver(boolean isRedeliver) {
        this.isRedeliver = isRedeliver;
    }
    
    public void performAction(Context context) {}
}
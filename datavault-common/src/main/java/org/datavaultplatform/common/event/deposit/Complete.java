package org.datavaultplatform.common.event.deposit;

import java.util.HashMap;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.datavaultplatform.common.event.Event;

@Entity
public class Complete extends Event {

    // Maps the model ArchiveStore Id to the generated Archive Id
    HashMap<String, String> archiveIds = new HashMap<>();

    //public String archiveId;
    public long archiveSize;
    
    Complete() {};
    public Complete(String jobId, String depositId, HashMap<String, String> archiveIds, long archiveSize) {
        
        super("Deposit completed");
        
        this.eventClass = Complete.class.getCanonicalName();
        this.archiveIds = archiveIds;
        this.archiveSize = archiveSize;
        this.depositId = depositId;
        this.jobId = jobId;
    }

    public HashMap<String, String> getArchiveIds() {
        return archiveIds;
    }

    public void setArchiveIds(HashMap<String, String> archiveIds) {
        this.archiveIds = archiveIds;
    }

    /*
    public String getArchiveId() {
        return archiveId;
    }

    public void setArchiveId(String archiveId) {
        this.archiveId = archiveId;
    }
    */

    public long getArchiveSize() {
        return archiveSize;
    }

    public void setArchiveSize(long archiveSize) {
        this.archiveSize = archiveSize;
    }
}

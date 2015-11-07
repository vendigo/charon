package com.github.vendigo.charon.file.registration;

import org.apache.camel.component.jpa.Consumed;

import javax.persistence.*;
import java.util.Date;

@Entity
public class InFileStatus {
    @Id
    @GeneratedValue
    private Long fileStatusId;
    @Column
    private Long fileId;
    @Column
    @Enumerated(EnumType.STRING)
    private FileState state;
    @Column
    private Date creationTime = new Date();
    @Column
    private boolean processed = false;

    public InFileStatus() {
    }

    public InFileStatus(Long fileId, FileState state) {
        this.fileId = fileId;
        this.state = state;
    }

    public Long getFileStatusId() {
        return fileStatusId;
    }

    public Long getFileId() {
        return fileId;
    }

    public FileState getState() {
        return state;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public boolean isProcessed() {
        return processed;
    }

    @Consumed
    public void setProcessed() {
        this.processed = true;
    }
}

package com.github.vendigo.charon.file.registration;

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
    private Date creationTime = new Date();

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
}

package com.github.vendigo.charon.routes.file.registration;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class InFile {
    @Id
    @GeneratedValue
    private Long fileId;
    @Column
    private String fileName;
    @Column
    private String absoluteFilePath;
    @Column
    private Long fileLength;
    @Column
    private String fileConfigName;

    public InFile() {
    }

    public InFile(String fileName, String absoluteFilePath, Long fileLength, String fileConfigName) {
        this.fileName = fileName;
        this.absoluteFilePath = absoluteFilePath;
        this.fileLength = fileLength;
        this.fileConfigName = fileConfigName;
    }

    public Long getFileId() {
        return fileId;
    }

    public String getFileName() {
        return fileName;
    }

    public String getAbsoluteFilePath() {
        return absoluteFilePath;
    }

    public Long getFileLength() {
        return fileLength;
    }

    public String getFileConfigName() {
        return fileConfigName;
    }
}

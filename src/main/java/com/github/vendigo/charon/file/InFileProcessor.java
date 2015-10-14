package com.github.vendigo.charon.file;

import org.apache.camel.Body;
import org.apache.camel.Handler;
import org.apache.camel.Headers;
import org.apache.camel.component.file.GenericFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Map;

@Component("inFileProcessor")
public class InFileProcessor {
    @Autowired
    InFileRepository inFileRepository;
    @Autowired
    InFileStatusRepository inFileStatusRepository;

    @Handler
    public void registerFile(@Body GenericFile<File> file, @Headers Map<String, Object> headers) {
        InFile inFile = new InFile(file.getFileName(), file.getAbsoluteFilePath(), file.getFileLength());

        inFileRepository.save(inFile);
        Long fileId = inFile.getFileId();

        InFileStatus inFileStatus = new InFileStatus(fileId, FileState.FOUND);
        inFileStatusRepository.save(inFileStatus);

        headers.put("fileId", fileId);
        headers.put("fileState", FileState.FOUND);
    }
}

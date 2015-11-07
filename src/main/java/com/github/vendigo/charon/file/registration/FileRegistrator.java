package com.github.vendigo.charon.file.registration;

import com.github.vendigo.charon.configuration.FileConfigurations;
import com.github.vendigo.charon.file.parsing.FileConfiguration;
import org.apache.camel.Body;
import org.apache.camel.Handler;
import org.apache.camel.Headers;
import org.apache.camel.component.file.GenericFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component("registerFile")
public class FileRegistrator {
    @Autowired
    InFileRepository inFileRepository;
    @Autowired
    InFileStatusRepository inFileStatusRepository;
    @Autowired
    FileConfigurations fileConfigurations;

    @Handler
    public void registerFile(@Body GenericFile<File> file, @Headers Map<String, Object> headers) {
        Optional<FileConfiguration> fileConfiguration = fileConfigurations.findByFileName(file.getFileName());
        if (fileConfiguration.isPresent()) {
            InFile inFile = new InFile(file.getFileName(), file.getAbsoluteFilePath(), file.getFileLength(),
                    fileConfiguration.get().getConfigName());
            inFileRepository.save(inFile);
            Long fileId = inFile.getFileId();

            headers.put("fileId", fileId);
            headers.put("fileState", FileState.FOUND);
            headers.put("fileConfiguration", fileConfiguration.get());

            InFileStatus inFileStatus = new InFileStatus(fileId, FileState.FOUND);
            inFileStatusRepository.save(inFileStatus);
        }

        //TODO Extract header names to constants, headers wrapper
        //TODO Else throw exception
    }
}

package com.github.vendigo.charon.file;

import com.github.vendigo.charon.parser.FileConfiguration;
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
    List<FileConfiguration> fileConfigurations;

    @Handler
    public void registerFile(@Body GenericFile<File> file, @Headers Map<String, Object> headers) {
        InFile inFile = new InFile(file.getFileName(), file.getAbsoluteFilePath(), file.getFileLength());

        inFileRepository.save(inFile);
        Long fileId = inFile.getFileId();

        InFileStatus inFileStatus = new InFileStatus(fileId, FileState.FOUND);
        inFileStatusRepository.save(inFileStatus);

        //TODO Extract header names to constants, headers wrapper
        headers.put("fileId", fileId);
        headers.put("fileState", FileState.FOUND);

        Optional<FileConfiguration> fileConfiguration = findFileConfiguration(file.getFileName());
        if (fileConfiguration.isPresent()) {
            headers.put("fileConfiguration", fileConfiguration.get());
        }

        //TODO Else throw exception
    }

    private Optional<FileConfiguration> findFileConfiguration(String fileName) {
        return fileConfigurations.stream().filter(conf -> fileName.matches(conf.getFileNamePattern())).findFirst();
    }
}

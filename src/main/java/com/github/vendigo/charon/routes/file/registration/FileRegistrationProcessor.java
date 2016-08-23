package com.github.vendigo.charon.routes.file.registration;

import com.github.vendigo.charon.configuration.BeansHolder;
import com.github.vendigo.charon.configuration.FileConfigurationsHolder;
import com.github.vendigo.charon.routes.file.config.FileConfiguration;
import org.apache.camel.Body;
import org.apache.camel.Handler;
import org.apache.camel.Headers;
import org.apache.camel.component.file.GenericFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Map;
import java.util.Optional;

import static org.apache.commons.lang3.Validate.notNull;

public class FileRegistrationProcessor {

    private final BeansHolder beansHolder;

    public FileRegistrationProcessor(BeansHolder beansHolder) {
        this.beansHolder = notNull(beansHolder, "beansHolder must be not null");
    }

    @Handler
    public void registerFile(@Body GenericFile<File> file, @Headers Map<String, Object> headers) {
        Optional<FileConfiguration> fileConfiguration = beansHolder.getFileConfigurationsHolder().
                findByFileNamePattern(file.getFileName());
        if (fileConfiguration.isPresent()) {
            InFile inFile = new InFile(file.getFileName(), file.getAbsoluteFilePath(), file.getFileLength(),
                    fileConfiguration.get().getConfigName());
            beansHolder.getInFileRepository().save(inFile);
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

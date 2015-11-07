package com.github.vendigo.charon.configuration;

import com.github.vendigo.charon.file.parsing.FileConfiguration;
import com.github.vendigo.charon.file.registration.InFile;
import com.github.vendigo.charon.file.registration.InFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component("fileConfigurations")
public class FileConfigurations {
    @Autowired
    List<FileConfiguration> fileConfigurations;

    @Autowired
    InFileRepository inFileRepository;

    public Optional<FileConfiguration> findByFileName(String fileName) {
        return fileConfigurations.stream().filter(conf -> fileName.matches(conf.getFileNamePattern())).findFirst();
    }

    public Optional<FileConfiguration> findByFileId(Long fileId) {
        InFile inFile = inFileRepository.findOne(fileId);
        if (inFile == null) {
            return Optional.empty();
        }

        return fileConfigurations.stream().filter(conf -> conf.getConfigName().equals(inFile.getFileConfigName())).findFirst();
    }
}

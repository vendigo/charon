package com.github.vendigo.charon.configuration;

import com.github.vendigo.charon.routes.file.config.FileConfiguration;
import com.github.vendigo.charon.routes.file.registration.InFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component("fileConfigurations")
public class FileConfigurationsHolder {
    @Autowired
    List<FileConfiguration> fileConfigurations;

    @Autowired
    InFileRepository inFileRepository;

    public Optional<FileConfiguration> findByFileNamePattern(String fileName) {
        return fileConfigurations.stream().filter(conf -> fileName.matches(conf.getFileNamePattern())).findFirst();
    }
}

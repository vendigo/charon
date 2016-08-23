package com.github.vendigo.charon.configuration;

import com.github.vendigo.charon.routes.file.registration.InFileRepository;
import com.github.vendigo.charon.routes.file.registration.InFileStatusRepository;
import com.github.vendigo.charon.routes.loading.InMemoryStorage;
import com.github.vendigo.charon.row.conversion.ColumnConverter;
import com.github.vendigo.charon.row.validation.ColumnValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BeansHolder {
    @Autowired
    private AppProperties appProperties;
    @Autowired
    private FileConfigurationsHolder fileConfigurationsHolder;
    @Autowired
    private InFileRepository inFileRepository;
    @Autowired
    private InFileStatusRepository inFileStatusRepository;
    @Autowired
    private ColumnConverter columnConverter;
    @Autowired
    private ColumnValidator columnValidator;
    @Autowired
    private InMemoryStorage inMemoryStorage;

    public AppProperties getAppProperties() {
        return appProperties;
    }

    public FileConfigurationsHolder getFileConfigurationsHolder() {
        return fileConfigurationsHolder;
    }

    public InFileRepository getInFileRepository() {
        return inFileRepository;
    }

    public InFileStatusRepository getInFileStatusRepository() {
        return inFileStatusRepository;
    }

    public ColumnConverter getColumnConverter() {
        return columnConverter;
    }

    public ColumnValidator getColumnValidator() {
        return columnValidator;
    }

    public InMemoryStorage getInMemoryStorage() {
        return inMemoryStorage;
    }
}

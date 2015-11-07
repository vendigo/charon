package com.github.vendigo.charon.route.helpers;

import com.github.vendigo.charon.configuration.FileConfigurations;
import com.github.vendigo.charon.file.parsing.FileConfiguration;
import com.github.vendigo.charon.file.registration.InFileRepository;
import com.github.vendigo.charon.file.registration.InFileStatus;
import com.github.vendigo.charon.utils.HeaderNames;
import org.apache.camel.Handler;
import org.apache.camel.Headers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;

@Component("findTableNames")
public class TableNamesLookup {

    @Autowired
    InFileRepository inFileRepository;
    @Autowired
    FileConfigurations fileConfigurations;

    @Handler
    public void findFileInformation(InFileStatus inFileStatus, @Headers Map<String, Object> headers) {
        Optional<FileConfiguration> fileConfigurationOptional = fileConfigurations.findByFileId(inFileStatus.getFileId());
        if (fileConfigurationOptional.isPresent()) {
            FileConfiguration fileConfiguration = fileConfigurationOptional.get();
            headers.put(HeaderNames.PARSED_TABLE_NAME, fileConfiguration.getParsedTableName());
            headers.put(HeaderNames.HIST_TABLE_NAME, fileConfiguration.getHistTableName());
        }
    }
}

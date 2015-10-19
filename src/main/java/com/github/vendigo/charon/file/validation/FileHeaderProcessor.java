package com.github.vendigo.charon.file.validation;

import com.github.vendigo.charon.configuration.AppProperties;
import com.github.vendigo.charon.file.parsing.FileConfiguration;
import org.apache.camel.Body;
import org.apache.camel.Header;
import org.apache.camel.Headers;
import org.apache.camel.component.file.GenericFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Map;

@Component("checkBusinessDate")
public class FileHeaderProcessor {

    @Autowired
    AppProperties appProperties;

    public void checkHeader(@Body GenericFile<File> file, @Header("fileConfiguration")FileConfiguration fileConf,
                            @Headers Map<String, Object> headers) throws Exception {

        boolean apropriateBusinessDate = true;

        if (fileConf.isHasHeader()) {
            BufferedReader fileReader = new BufferedReader(new FileReader(file.getFile()));
            String header = fileReader.readLine();
            apropriateBusinessDate = header.endsWith(appProperties.getBusinessDate());
            fileReader.close();
        }

        headers.put("apropriateBusinessDate", apropriateBusinessDate);
    }
}

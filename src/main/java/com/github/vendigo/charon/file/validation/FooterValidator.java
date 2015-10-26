package com.github.vendigo.charon.file.validation;

import com.github.vendigo.charon.file.parsing.FileConfiguration;
import com.github.vendigo.charon.file.registration.FileState;
import com.github.vendigo.charon.file.registration.InFileStatus;
import com.github.vendigo.charon.file.registration.InFileStatusRepository;
import org.apache.camel.Body;
import org.apache.camel.Exchange;
import org.apache.camel.Handler;
import org.apache.camel.Header;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component("validateFooter")
public class FooterValidator {

    @Autowired
    InFileStatusRepository inFileStatusRepository;

    @Handler
    public void validateFooter(@Body List<Map<String, Object>> data, @Header("fileConfiguration") FileConfiguration fileConf,
                               @Header("fileId") long fileId, Exchange exchange) {
        String footer = fileConf.getFooter();
        int lineCount = extractLineCount(fileConf, footer);

        InFileStatus inFileStatus = new InFileStatus(fileId, determineFileState(data.size(), lineCount), fileConf.getConfigName());
        inFileStatusRepository.save(inFileStatus);
        failRouteIfNeed(inFileStatus, exchange);
    }

    private void failRouteIfNeed(InFileStatus inFileStatus, Exchange exchange) {
        if (inFileStatus.getState() == FileState.FAILED) {
            exchange.setException(new FooterValidationException("Wrong lineCount"));
        }
    }

    private FileState determineFileState(int expectedLineCount, int realLineCount) {
        return (realLineCount != expectedLineCount) ? FileState.FAILED : FileState.PARSED;
    }

    private int extractLineCount(FileConfiguration fileConf, String footer) {
        String[] split = footer.split("\\" + fileConf.getDelimiter());
        return Integer.parseInt(split[split.length - 1]);
    }
}

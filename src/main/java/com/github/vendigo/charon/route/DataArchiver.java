package com.github.vendigo.charon.route;

import com.github.vendigo.charon.file.registration.InFileStatus;
import com.github.vendigo.charon.utils.HeaderNames;
import org.apache.camel.Header;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component("dataArchiver")
public class DataArchiver {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public void cleanUpParsedTable(InFileStatus inFileStatus, @Header(HeaderNames.PARSED_TABLE_NAME) String parsedTableName) {
        jdbcTemplate.execute(buildDeleteQuery(inFileStatus.getFileId(), parsedTableName));
    }

    public void moveDataToHist(InFileStatus inFileStatus, @Header(HeaderNames.HIST_TABLE_NAME) String histTableName,
                               @Header(HeaderNames.PARSED_TABLE_NAME) String parsedTableName) {
        jdbcTemplate.execute(buildCopyQuery(inFileStatus.getFileId(), histTableName, parsedTableName));
    }

    private String buildCopyQuery(Long fileId, String histTableName, String parsedTableName) {
        return String.format("insert into %s select * from %s where fileId=%d", histTableName, parsedTableName, fileId);
    }

    private String buildDeleteQuery(Long fileId, String parsedTableName) {
        return String.format("delete from %s where fileId=%d", parsedTableName, fileId);
    }
}

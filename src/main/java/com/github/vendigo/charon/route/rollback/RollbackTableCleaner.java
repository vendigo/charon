package com.github.vendigo.charon.route.rollback;

import com.github.vendigo.charon.file.registration.InFileStatus;
import com.github.vendigo.charon.utils.HeaderNames;
import org.apache.camel.Handler;
import org.apache.camel.Header;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component("cleanUpParsedTable")
public class RollbackTableCleaner {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Handler
    public void cleanUpParsedTable(InFileStatus inFileStatus, @Header(HeaderNames.TABLE_NAME) String parsedTableName) {
        System.out.println("Executing: "+buildDeleteQuery(inFileStatus.getFileId(), parsedTableName));
        jdbcTemplate.execute(buildDeleteQuery(inFileStatus.getFileId(), parsedTableName));
    }

    private String buildDeleteQuery(Long fileId, String parsedTableName) {
        return String.format("delete from %s where fileId=%d",parsedTableName, fileId);
    }
}

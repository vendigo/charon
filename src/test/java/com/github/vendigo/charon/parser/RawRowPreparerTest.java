package com.github.vendigo.charon.parser;

import com.github.vendigo.charon.configuration.AppProperties;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsMapContaining.hasEntry;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RawRowPreparerTest {
    @Test
    public void testPrepareRawRow() throws Exception {
        AppProperties appProperties = mock(AppProperties.class);
        when(appProperties.getChunkSize()).thenReturn(2);

        RawRowPreparer preparer = new RawRowPreparer();
        preparer.appProperties = appProperties;

        Map<String, String> row1 = new HashMap<>();
        row1.put("name", "Dima");

        Map<String, String> row2 = new HashMap<>();
        row2.put("name", "Petya");

        List<Map<String, String>> chunk = Arrays.asList(row1, row2);

        Column column1 = new Column(1, "name", ColumnType.STRING);
        FileConfiguration fileConf = new FileConfiguration("test", "test", Arrays.asList(column1));

        Map<String, Object> headers = new HashMap<>();
        headers.put("fileId", 1);
        headers.put("fileConfiguration", fileConf);

        preparer.prepareRawRow(chunk, 0, headers);

        assertThat(headers, hasEntry("insertRawParams", "(1, 0, 'Dima'), (1, 1, 'Petya')"));
    }
}

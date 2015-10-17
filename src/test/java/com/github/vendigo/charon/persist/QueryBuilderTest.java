package com.github.vendigo.charon.persist;

import com.github.vendigo.charon.parser.Column;
import com.github.vendigo.charon.parser.ColumnType;
import com.github.vendigo.charon.parser.FileConfiguration;
import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class QueryBuilderTest {

    @Test
    public void testInsertRaw() throws Exception {
        Column column1 = new Column(1, "first", ColumnType.STRING);
        Column column2 = new Column(1, "second", ColumnType.STRING);
        Column column3 = new Column(1, "third", ColumnType.STRING);
        FileConfiguration fileConf = new FileConfiguration("test", "test", Arrays.asList(column1, column2, column3));
        QueryBuilder builder = new QueryBuilder(fileConf);

        assertThat(builder.insertRawQuery(), equalTo("INSERT INTO TEST_RAW (first, second, third) VALUES #"));
    }
}

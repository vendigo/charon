package com.github.vendigo.charon.row;

import java.util.Map;

public class RawRow {
    private Map<String, String> columns;
    private String header;
    private String footer;

    public RawRow(Map<String, String> columns, String header, String footer) {
        this.columns = columns;
        this.header = header;
        this.footer = footer;
    }

    public Map<String, String> getColumns() {
        return columns;
    }

    public String getHeader() {
        return header;
    }

    public String getFooter() {
        return footer;
    }

    @Override
    public String toString() {
        return "RawRow{" +
                "columns=" + columns +
                ", header='" + header + '\'' +
                ", footer='" + footer + '\'' +
                '}';
    }
}

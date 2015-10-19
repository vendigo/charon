package com.github.vendigo.charon.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AppProperties {
    @Value("${files.dir.in}")
    private String inFolder;
    @Value("${files.dir.processed}")
    private String outFolder;
    @Value("${files.dir.failed}")
    private String failedFolder;
    @Value("${chunk.size}")
    private int chunkSize;
    @Value("${threads.count}")
    private int threadsCount;

    public String getInFolder() {
        return inFolder;
    }

    public String getOutFolder() {
        return outFolder;
    }

    public String getFailedFolder() {
        return failedFolder;
    }

    public int getChunkSize() {
        return chunkSize;
    }

    public int getThreadsCount() {
        return threadsCount;
    }
}

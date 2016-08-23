package com.github.vendigo.charon.file;

import com.github.vendigo.charon.routes.file.registration.InFile;
import com.github.vendigo.charon.routes.file.registration.InFileRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class InFileRepositoryTest extends AbstractRepositoryTest {
    @Autowired
    InFileRepository fileRepository;

    @Test
    @Transactional
    public void testSaveGet() throws Exception {
        InFile inFile = new InFile("test.txt", "D:/files/test.txt", 100L, "test");
        fileRepository.save(inFile);

        assertThat(fileRepository.count(), equalTo(1L));
    }
}

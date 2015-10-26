package com.github.vendigo.charon.file;

import com.github.vendigo.charon.file.registration.FileState;
import com.github.vendigo.charon.file.registration.InFileStatus;
import com.github.vendigo.charon.file.registration.InFileStatusRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class InFileStatusRepositoryTest extends AbstractRepositoryTest {
    @Autowired
    InFileStatusRepository inFileStatusRepository;

    @Test
    public void testSaveGet() throws Exception {
        InFileStatus fileStatus = new InFileStatus(1L, FileState.FOUND, "test");
        inFileStatusRepository.save(fileStatus);

        assertThat(inFileStatusRepository.count(), equalTo(1L));
    }
}

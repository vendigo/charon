package com.github.vendigo.charon.file.registration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("createInFileStatus")
public class InFileStatusCreator {

    @Autowired
    InFileStatusRepository inFileStatusRepository;

    public void reverted(InFileStatus prevInFileStatus) {
        inFileStatusRepository.save(new InFileStatus(prevInFileStatus.getFileId(), FileState.REVERTED));
    }

    public void archived(InFileStatus prevInFileStatus) {
        inFileStatusRepository.save(new InFileStatus(prevInFileStatus.getFileId(), FileState.ARCHIVED));
    }

    public void loaded(InFileStatus prevInFileStatus) {
        inFileStatusRepository.save(new InFileStatus(prevInFileStatus.getFileId(), FileState.LOADED));
    }
}

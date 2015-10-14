package com.github.vendigo.charon.file;

import com.github.vendigo.charon.configuration.RootConfiguraion;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RootConfiguraion.class})
@Transactional
public abstract class AbstractRepositoryTest {
}

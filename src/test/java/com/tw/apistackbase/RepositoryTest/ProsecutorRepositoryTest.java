package com.tw.apistackbase.RepositoryTest;

import com.tw.apistackbase.Entity.Procuratorate;
import com.tw.apistackbase.Entity.Prosecutor;
import com.tw.apistackbase.Repository.ProsecutorRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProsecutorRepositoryTest {

    @Autowired
    private ProsecutorRepository prosecutorRepository;

    @Test
    public void should_add_prosecutor_when_name_is_no_null(){
        //given
        Prosecutor prosecutor = new Prosecutor("pro");
        Prosecutor prosecutorNullName = new Prosecutor();
        //when
        Prosecutor save = prosecutorRepository.saveAndFlush(prosecutor);
        //then
        Assertions.assertEquals(prosecutor.getName(), save.getName());
        Assertions.assertThrows(Exception.class, () -> prosecutorRepository.saveAndFlush(prosecutorNullName));
    }
}

package com.tw.apistackbase.RepositoryTest;

import com.tw.apistackbase.Entity.DetailInfo;
import com.tw.apistackbase.Entity.Procuratorate;
import com.tw.apistackbase.Entity.Prosecutor;
import com.tw.apistackbase.Repository.ProcuratorateRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProcuratorateRepositoryTest {

    @Autowired
    private ProcuratorateRepository procuratorateRepository;

    @Test
    public void should_add_procuratorate_when_name_is_no_null_and_no_repeat(){
        //given
        Procuratorate procuratorate1 = new Procuratorate("pro");
        Procuratorate procuratorate2 = new Procuratorate();
        Procuratorate procuratorate3 = new Procuratorate("pro");
        //when
        Procuratorate save = procuratorateRepository.saveAndFlush(procuratorate1);
        //then
        Assertions.assertEquals(procuratorate1.getName(), save.getName());
        Assertions.assertThrows(Exception.class, () -> procuratorateRepository.saveAndFlush(procuratorate2));
        Assertions.assertThrows(Exception.class, () -> procuratorateRepository.saveAndFlush(procuratorate3));
    }

    @Test
    public void should_return_procuratorate_when_find_by_id(){
        //given
        Procuratorate procuratorate1 = new Procuratorate("pro");
        Procuratorate save = procuratorateRepository.saveAndFlush(procuratorate1);
        //when
        Procuratorate saved = procuratorateRepository.getOne(save.getId());
        //then
        Assertions.assertEquals(procuratorate1.getName(), saved.getName());
    }

    @Test
    public void should_return_procuratorate_with_prosecutors_when_find_procuratorate_by_id(){
        //given
        Procuratorate procuratorate = new Procuratorate("pro");
        List<Prosecutor> prosecutors = new ArrayList<>();
        prosecutors.add(new Prosecutor("zhangsan"));
        prosecutors.add(new Prosecutor("lisi"));
        procuratorate.setProsecutors(prosecutors);
        Procuratorate save = procuratorateRepository.saveAndFlush(procuratorate);
        //when
        Procuratorate saved = procuratorateRepository.getOne(save.getId());
        //then
        Assertions.assertEquals(procuratorate.getName(), saved.getName());
        Assertions.assertEquals(2, saved.getProsecutors().size());
        Assertions.assertEquals("zhangsan", saved.getProsecutors().get(0).getName());
        Assertions.assertEquals("lisi", saved.getProsecutors().get(1).getName());
    }
}

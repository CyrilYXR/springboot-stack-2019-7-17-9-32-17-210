package com.tw.apistackbase.RepositoryTest;

import com.tw.apistackbase.Entity.CriminalCase;
import com.tw.apistackbase.Repository.CriminalCaseRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CriminalCaseRepositoryTest {

    @Autowired
    private CriminalCaseRepository caseRepository;

    @Test
    public void should_add_case_when_columns_are_not_null(){
        //given
        CriminalCase criminalCase = new CriminalCase("case1", 1970010123232323L);
        //when
        CriminalCase caseSaved = caseRepository.save(criminalCase);
        //then
        Assertions.assertEquals("case1", caseRepository.getOne(caseSaved.getId()).getName());
    }

    @Test
    public void should_throw_exception_when_column_has_null(){
        //given
        CriminalCase criminalCase = new CriminalCase();
        criminalCase.setName("case1");
        //when
        //then
        Assertions.assertThrows(DataIntegrityViolationException.class, () -> caseRepository.saveAndFlush(criminalCase));
    }

    @Test
    public void should_return_case_when_find_by_id(){
        //given
        CriminalCase criminalCase = new CriminalCase("case1", 1970010123232323L);
        CriminalCase caseSaved = caseRepository.save(criminalCase);
        //when
        CriminalCase aCase = caseRepository.getOne(caseSaved.getId());
        //then
        Assertions.assertEquals("case1", aCase.getName());
        Assertions.assertEquals(1970010123232323L, aCase.getTime().longValue());
    }

    @Test
    public void should_return_all_cases_order_by_time_when_find_all_case_order_by_time(){
        //given
        CriminalCase criminalCase1 = new CriminalCase("case1", 1970010123232300L);
        CriminalCase criminalCase2 = new CriminalCase("case2", 1970010123232323L);
        caseRepository.save(criminalCase1);
        caseRepository.save(criminalCase2);
        //when
        List<CriminalCase> cases = caseRepository.findByOrderByTimeDesc();
        //then
        Assertions.assertEquals(2, cases.size());
        Assertions.assertEquals("case2", cases.get(0).getName());
        Assertions.assertEquals("case1", cases.get(1).getName());
    }

}

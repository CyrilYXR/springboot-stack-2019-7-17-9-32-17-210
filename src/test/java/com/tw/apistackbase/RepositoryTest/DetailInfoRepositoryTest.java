package com.tw.apistackbase.RepositoryTest;

import com.tw.apistackbase.Entity.DetailInfo;
import com.tw.apistackbase.Repository.DetailInfoRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DetailInfoRepositoryTest {

    @Autowired
    private DetailInfoRepository detailInfoRepository;

    @Test
    public void should_add_detail_info_when_save_a_detail_info_entity(){
        //given
        DetailInfo detailInfo = new DetailInfo("subjective element","objective element");
        DetailInfo nullDetailInfo = new DetailInfo();
        //when
        DetailInfo save = detailInfoRepository.saveAndFlush(detailInfo);
        //then
        Assertions.assertEquals(detailInfo.getSubjectiveElement(), save.getSubjectiveElement());
        Assertions.assertThrows(Exception.class, () -> detailInfoRepository.saveAndFlush(nullDetailInfo));
    }

    @Test
    public void should_return_a_detail_info_when_find_by_id(){
        //given
        DetailInfo detailInfo1 = new DetailInfo("subjective element","objective element");
        DetailInfo detailInfo2 = new DetailInfo("subjective","objective");
        DetailInfo detailInfo = detailInfoRepository.saveAndFlush(detailInfo1);
        detailInfoRepository.saveAndFlush(detailInfo2);
        //when
        DetailInfo infoSaved = detailInfoRepository.getOne(detailInfo.getId());
        //then
        Assertions.assertEquals(detailInfo1.getSubjectiveElement(), infoSaved.getSubjectiveElement());
    }
}

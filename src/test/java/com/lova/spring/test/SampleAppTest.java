package com.lova.spring.test;

import com.lova.spring.config.AppConfig;
import com.lova.spring.domain.SampleDomain;
import com.lova.spring.service.SampleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.Serializable;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Created by Lovababu on 4/26/2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
public class SampleAppTest {

    @Autowired
    private SampleService sampleService;

    @Test
    public void testCreate(){
        SampleDomain domain = getSampleDomain(123L, "lovababu", "PGVM");
        Serializable id = sampleService.insert(domain);
        assertEquals(id, domain.getId());
    }

    @Test
    public void testUpdate(){
        testCreate(); //In this step Record will be inserted with the Id '100',
                      // since it has hardcode in the above test case.
        SampleDomain domain = sampleService.update(getSampleDomain(123L, "avol", "TPK"));

        assertEquals(domain.getName(), "avol");
    }

    @Test
    public void testGet(){
        //refer. my-test-data.sql, record has already inserted by embedded b with the SID: 100
        SampleDomain domain = getSampleDomain(100L, null, null);
        domain = sampleService.get(domain);
        assertEquals(domain.getName(), "SRILEKHA");
    }

    @Test
    public void testDelete(){
        SampleDomain domain = getSampleDomain(100L, null, null);
        sampleService.delete(domain);
        //Trying to get the record with the id 100, which was deleted just now.

        domain = sampleService.get(domain);//No records found.
        assertNull(domain);
    }

    private SampleDomain getSampleDomain(Long id, String name, String addr){
        SampleDomain domain = new SampleDomain();
        domain.setId(id);
        domain.setName(name);
        domain.setAddress(addr);
        return domain;
    }
}

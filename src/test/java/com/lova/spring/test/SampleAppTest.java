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
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

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
        // Create first and then update.
        SampleDomain domainToBeInserted = getSampleDomain(101L, "Name 1", "Address 1");
        Long id = (Long) sampleService.insert(domainToBeInserted);

        assertEquals(id, domainToBeInserted.getId());

        //Here update the name and Address for the same record, then update in DB.
        SampleDomain domainUpdated = sampleService.update(getSampleDomain(id, "Name 2", "Address 2"));

        assertEquals("Name 2", domainUpdated.getName());
        assertEquals("Address 2", domainUpdated.getAddress());
    }

    @Test
    public void testGet(){
        //refer. my-test-data.sql, record has already inserted by embedded b with the SID: 100
        SampleDomain domain = getSampleDomain(100L, null, null);
        domain = sampleService.get(domain);
        assertEquals(domain.getName(), "Avol");
    }

    @Test
    public void testDelete(){
        SampleDomain domain = getSampleDomain(100L, null, null);
        sampleService.delete(domain);
        //Trying to get the record with the id 100, which was deleted just now.

        domain = sampleService.get(domain);//No records found.
        assertNull(domain);
    }

    @Test
    public void testListView(){
        testCreate();
        List<SampleDomain> list = sampleService.listView();
        assertNotNull(list);
        System.out.println("----------------TABLE DATA ---------------------");
        System.out.println("SID    |   NAME      |    Address    ");
        System.out.println("......................................");

        long count = list.stream().map(sampleDomain -> printPrettyFormat(sampleDomain)).count();
        assertNotEquals(count, 0);
    }

    private Void printPrettyFormat(SampleDomain domain) {
        System.out.println(domain.getId() +"    |    " + domain.getName() + "  |   " + domain.getAddress());
        return null;
    }

    private SampleDomain getSampleDomain(Long id, String name, String addr){
        SampleDomain domain = new SampleDomain();
        domain.setId(id);
        domain.setName(name);
        domain.setAddress(addr);
        return domain;
    }
}

package com.lova.spring.service.impl;

import com.lova.spring.dao.SampleDao;
import com.lova.spring.domain.SampleDomain;
import com.lova.spring.service.SampleService;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

/**
 * Created by Lovababu on 4/26/2015.
 */

@Service
public class SampleServiceImpl implements SampleService {

    @Autowired
    private SampleDao sampleDao;

    @Override
    @Transactional
    public Serializable insert(SampleDomain domain) {
        return sampleDao.insert(domain);
    }

    @Override
    @Transactional
    public SampleDomain update(SampleDomain domain) {
        return sampleDao.update(domain);
    }

    @Override
    @Transactional
    public SampleDomain get(SampleDomain domain) {
        return sampleDao.get(domain);
    }

    @Override
    @Transactional
    public void delete(SampleDomain domain) {
        sampleDao.delete(domain);
    }

}

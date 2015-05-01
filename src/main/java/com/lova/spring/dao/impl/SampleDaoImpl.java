package com.lova.spring.dao.impl;

import com.lova.spring.dao.SampleDao;
import com.lova.spring.domain.SampleDomain;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Lovababu on 4/26/2015.
 */
@Slf4j
public class SampleDaoImpl implements SampleDao {

    private SessionFactory sessionFactory;

    public SampleDaoImpl(){
    }

    @Override
    public Serializable insert(SampleDomain domain) {
        log.info("Saving the Object with SID: {}", domain.getId());
        return sessionFactory.getCurrentSession().save(domain);
    }

    @Override
    public SampleDomain update(SampleDomain domain) {
        log.info("Updating the Object with SID: {}", domain.getId());
        sessionFactory.getCurrentSession().update(domain);
        return  get(domain);
    }

    @Override
    public SampleDomain get(SampleDomain domain) {
        log.info("Retrieving the Object with SID: {}", domain.getId());
        return (SampleDomain) sessionFactory.getCurrentSession()
                    .get(SampleDomain.class, domain.getId());

    }

    @Override
    public void delete(SampleDomain domain) {
        log.info("Deleting the Object with SID: {}", domain.getId());
        sessionFactory.getCurrentSession().delete(domain);
    }

    @Override
    public List<SampleDomain> listView() {
        return this.sessionFactory.getCurrentSession().createQuery("FROM com.lova.spring.domain.SampleDomain").list();
    }

    @Override
    public void sessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}

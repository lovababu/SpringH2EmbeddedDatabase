package com.lova.spring.dao.impl;

import com.lova.spring.dao.SampleDao;
import com.lova.spring.domain.SampleDomain;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * Created by Lovababu on 4/26/2015.
 */

public class SampleDaoImpl implements SampleDao {

    private SessionFactory sessionFactory;

    public SampleDaoImpl(){
    }

    @Override
    public Serializable insert(SampleDomain domain) {
        return sessionFactory.getCurrentSession().save(domain);
    }

    @Override
    public SampleDomain update(SampleDomain domain) {
        sessionFactory.getCurrentSession().update(domain);
        return  get(domain);
    }

    @Override
    public SampleDomain get(SampleDomain domain) {
        return (SampleDomain) sessionFactory.getCurrentSession()
                    .get(SampleDomain.class, domain.getId());

    }

    @Override
    public void delete(SampleDomain domain) {
        sessionFactory.getCurrentSession().delete(domain);
    }

    @Override
    public void sessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}

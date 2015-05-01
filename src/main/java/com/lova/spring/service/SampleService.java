package com.lova.spring.service;

import com.lova.spring.domain.SampleDomain;

import java.io.Serializable;

/**
 * Created by Lovababu on 4/26/2015.
 */
public interface SampleService {

    Serializable insert(SampleDomain domain);

    SampleDomain update(SampleDomain domain);

    SampleDomain get(SampleDomain domain);

    void delete(SampleDomain domain);
}

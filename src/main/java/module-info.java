module com.woorinaru.spring.rest {
    // woorinaru models
    requires com.woorinaru.core;
    requires com.woorinaru.repository.sql;
    // JPA
    requires java.sql;
    requires java.persistence;

    // mapstruct
    requires org.mapstruct;

    // spring
    requires spring.context;
    requires spring.boot.autoconfigure;
    requires spring.boot;
    requires spring.web;
    requires spring.beans;
    requires spring.tx;
    requires spring.orm;
    requires spring.security.config;

    requires java.annotation;

    // jackson
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.datatype.jsr310;
    requires com.fasterxml.jackson.datatype.jdk8;
    requires com.fasterxml.jackson.module.paramnames;
    requires spring.core;
    requires jjwt;
}
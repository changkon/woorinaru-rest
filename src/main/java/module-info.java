module com.woorinaru.rest {
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
    requires spring.security.oauth2;

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
    requires google.api.client;
    requires com.google.api.client.json.jackson2;
    requires com.google.api.client;
    requires org.apache.commons.lang3;
    requires org.apache.tomcat.embed.core;
    requires spring.security.core;
    requires spring.security.web;
    requires org.apache.logging.log4j;
}
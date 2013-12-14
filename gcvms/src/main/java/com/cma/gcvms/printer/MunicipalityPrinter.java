/*
 * (c) Copyright 2005-2013 JAXIO, http://www.jaxio.com
 * Source code generated by Celerio, a Jaxio product
 * Want to purchase Celerio ? email us at info@jaxio.com
 * Follow us on twitter: @springfuse
 * Documentation: http://www.jaxio.com/documentation/celerio/
 * Template pack-backend-jpa:src/main/java/printer/Printer.e.vm.java
 */
package com.cma.gcvms.printer;

import java.util.Locale;

import javax.inject.Named;
import javax.inject.Singleton;

import com.cma.gcvms.domain.Municipality;
import com.cma.gcvms.domain.Municipality_;
import com.cma.gcvms.printer.support.GenericPrinter;

/**
 * {@link GenericPrinter} for {@link Municipality} 
 *
 * @see GenericPrinter
 * @see TypeAwarePrinter
 */
@Named
@Singleton
public class MunicipalityPrinter extends GenericPrinter<Municipality> {
    public MunicipalityPrinter() {
        super(Municipality.class, Municipality_.name);
    }

    @Override
    public String print(Municipality municipality, Locale locale) {
        if (municipality == null) {
            return "";
        }
        StringBuilder ret = new StringBuilder();
        appendIfNotEmpty(ret, municipality.getName());
        return ret.toString();
    }
}
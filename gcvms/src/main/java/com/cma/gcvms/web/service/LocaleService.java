/*
 * (c) Copyright 2005-2013 JAXIO, http://www.jaxio.com
 * Source code generated by Celerio, a Jaxio product
 * Want to purchase Celerio ? email us at info@jaxio.com
 * Follow us on twitter: @springfuse
 * Documentation: http://www.jaxio.com/documentation/celerio/
 * Template pack-jsf2-spring-conversation:src/main/java/services/LocaleService.p.vm.java
 */
package com.cma.gcvms.web.service;

import static com.cma.gcvms.web.conversation.ConversationHolder.getCurrentConversation;
import static java.util.Locale.ENGLISH;
import static java.util.Locale.FRENCH;
import static org.springframework.web.context.WebApplicationContext.SCOPE_SESSION;

import java.util.Locale;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.web.servlet.LocaleResolver;

import com.cma.gcvms.context.LocaleHolder;
import com.cma.gcvms.web.conversation.Conversation;

@Named
@Scope(SCOPE_SESSION)
public class LocaleService {
    @Inject
    private LocaleResolver localeResolver;

    public String getLocale() {
        return LocaleHolder.getLocale().toString();
    }

    public String getLanguage() {
        return LocaleHolder.getLocale().getLanguage();
    }

    public String switchToFrench() {
        return switchToLocale(FRENCH);
    }

    public String switchToEnglish() {
        return switchToLocale(ENGLISH);
    }

    private String switchToLocale(Locale locale) {
        updateJsfLocale(locale);
        updateSpringLocale(locale);
        return redirectToSelf();
    }

    private String redirectToSelf() {
        Conversation currentConversation = getCurrentConversation();
        String viewId = FacesContext.getCurrentInstance().getViewRoot().getViewId();
        if (currentConversation != null) {
            return viewId + "?faces-redirect=true&_cid=" + currentConversation.getCid();
        } else {
            return viewId + "?faces-redirect=true";
        }
    }

    private void updateJsfLocale(Locale locale) {
        FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
    }

    private void updateSpringLocale(Locale locale) {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        localeResolver.setLocale((HttpServletRequest) externalContext.getRequest(), (HttpServletResponse) externalContext.getResponse(), locale);
        LocaleHolder.setLocale(locale);
    }

    public boolean isFrench() {
        // check 'fr_FR' or simply 'fr'
        return FRENCH.equals(LocaleHolder.getLocale()) || FRENCH.getLanguage().equals(getLanguage());
    }
}

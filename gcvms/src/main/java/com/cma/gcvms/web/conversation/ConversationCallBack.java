/*
 * (c) Copyright 2005-2013 JAXIO, http://www.jaxio.com
 * Source code generated by Celerio, a Jaxio product
 * Want to purchase Celerio ? email us at info@jaxio.com
 * Follow us on twitter: @springfuse
 * Documentation: http://www.jaxio.com/documentation/celerio/
 * Template pack-jsf2-spring-conversation:src/main/java/conversation/ConversationCallBack.p.vm.java
 */
package com.cma.gcvms.web.conversation;

import static com.cma.gcvms.web.conversation.ConversationHolder.getCurrentConversation;

import java.io.Serializable;

/**
 * CallBacks should be invoked at the end of a @{link ConversationContext} life.
 * A CallBack allows the creator of the conversation context to know which action led to the context termination and to retrieve 
 * any relevant output.
 * For example, a conversation context can be created to let a user select an entity among a list of entities. When the user selects 
 * an entity, the action invokes the selected(T entity) method which let the creator of the context do something with the selected 
 * entity (e.g add it or set it somewhere...).
 */
public class ConversationCallBack<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    public ConversationCallBack() {
    }

    public final String ok(T entity) {
        incrementPopContextOnNextPauseCounter();
        onOk(entity);
        return nextView();
    }

    /**
     * The given entity has been oked. Example: it could mean
     * that it is a newly created entity, that it was validated (but not saved) and
     * that it is up to you to decide what to do with it.
     */
    protected void onOk(T entity) {
    }

    public final String selected(T entity) {
        incrementPopContextOnNextPauseCounter();
        onSelected(entity);
        return nextView();
    }

    public final String selected(T... entities) {
        incrementPopContextOnNextPauseCounter();
        for (T entity : entities) {
            onSelected(entity);
        }
        return nextView();
    }

    /**
     * The given entity has been selected.
     */
    protected void onSelected(T entity) {
    }

    public final String saved(T entity) {
        incrementPopContextOnNextPauseCounter();
        onSaved(entity);
        return nextView();
    }

    /**
     * The given entity has just been saved.
     */
    protected void onSaved(T entity) {
    }

    public final String notSaved(T entity) {
        incrementPopContextOnNextPauseCounter();
        onNotSaved(entity);
        return nextView();
    }

    /**
     * The given entity has not been saved.
     */
    protected void onNotSaved(T entity) {
    }

    public final String deleted(T entity) {
        incrementPopContextOnNextPauseCounter();
        onDeleted(entity);
        return nextView();
    }

    /**
     * The given entity has just been deleted.
     */
    protected void onDeleted(T entity) {
    }

    public final String back() {
        incrementPopContextOnNextPauseCounter();
        onBack();
        return nextView();
    }

    /**
     * No real action was performed, the user just asked to go back.
     */
    protected void onBack() {
    }

    // Context utils

    private void incrementPopContextOnNextPauseCounter() {
        getCurrentConversation().incrementPopContextOnNextPauseCounter();
    }

    private String nextView() {
        return getCurrentConversation().nextView();
    }
}
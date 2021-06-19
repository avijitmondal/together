/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avijitmondal.together.core.dto;

/**
 *
 * @author avijit
 */
public class Link {

    private Page first;
    private Page prev;
    private Page self;
    private Page next;
    private Page last;

    /**
     *
     * @return
     */
    public Page getFirst() {
        return first;
    }

    /**
     *
     * @return
     */
    public Page getPrev() {
        return prev;
    }

    /**
     *
     * @return
     */
    public Page getSelf() {
        return self;
    }

    /**
     *
     * @return
     */
    public Page getNext() {
        return next;
    }

    /**
     *
     * @return
     */
    public Page getLast() {
        return last;
    }

    /**
     *
     */
    private class Page {

        private String href;

        public Page() {
        }

        public Page(String href) {
            this.href = href;
        }

        public String getLink() {
            return href;
        }
    }
}

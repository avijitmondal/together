/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avijit.together.core.dto;

/**
 *
 * @author avijit
 */
public class Sort {

    private Direction direction;
    private String property;
    private boolean ignoreCase;
    private String nullHandling;
    private boolean ascending;
    private boolean descending;

    /**
     *
     */
    public Sort() {
    }

    /**
     *
     * @param direction
     * @param property
     * @param ignoreCase
     * @param nullHandling
     * @param ascending
     * @param descending
     */
    public Sort(Direction direction, String property, boolean ignoreCase, String nullHandling, boolean ascending, boolean descending) {
        this.direction = direction;
        this.property = property;
        this.ignoreCase = ignoreCase;
        this.nullHandling = nullHandling;
        this.ascending = ascending;
        this.descending = descending;
    }

    /**
     *
     * @return
     */
    public Direction getDirection() {
        return direction;
    }

    /**
     *
     * @return
     */
    public String getProperty() {
        return property;
    }

    /**
     *
     * @return
     */
    public boolean isIgnoreCase() {
        return ignoreCase;
    }

    /**
     *
     * @return
     */
    public String getNullHandling() {
        return nullHandling;
    }

    /**
     *
     * @return
     */
    public boolean isAscending() {
        return ascending;
    }

    /**
     *
     * @return
     */
    public boolean isDescending() {
        return descending;
    }

    /**
     *
     */
    private enum Direction {
        ASC, DESC
    }
}

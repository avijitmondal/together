/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avijit.together.core.dto;

/**
 *
 * @author avijit
 * @param <T>
 */
public class ResponseDTO<T> {
    private int size;
    private T content;
    private int number;
    private Sort sort;
    private int totalPages;
    private int totalElements;
    private boolean last;
    private int numberOfElements;
    private boolean first;
    private Link _links;

    public ResponseDTO() {
    }

    public ResponseDTO(T content) {
        this.content = content;
    }

    public ResponseDTO(int size, T content, int number, Sort sort, int totalPages, int totalElements, boolean last, int numberOfElements, boolean first, Link _links) {
        this.size = size;
        this.content = content;
        this.number = number;
        this.sort = sort;
        this.totalPages = totalPages;
        this.totalElements = totalElements;
        this.last = last;
        this.numberOfElements = numberOfElements;
        this.first = first;
        this._links = _links;
    }

    public int getSize() {
        return size;
    }

    public T getContent() {
        return content;
    }

    public int getNumber() {
        return number;
    }

    public Sort getSort() {
        return sort;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public int getTotalElements() {
        return totalElements;
    }

    public boolean isLast() {
        return last;
    }

    public int getNumberOfElements() {
        return numberOfElements;
    }

    public boolean isFirst() {
        return first;
    }

    public Link getLinks() {
        return _links;
    }
}

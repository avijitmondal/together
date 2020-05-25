package com.avijitmondal.together.alive.service;

public interface Observer<T, D> {

    void update(T t, D d);
}

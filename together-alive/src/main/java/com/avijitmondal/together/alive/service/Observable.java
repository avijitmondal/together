package com.avijitmondal.together.alive.service;

public interface Observable<T, D> {

    void notify(T t, D d);
}

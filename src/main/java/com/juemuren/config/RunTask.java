package com.juemuren.config;

@FunctionalInterface
public interface RunTask<T> {
    T run() throws InterruptedException;
}

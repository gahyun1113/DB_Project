package com.db_project.db_project.service;

import org.springframework.stereotype.Service;

@Service
public class DeadlockService {

    private final Object resourceA = new Object();
    private final Object resourceB = new Object();

    public void methodA() {
        //리소스A를 잠금
        synchronized (resourceA) {
            System.out.println("Thread " + Thread.currentThread().getName() + " locked resourceA");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                System.out.println("메소드A에서 InterruptedException 발생");
//                Thread.currentThread().interrupt();
            }
            //리소스B를 잠그려고 하지만 이미 다른 쓰레드에서 리소스B를 잠금시킨 상태 점유하기를 대기함
            synchronized (resourceB) {
                System.out.println("Thread " + Thread.currentThread().getName() + " locked resourceB");
            }
        }
    }

    public void methodB() {
        //리소스B를 잠금
        synchronized (resourceB) {
            System.out.println("Thread " + Thread.currentThread().getName() + " locked resourceB");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                System.out.println("메소드B에서 InterruptedException 발생");
//                Thread.currentThread().interrupt();
            }
            //리소스A를 잠그려고 하지만 이미 다른 쓰레드에서 리소스B를 잠금시킨 상태 점유하기를 대기함
            synchronized (resourceA) {
                System.out.println("Thread " + Thread.currentThread().getName() + " locked resourceA");
            }
        }
    }
}

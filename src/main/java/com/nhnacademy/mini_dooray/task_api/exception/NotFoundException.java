package com.nhnacademy.mini_dooray.task_api.exception;

public class NotFoundException extends IllegalArgumentException {
    public NotFoundException() {
        super("값을 찾을수 없습니다.");
    }
}

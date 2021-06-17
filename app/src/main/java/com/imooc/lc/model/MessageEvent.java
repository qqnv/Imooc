package com.imooc.lc.model;

/**
 * @类名: MessageEvent
 * @描述:
 * @作者: huangchao
 * @时间: 2018/6/11 下午4:13
 * @版本: 1.0.0
 */
public class MessageEvent {
    private String message;

    public MessageEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

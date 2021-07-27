package com.laioffer.jupiter.external;

/**
 * @Description:
 * @Author RileyShen
 * @Create 2021-07-23
 */
public class TwitchException extends RuntimeException{
    public TwitchException(String errorMessage) {
        super(errorMessage);
    }
}

package com.laioffer.jupiter.db;

/**
 * @Description:
 * @Author RileyShen
 * @Create 2021-08-26
 */
public class MySQLException extends RuntimeException{
    public MySQLException(String errorMessage) {
        super(errorMessage);
    }
}

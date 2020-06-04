/*
 *  Copyright (c) 2020 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 *  license agreement you entered into with Jalasoft.
 */

package com.jalasoft.practice.common.exception;

/**
 * @author HP
 * @version 1.1
 */
public class InvalidDataException extends Exception {
    public InvalidDataException(String message, Throwable ex) {
        super(message, ex);
    }

    public InvalidDataException(String message) {
        super(message);
    }

    public InvalidDataException(Throwable ex) {
        super(ex);
    }
}

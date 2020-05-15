/*
 *  Copyright (c) 2020 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 *  license agreement you entered into with Jalasoft.
 */

package com.jalasoft.practice.model.extract.exception;

/**
 * @author HP
 * @version 1.1
 */
public class ExtractException extends Exception {

    private static final String MESSAGE = "Error extracting information from file.";

    public ExtractException(String currentMessage, Throwable ex) {
        super(currentMessage, ex);
    }

    public ExtractException(Throwable ex) {
        super(MESSAGE, ex);
    }
}

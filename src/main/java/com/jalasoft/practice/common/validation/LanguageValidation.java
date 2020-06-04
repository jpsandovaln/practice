/*
 *  Copyright (c) 2020 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 *  license agreement you entered into with Jalasoft.
 */

package com.jalasoft.practice.common.validation;

import com.jalasoft.practice.common.constant.ErrorMessageConstant;
import com.jalasoft.practice.common.exception.InvalidDataException;

import java.util.Arrays;
import java.util.List;

/**
 * @author HP
 * @version 1.1
 */
public class LanguageValidation implements IValidatorStrategy {

    private String lang;
    private final static List<String> LANGUAGES = Arrays.asList(
            "eng",
            "spa"
    );

    public LanguageValidation(String lang) {
        this.lang = lang;
    }

    @Override
    public void validate() throws InvalidDataException {
        if (!LANGUAGES.contains(this.lang)) {
            throw new InvalidDataException(ErrorMessageConstant.LANGUAGE_ERROR_MESSAGE);
        }
    }
}

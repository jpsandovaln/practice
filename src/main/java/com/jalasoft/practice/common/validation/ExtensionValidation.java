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
public class ExtensionValidation implements IValidatorStrategy {

    private String ext;
    private final static List<String> EXTENSIONS = Arrays.asList(
            "txt",
            "xmp"
    );

    public ExtensionValidation(String ext) {
        this.ext = ext;
    }

    @Override
    public void validate() throws InvalidDataException {
        if(!EXTENSIONS.contains(this.ext)) {
            throw new InvalidDataException(ErrorMessageConstant.EXTENSION_ERROR_MESSAGE);
        }
    }
}

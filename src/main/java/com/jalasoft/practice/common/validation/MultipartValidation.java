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
import com.jalasoft.practice.controller.exception.RequestParamInvalidException;
import com.sun.org.apache.bcel.internal.generic.PUSH;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author HP
 * @version 1.1
 */
public class MultipartValidation implements IValidatorStrategy {

    private MultipartFile file;
    public MultipartValidation(MultipartFile file) {
        this.file = file;
    }
    @Override
    public void validate() throws InvalidDataException {
        if (this.file == null || this.file.isEmpty() || this.file.getOriginalFilename().contains("..")) {
            throw new InvalidDataException(ErrorMessageConstant.MULTIPART_ERROR_MESSAGE);
        }
    }
}

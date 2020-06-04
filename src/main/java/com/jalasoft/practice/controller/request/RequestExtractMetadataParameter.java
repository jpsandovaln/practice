/*
 *  Copyright (c) 2020 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 *  license agreement you entered into with Jalasoft.
 */

package com.jalasoft.practice.controller.request;

import com.jalasoft.practice.common.exception.InvalidDataException;
import com.jalasoft.practice.common.validation.ExtensionValidation;
import com.jalasoft.practice.common.validation.IValidatorStrategy;
import com.jalasoft.practice.common.validation.MD5Validation;
import com.jalasoft.practice.common.validation.MultipartValidation;
import com.jalasoft.practice.common.validation.NotNullOrEmptyValidation;
import com.jalasoft.practice.common.validation.ValidationContext;
import com.jalasoft.practice.controller.exception.RequestParamInvalidException;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

/**
 * @author HP
 * @version 1.1
 */
public class RequestExtractMetadataParameter extends RequestParameter{
    private String outputType;
    private final static List<String> TYPE_LIST = Arrays.asList("txt","xmp");

    public RequestExtractMetadataParameter(String outputType, String md5, MultipartFile file) {
        super(md5, file);
        this.outputType = outputType;
    }

    public String getOutputType() {
        return outputType;
    }

    public void setOutputType(String outputType) {
        this.outputType = outputType;
    }

    @Override
    public void validate() throws InvalidDataException {
        List<IValidatorStrategy> strategyList = Arrays.asList(
                new NotNullOrEmptyValidation("md5", this.md5),
                new MD5Validation(this.md5),
                new MultipartValidation(this.file),
                new NotNullOrEmptyValidation("outputType", this.outputType),
                new ExtensionValidation(this.outputType)
        );
        ValidationContext context = new ValidationContext(strategyList);
        context.validate();
    }
}

/*
 *  Copyright (c) 2020 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 *  license agreement you entered into with Jalasoft.
 */

package com.jalasoft.practice.model.extract.parameter;

import com.jalasoft.practice.common.Util;
import com.jalasoft.practice.common.exception.InvalidDataException;
import com.jalasoft.practice.common.validation.FileValidation;
import com.jalasoft.practice.common.validation.IValidatorStrategy;
import com.jalasoft.practice.common.validation.LanguageValidation;
import com.jalasoft.practice.common.validation.MimeTypeValidation;
import com.jalasoft.practice.common.validation.NotNullOrEmptyValidation;
import com.jalasoft.practice.common.validation.ValidationContext;
import com.jalasoft.practice.model.extract.exception.ParameterInvalidException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;

/**
 * @author HP
 * @version 1.1
 */
public class ExtractTextParam extends Parameter {

    private String lang;
    private String tessData;
    private final static List<String> LANGUAGES = Arrays.asList("eng", "spa");

    public ExtractTextParam(File file, String lang, String tessData) {
        super(file);
        this.lang = lang;
        this.tessData = tessData;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getTessData() {
        return tessData;
    }

    public void setTessData(String tessData) {
        this.tessData = tessData;
    }

    @Override
    public void validate() throws InvalidDataException {
        List<IValidatorStrategy> strategyList = Arrays.asList(
                new FileValidation(this.inputFile, true),
                new MimeTypeValidation(Util.getMimeType(this.inputFile)),
                new NotNullOrEmptyValidation("tessData", this.tessData),
                new FileValidation(new File(this.tessData), false),
                new NotNullOrEmptyValidation("lang", this.lang),
                new LanguageValidation(this.lang)
        );

        ValidationContext context = new ValidationContext(strategyList);
        context.validate();
    }
}

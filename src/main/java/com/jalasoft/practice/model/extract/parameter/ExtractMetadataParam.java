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
import com.jalasoft.practice.common.validation.ExtensionValidation;
import com.jalasoft.practice.common.validation.FileValidation;
import com.jalasoft.practice.common.validation.IValidatorStrategy;
import com.jalasoft.practice.common.validation.LanguageValidation;
import com.jalasoft.practice.common.validation.MimeTypeValidation;
import com.jalasoft.practice.common.validation.NotNullOrEmptyValidation;
import com.jalasoft.practice.common.validation.ValidationContext;
import com.jalasoft.practice.model.extract.exception.ParameterInvalidException;

import java.io.File;
import java.util.Arrays;
import java.util.List;

/**
 * @author HP
 * @version 1.1
 */
public class ExtractMetadataParam extends Parameter {
    String type;
    String outDir;
    String exifToolBinaryDir;
    private final static List<String> TYPE_LIST = Arrays.asList("txt", "xmp");

    public ExtractMetadataParam(File inputFile, String type, String outDir, String exifToolBinaryDir) {
        super(inputFile);
        this.type = type;
        this.outDir = outDir;
        this.exifToolBinaryDir = exifToolBinaryDir;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOutDir() {
        return outDir;
    }

    public void setOutDir(String outDir) {
        this.outDir = outDir;
    }

    public String getExifToolBinaryDir() {
        return exifToolBinaryDir;
    }

    public void setExifToolBinaryDir(String exifToolBinaryDir) {
        this.exifToolBinaryDir = exifToolBinaryDir;
    }

    @Override
    public void validate() throws InvalidDataException {
        List<IValidatorStrategy> strategyList = Arrays.asList(
                new FileValidation(this.inputFile, true),
                new MimeTypeValidation(Util.getMimeType(this.inputFile)),
                new NotNullOrEmptyValidation("outDir", this.outDir),
                new FileValidation(new File(this.outDir), false),
                new FileValidation(new File(this.exifToolBinaryDir), true),
                new NotNullOrEmptyValidation("type", this.type),
                new ExtensionValidation(this.type)
        );

        ValidationContext context = new ValidationContext(strategyList);
        context.validate();
    }
}

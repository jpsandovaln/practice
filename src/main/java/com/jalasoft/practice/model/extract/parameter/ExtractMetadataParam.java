/*
 *  Copyright (c) 2020 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 *  license agreement you entered into with Jalasoft.
 */

package com.jalasoft.practice.model.extract.parameter;

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
    public void validate() throws ParameterInvalidException {
        if (!inputFile.exists()) {
            throw new ParameterInvalidException("The input file does not exist");
        }

        if(inputFile.isHidden()) {
            throw new ParameterInvalidException();
        }

        if(!inputFile.isFile()) {
            throw new ParameterInvalidException();
        }

        if(inputFile.toPath().toString().contains("..")) {
            throw new ParameterInvalidException("Invalid input file path.");
        }

        if (this.exifToolBinaryDir == null || this.exifToolBinaryDir.trim().isEmpty()) {
            throw new ParameterInvalidException("exifToolBinaryDir is null or empty");
        }

        File exifToolBinaryFolder = new File(this.exifToolBinaryDir);
        if (!exifToolBinaryFolder.exists()) {
            throw new ParameterInvalidException("exifToolBinaryDir", exifToolBinaryDir);
        }

        if (!exifToolBinaryFolder.isFile()) {
            throw new ParameterInvalidException("exifToolBinaryDir is not a file");
        }

        if (this.type.trim().isEmpty()) {
            throw new ParameterInvalidException();
        }

        if (!TYPE_LIST.contains(this.type)) {
            throw new ParameterInvalidException("type", type);
        }
    }
}

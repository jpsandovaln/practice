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

        String mimeType = "";
        try {
            mimeType = Files.probeContentType(inputFile.toPath());
        } catch(IOException ex) {
            throw new ParameterInvalidException(ex);
        }

        if (!mimeType.contains("image")) {
            throw new ParameterInvalidException("input file is not image.");
        }

        if (this.tessData == null || this.tessData.trim().isEmpty()) {
            throw new ParameterInvalidException("tessData is null or empty");
        }

        File tessDataFolder = new File(this.tessData);
        if (!tessDataFolder.exists()) {
            throw new ParameterInvalidException("tessData", tessData);
        }

        if (!tessDataFolder.isDirectory()) {
            throw new ParameterInvalidException("tessData is not a directory");
        }

        if (this.lang.trim().isEmpty()) {
            throw new ParameterInvalidException();
        }

        if (!LANGUAGES.contains(this.lang)) {
            throw new ParameterInvalidException("lang", lang);
        }
    }
}

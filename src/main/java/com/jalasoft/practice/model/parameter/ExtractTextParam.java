/*
 *  Copyright (c) 2020 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 *  license agreement you entered into with Jalasoft.
 */

package com.jalasoft.practice.model.parameter;

import java.io.File;

/**
 * @author HP
 * @version 1.1
 */
public class ExtractTextParam extends Parameter {

    private String lang;
    private String tessData;

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
    public void validate() throws Exception {
        super.validate();
        if (this.lang.trim().isEmpty()) {
            throw new Exception("error lang");
        }
        if(!"eng".equals(lang)) {
            throw new Exception("error lang not valid");
        }
    }
}

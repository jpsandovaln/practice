/*
 *  Copyright (c) 2020 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 *  license agreement you entered into with Jalasoft.
 */

package com.jalasoft.practice.model.parameter;

/**
 * @author HP
 * @version 1.1
 */
public class ExtractTextParam {
    private String imageFile;
    private String lang;
    private String tessData;

    public String getImageFile() {
        return imageFile;
    }

    public void setImageFile(String imageFile) {
        this.imageFile = imageFile;
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


}

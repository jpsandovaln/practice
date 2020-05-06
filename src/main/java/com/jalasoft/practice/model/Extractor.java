/*
 *  Copyright (c) 2020 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 *  license agreement you entered into with Jalasoft.
 */

package com.jalasoft.practice.model;

import com.jalasoft.practice.model.parameter.ExtractTextParam;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import java.io.File;
/**
 *
 * @author HP
 * @version 1.1
 */
public class Extractor {

    public String extract(ExtractTextParam param) {
        File image = new File(param.getImageFile());
        ITesseract ext = new Tesseract();
        ext.setDatapath(param.getTessData());
        ext.setLanguage(param.getLang());
        try {
            return ext.doOCR(image);
        } catch (TesseractException ex) {
            return "error";
        }
    }
}

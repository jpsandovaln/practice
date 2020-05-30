/*
 *  Copyright (c) 2020 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 *  license agreement you entered into with Jalasoft.
 */

package com.jalasoft.practice.model.extract;

import com.jalasoft.practice.model.extract.exception.ExtractException;
import com.jalasoft.practice.model.extract.exception.ParameterInvalidException;
import com.jalasoft.practice.model.extract.parameter.ExtractTextParam;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;

public class ExtractorTextFromImageTest {

    private final static String PATH = "src/test/resources/extractText/";
    private final static String TESS4J = "thirdParty/Tess4j/tessdata";

    @Test
    public void extractTextValidImage() throws ParameterInvalidException, ExtractException {
        ExtractTextParam param = new ExtractTextParam(
                new File(PATH + "number.png"),
                "eng",
                TESS4J
        );
        ExtractorTextFromImage ext = new ExtractorTextFromImage();
        assertEquals("468792", ext.extract(param).getText());
    }

    @Test
    public void extractTextValidImageWithoutText() throws ParameterInvalidException, ExtractException {
        ExtractTextParam param = new ExtractTextParam(
                new File(PATH + "susto.jpg"),
                "eng",
                TESS4J
        );
        ExtractorTextFromImage ext = new ExtractorTextFromImage();
        assertEquals("7; K", ext.extract(param).getText());
    }

    @Test(expected = ParameterInvalidException.class)
    public void extractTextFromTextFile() throws ParameterInvalidException, ExtractException {
        ExtractTextParam param = new ExtractTextParam(
                new File(PATH + "test.txt"),
                "eng",
                TESS4J
        );
        ExtractorTextFromImage ext = new ExtractorTextFromImage();
        ext.extract(param);

    }

    @Test(expected = ParameterInvalidException.class)
    public void extractTextInvalidTessdata() throws ParameterInvalidException, ExtractException {
        ExtractTextParam param = new ExtractTextParam(
                new File(PATH + "number.png"),
                "eng",
                TESS4J + "/invalid"
        );
        ExtractorTextFromImage ext = new ExtractorTextFromImage();
        ext.extract(param);
    }

    @Test(expected = ParameterInvalidException.class)
    public void extractTextInvalidParameter() throws ParameterInvalidException, ExtractException {
        ExtractorTextFromImage ext = new ExtractorTextFromImage();
        ext.extract(null);
    }
}

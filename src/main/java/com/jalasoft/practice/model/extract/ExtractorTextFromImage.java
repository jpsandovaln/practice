/*
 *  Copyright (c) 2020 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 *  license agreement you entered into with Jalasoft.
 */

package com.jalasoft.practice.model.extract;

import com.jalasoft.practice.common.exception.InvalidDataException;
import com.jalasoft.practice.model.extract.exception.ExtractException;
import com.jalasoft.practice.model.extract.exception.ParameterInvalidException;
import com.jalasoft.practice.model.extract.parameter.ExtractTextParam;
import com.jalasoft.practice.model.extract.parameter.Parameter;
import com.jalasoft.practice.model.extract.result.Result;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

/**
 *
 * @author HP
 * @version 1.1
 */
class ExtractorTextFromImage implements IExtractor<ExtractTextParam> {

    @Override
    public Result extract(ExtractTextParam param) throws InvalidDataException, ExtractException {
        try {
            param.validate();
            ITesseract ext = new Tesseract();
            ext.setDatapath(param.getTessData());
            ext.setLanguage(param.getLang());
            return new Result(ext.doOCR(param.getInputFile()));
        } catch (NullPointerException ex) {
            throw new InvalidDataException(ex);
        } catch (TesseractException ex) {
            throw new ExtractException(ex);
        }
    }
}

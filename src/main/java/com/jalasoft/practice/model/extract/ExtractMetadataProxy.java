/*
 *  Copyright (c) 2020 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 *  license agreement you entered into with Jalasoft.
 */

package com.jalasoft.practice.model.extract;

import com.jalasoft.practice.common.Util;
import com.jalasoft.practice.common.exception.InvalidDataException;
import com.jalasoft.practice.model.extract.exception.ExtractException;
import com.jalasoft.practice.model.extract.parameter.ExtractMetadataParam;
import com.jalasoft.practice.model.extract.result.Result;

/**
 * @author HP
 * @version 1.1
 */
public class ExtractMetadataProxy implements IExtractor<ExtractMetadataParam> {

    private IExtractor extractor;

    public ExtractMetadataProxy() {
        this.extractor = new ExtractMetadataFromFile();
    }

    @Override
    public Result extract(ExtractMetadataParam param) throws InvalidDataException, ExtractException {
        String filePath = extractor.extract(param).getText();
        return new Result(Util.zipFile(filePath));
    }
}

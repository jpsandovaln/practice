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
import com.jalasoft.practice.model.extract.parameter.ExtractMetadataParam;
import com.jalasoft.practice.model.extract.parameter.ExtractTextParam;
import com.jalasoft.practice.model.extract.parameter.Parameter;
import com.jalasoft.practice.model.extract.result.Result;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author HP
 * @version 1.1
 */
public class ExtractMetadataFromFile implements IExtractor<ExtractMetadataParam> {
    @Override
    public Result extract(ExtractMetadataParam param) throws InvalidDataException, ExtractException {
        param.validate();

        try {
            String outputFile = param.getOutDir() + param.getInputFile().getName() + "." + param.getType();
            String command = String.format(
                    "%s %s > %s",
                    param.getExifToolBinaryDir(),
                    param.getInputFile().getAbsolutePath(),
                    outputFile
            );
            Files.createDirectories(Paths.get(param.getOutDir()));
            ProcessBuilder builder = new ProcessBuilder("cmd", "/c", "\"" + command + "\"");
            builder.redirectErrorStream(true);
            Process process = builder.start();
            process.waitFor();
            return new Result(outputFile);
        } catch (IOException | InterruptedException ex) {
            throw new ExtractException(ex);
        }
    }
}

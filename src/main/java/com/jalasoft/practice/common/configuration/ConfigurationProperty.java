/*
 *  Copyright (c) 2020 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 *  license agreement you entered into with Jalasoft.
 */

package com.jalasoft.practice.common.configuration;

import com.jalasoft.practice.common.constant.PropertyConstant;
import com.jalasoft.practice.common.exception.InvalidDataException;

import java.util.List;

/**
 * @author HP
 * @version 1.1
 */
public class ConfigurationProperty {
    public static List<String> getLanguages() throws InvalidDataException{
        return PropertyHandler.getInstance().getValueAsList(PropertyConstant.EXTRACT_TEXT_LANGUAGE);
    }

    public static List<String> getMimeType() throws InvalidDataException {
        return PropertyHandler.getInstance().getValueAsList(PropertyConstant.EXTRACT_TEXT_MIME_TYPE);
    }

    public static List<String> getExtension() throws InvalidDataException {
        return PropertyHandler.getInstance().getValueAsList(PropertyConstant.EXTRACT_TEXT_EXTENSION);
    }
}

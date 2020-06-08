/*
 *  Copyright (c) 2020 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 *  license agreement you entered into with Jalasoft.
 */

package com.jalasoft.practice.model.extract;

import java.util.HashMap;
import java.util.Map;

/**
 * @author HP
 * @version 1.1
 */
public class ExtractFactory {

    public final static String TEXT = "text";
    public final static String METADATA = "metadata";
    public final static String PROXY = "proxy";
    public final static Map<String, IExtractor> extractMap = new HashMap<String, IExtractor>() {
        {
            put(TEXT, new ExtractorTextFromImage());
            put(METADATA, new ExtractMetadataFromFile());
            put(PROXY, new ExtractMetadataProxy());
        }
    };

    public static IExtractor createExtractor(String extract) {
        return  extractMap.get(extract);
    }
}

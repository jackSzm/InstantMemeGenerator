package com.szmelter.max.instantmemegenerator.rest.data;


import java.util.HashMap;
import java.util.Map;

import lombok.Getter;

public class MemeBuilderResponse {

    public class Data {
        @Getter
        private String url;
        private String pageUrl;
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    }

    private boolean success;

    @Getter
    private Data data;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
}

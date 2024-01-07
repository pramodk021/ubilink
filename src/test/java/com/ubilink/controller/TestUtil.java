package com.ubilink.controller;

import java.nio.charset.Charset;

import org.springframework.http.MediaType;

public class TestUtil {

    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.TEXT_PLAIN.getType(),
                                                                        MediaType.TEXT_PLAIN.getSubtype(),                        
                                                                        Charset.forName("utf8")                     
                                                                        );
}
package com.astamuse.asta4d.web.dispatch.response.writer;

import javax.servlet.http.HttpServletResponse;

import com.astamuse.asta4d.web.util.JsonUtil;

public class JsonWriter implements ContentWriter<Object> {

    @Override
    public void writeResponse(HttpServletResponse response, Object content) throws Exception {
        response.setContentType("application/json");
        JsonUtil.toJson(response.getOutputStream(), content);
    }

}
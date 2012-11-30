package com.astamuse.asta4d.web.dispatch.response.writer;

import javax.servlet.http.HttpServletResponse;

public class DefaultContentWriter implements ContentWriter {

    @Override
    public void writeResponse(HttpServletResponse response, Object content) throws Exception {
        if (content != null) {
            response.getOutputStream().write(content.toString().getBytes());
        } else {
            response.getOutputStream().write("##null##".getBytes());
        }

    }

}
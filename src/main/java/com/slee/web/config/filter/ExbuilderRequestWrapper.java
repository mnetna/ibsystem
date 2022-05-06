package com.slee.web.config.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.slee.web.util.ExbuilderUtil;
import org.springframework.util.StringUtils;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;
import java.util.HashMap;

@SuppressWarnings("unchecked")
public class ExbuilderRequestWrapper extends HttpServletRequestWrapper {

    private String body;

    public ExbuilderRequestWrapper(HttpServletRequest request) throws IOException {
        super(request);

        ObjectMapper objectMapper = new ObjectMapper();

        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;
        try {
            InputStream inputStream = request.getInputStream();
            if (inputStream != null) {
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                char[] charBuffer = new char[128];
                int bytesRead = -1;
                while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                    stringBuilder.append(charBuffer, 0, bytesRead);
                }
            }
        } catch (IOException ex) {
            throw ex;
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException ex) {
                    throw ex;
                }
            }
        }

        // TODO ObjectMapper 별도 공간으로 처리
//        body = stringBuilder.toString();
        String str = stringBuilder.toString();
        if (!StringUtils.isEmpty(str)) {
            body = objectMapper.writeValueAsString(
                    ExbuilderUtil.getInputFromRequestMap(
                            objectMapper.readValue(str, HashMap.class)));
        }
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(body.getBytes());
        ServletInputStream servletInputStream = new ServletInputStream() {
            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener listener) {
            }

            public int read() throws IOException {
                return byteArrayInputStream.read();
            }
        };
        return servletInputStream;
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(this.getInputStream()));
    }

//    @Override
//    public String getServletPath() {
//        final String originalPath = ((HttpServletRequest)getRequest()).getServletPath();
//        return originalPath.replace("/ex", ""); // Must be consistent with getRequestURL()
//    }

    //Use this method to read the request body N times
    public String getBody() {
        return this.body;
    }
}

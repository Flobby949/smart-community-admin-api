package com.soft2242.one.entity;

import org.apache.http.entity.AbstractHttpEntity;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author : Flobby
 * @program : community-client-api
 * @description :
 * @create : 2023-06-06 12:04
 **/

public class BooleanEntity extends AbstractHttpEntity {
    private boolean data;

    public BooleanEntity(Integer cmd) {
        this.data = cmd == 1;
    }

    @Override
    public boolean isRepeatable() {
        return false;
    }

    @Override
    public long getContentLength() {
        return 0;
    }

    @Override
    public InputStream getContent() throws IOException, UnsupportedOperationException {
        return null;
    }

    @Override
    public void writeTo(OutputStream outputStream) throws IOException {

    }

    @Override
    public boolean isStreaming() {
        return false;
    }
}

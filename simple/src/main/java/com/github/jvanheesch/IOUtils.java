package com.github.jvanheesch;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public final class IOUtils {
    private IOUtils() {
    }

    /**
     * See https://stackoverflow.com/a/35446009/1939921.
     */
    public static byte[] read(InputStream inputStream) throws IOException {
        try (
                BufferedInputStream bis = new BufferedInputStream(inputStream);
                ByteArrayOutputStream buf = new ByteArrayOutputStream()
        ) {
            int result = bis.read();
            while (result != -1) {
                buf.write((byte) result);
                result = bis.read();
            }
            return buf.toByteArray();
        }
    }
}

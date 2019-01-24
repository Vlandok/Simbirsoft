package com.vlad.lesson4.utils;

import android.content.Context;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

public final class JsonSupport {

    private final static String ENCODING_JSON = "cp1251";

    public static String loadJSONFromAsset(Context context, String fileNameJsonWithFormat) {
        String json;
        try (InputStream is = context.getAssets().open(fileNameJsonWithFormat)) {
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            json = new String(buffer, Charset.forName(ENCODING_JSON));
            return json;
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }

}

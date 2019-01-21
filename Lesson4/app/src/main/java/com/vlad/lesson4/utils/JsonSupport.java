package com.vlad.lesson4.utils;

import android.content.Context;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

public class JsonSupport {

    private final static String ENCODING_JSON = "cp1251";

    public static String loadJSONFromAsset(Context context, String fileNameJsonWithFormat) {
        String json;
        InputStream is = null;
        try {
            is = context.getAssets().open(fileNameJsonWithFormat);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            json = new String(buffer, Charset.forName(ENCODING_JSON));
        } catch (
                IOException ex) {
            ex.printStackTrace();
            return null;
        } finally {
            try {
                if (is != null)
                    is.close();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return json;
    }

}

package com.hammersmith.ku.coffeecorner.utils;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by ku on 12/20/16.
 */

public class Utils {
    public static String getExternalPathPrefix(Context context,
                                               String folderName) {
        if (TextUtils.isEmpty(folderName)) {
            folderName = null;
        }
        final File dir = context.getApplicationContext().getExternalFilesDir(
                folderName);
//        log("dir:" + dir);
        if (dir != null) {
            return dir.getAbsolutePath() + File.separator;
        }
        return null;
    }

    public static String urlEncode(String str) {
        try {
            str = URLEncoder.encode(str, "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return str;
    }


    public static String doHttpDownload2(Context ctx, String requesturl,
                                         String folderName, String filename) {
        log("doHttpDownload2(" + ctx + "," + requesturl + "," + filename + ")");
        URL theurl = null;
        int responseCode = -1;
        InputStream is = null;
        byte[] buffer = new byte[1024];
        HttpURLConnection httpcon = null;
        String result = null;
        try {
            int lastsign = requesturl.lastIndexOf("/");
            if (TextUtils.isEmpty(filename))
                filename = requesturl.substring(lastsign + 1);
            theurl = new URL(requesturl);
            httpcon = (HttpURLConnection) theurl.openConnection();
            httpcon.setConnectTimeout(600000);
            httpcon.setReadTimeout(600000);
            httpcon.setUseCaches(false);
            httpcon.setInstanceFollowRedirects(true);
            httpcon.setRequestProperty("Cache-Control",
                    "no-store,max-age=0,no-cache");
            httpcon.setRequestProperty("Expires", "0");
            httpcon.setRequestProperty("Pragma", "no-cache");
            httpcon.setRequestProperty("Connection", "close");
            httpcon.setRequestProperty("Charset", "utf-8");
            httpcon.setRequestMethod("GET");
            httpcon.setDoInput(true);
            httpcon.setDoOutput(false);
            responseCode = httpcon.getResponseCode();
//            log("responseCode:"+responseCode);
            if (responseCode == HttpURLConnection.HTTP_OK) {
                int readed = 0;
                is = httpcon.getInputStream();
                String path = getExternalPathPrefix(ctx, folderName) + filename;
//                log("-->path:"+path);
                FileOutputStream fos = new FileOutputStream(path);
                do {
                    readed = is.read(buffer);
                    if (readed > 0) {
                        fos.write(buffer, 0, readed);
                    }
                } while (readed > 0);
                fos.flush();
                fos.close();
                fos = null;

                result = filename;
//                log("-->result:"+result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (Exception e2) {
            }
            is = null;
            if (httpcon != null) {
                try {
                    httpcon.disconnect();
                } catch (Exception e) {
                }
            }
            httpcon = null;
            theurl = null;
            buffer = null;
        }

        return result;
    }

    private static void log(String str) {
        Log.e("Utils", str);
    }
}



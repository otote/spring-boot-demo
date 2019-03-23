package cn.otote.springbootdemo.util;

import com.sun.org.apache.bcel.internal.generic.NEW;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/***
 * Created on 2019-03-23 20:38
 * Created by otote
 *
 ***/
public class HttpUtils {


    public static String postByMap(String url, Map<String, String> map, String encodeing) throws IOException {
        String result = "";

        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();

        HttpPost httpPost = new HttpPost(url);

        List<NameValuePair> nameValuePairs = new ArrayList<>();
        if (map != null) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                nameValuePairs.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
        }

        if (encodeing == null) {
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, encodeing));
        } else {
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, encodeing));
        }

        httpPost.setHeader("Content-type", "application/x-www-form-urlencoded");
        httpPost.setHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");

        CloseableHttpResponse response = closeableHttpClient.execute(httpPost);

        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            result = EntityUtils.toString(response.getEntity(), "utf-8");
        }

        response.close();

        return result;
    }


    public static String postByJson(String url, String json, String encoding) throws IOException {
        String result = "";

        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();

        HttpPost httpPost = new HttpPost(url);

        StringEntity stringEntity = new StringEntity(json, ContentType.APPLICATION_JSON);
        if (encoding == null) {
            stringEntity.setContentEncoding("utf-8");
        } else {
            stringEntity.setContentEncoding(encoding);
        }

        httpPost.setEntity(stringEntity);

        CloseableHttpResponse response = closeableHttpClient.execute(httpPost);

        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            result = EntityUtils.toString(response.getEntity(), "utf-8");
        }

        response.close();

        return result;
    }


    public static String get(String url) throws IOException {
        String result = "";

        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();

        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader("Content-type", "application/json");

        CloseableHttpResponse response = closeableHttpClient.execute(httpGet);


        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            result = EntityUtils.toString(response.getEntity(), "utf-8");
        }

        response.close();

        return result;
    }


    public static void main(String[] args) throws IOException {
        String url = "http://spring.otote.cn";

        String result = get(url);

        System.out.println(result);

    }
}


package com.frame.easy.util.http;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;

/**
 * HttpUtil
 *
 * @Author tengchong
 * @Date 2019-04-09
 */
public class HttpUtil {
    private static String[] IEBrowserSignals = {"MSIE", "Trident", "Edge"};

    /**
     * 是否是IE浏览器
     *
     * @param request request
     * @return true/false
     */
    private static boolean isMSBrowser(HttpServletRequest request) {
        String userAgent = request.getHeader("User-Agent");
        for (String signal : IEBrowserSignals) {
            if (userAgent.contains(signal)){
                return true;
            }
        }
        return false;
    }

    /**
     * 获取 ResponseEntity<FileSystemResource> 用于文件下载
     * @param file 文件
     * @param fileName 显示文件名称
     * @param request HttpServletRequest
     * @return ResponseEntity
     * @throws UnsupportedEncodingException 异常
     */
    public static ResponseEntity<FileSystemResource> getResponseEntity(File file, String fileName, HttpServletRequest request) throws UnsupportedEncodingException {
        if (file == null || !file.exists()) {
            return null;
        }
        boolean isMSIE = HttpUtil.isMSBrowser(request);
        if (isMSIE) {
            fileName = URLEncoder.encode(fileName, "UTF-8");
        } else {
            fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        headers.add("Last-Modified", new Date().toString());
        headers.add("ETag", String.valueOf(System.currentTimeMillis()));

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(new FileSystemResource(file));
    }

}

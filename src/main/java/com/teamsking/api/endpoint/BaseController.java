package com.teamsking.api.endpoint;

import com.github.pagehelper.PageInfo;
import com.teamsking.util.Lang;
import eu.bitwalker.useragentutils.UserAgent;
import java.io.UnsupportedEncodingException;
import java.util.List;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author ynfeng
 */
public class BaseController {
    @Autowired
    protected HttpServletRequest request;

    @Autowired
    protected HttpServletResponse response;


    protected void addCookie(String name, String value, int age) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        cookie.setMaxAge(age);
        response.addCookie(cookie);
    }

    public HttpServletRequest request() {
        return request;
    }

    public String base() {
        return request.getContextPath();
    }

    public UserAgent ua() {
        return new UserAgent(request.getHeader("user-agent"));
    }

    public int fixPage(int page) {
        return ((page <= 0) ? 1 : page);
    }

    public String fixSearchKey(String key) {
        if (("get".equalsIgnoreCase(request.getMethod())) && (Lang.isWin())) {
            key = (Strings.isBlank(key)) ? "" : key;
            try {
                return new String(key.getBytes("iso-8859-1"), request.getCharacterEncoding());
            } catch (UnsupportedEncodingException e) {
                return "";
            }
        }
        return ((Strings.isBlank(key)) ? "" : key);
    }

    protected String getCookie(String name) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(name)) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    protected PageInfo warpPage(List list){
        return new PageInfo(list);
    }

    public String getNameSpace() {
        return null;
    }

    public String ip() {
        return Lang.getIP(request);
    }
}

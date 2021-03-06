package com.dianrong.common.uniauth.cas.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dianrong.common.uniauth.common.server.UniauthLocaleChangeInterceptor;
import com.dianrong.common.uniauth.common.server.UniauthLocaleInfoHolder;

import lombok.extern.slf4j.Slf4j;

/**
 * . 国际化处理相关的请求处理
 * 
 * @author wanglin
 */
@Controller
@RequestMapping("/i18n")
@Slf4j
public class CasI18nController {
    
    /**.
     * 参数
     */
    private final String setLocaleParameterKey = "locale";

    /**
     * . 获取当前系统的语言信息
     * 
     * @param request
     * @param response
     */
    @RequestMapping(value = "/getLanguage", method = RequestMethod.GET)
    public void getLocaleLanguage(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.getWriter().write(UniauthLocaleInfoHolder.getLocale().toString());
        } catch (IOException e) {
            log.warn("query i18n language failed");
        } ;
    }

    /**
     * 设置当前的用户的语言信息
     * 
     * @param request
     * @param response
     */
    @RequestMapping(value = "/setLanguage", method = {RequestMethod.GET, RequestMethod.POST})
    public void setLocaleLanguage(HttpServletRequest request, HttpServletResponse response) {
        try {
            HttpSession session = request.getSession(false);
            String newLocaleStr =  request.getParameter(setLocaleParameterKey);
            if(session != null && newLocaleStr != null) {
                session.setAttribute(UniauthLocaleChangeInterceptor.sessionName ,StringUtils.parseLocaleString(newLocaleStr));
            }
            response.getWriter().write("success");
        } catch (IOException e) {
            log.warn("refresh i18n language failed");
        } ;
    }
}

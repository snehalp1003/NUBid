/**
 * 
 */
package com.me.nubid.interceptors;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Snehal Patel
 */
public class MainInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler) throws Exception {
        String userEmail = request.getParameter("userEmail");
        String userPassword = request.getParameter("userPassword");

        if (userEmail != null && (userEmail.contains("<script>") || userEmail.contains("'")
                || userEmail.contains("SELECT"))) {
            PrintWriter pw = response.getWriter();
            pw.print("Invalid username !");
            return false;
        }
        if (userPassword != null && (userPassword.contains("<script>") || userPassword.contains("'")
                || userPassword.contains("SELECT"))) {
            PrintWriter pw = response.getWriter();
            pw.print("Invalid password !");
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
        System.out.println("POST HANDLE");
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
            HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        System.out.println("AFTER COMPLETION");
    }
}

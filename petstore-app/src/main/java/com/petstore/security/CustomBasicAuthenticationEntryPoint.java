package com.petstore.security;

import com.petstore.exceptions.PetStoreExceptionMsg;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * User: Ionut Barau (ionutbarau)
 * Project: petstore
 * Date: 27/04/2017.
 * Time: 13:43
 */
public class CustomBasicAuthenticationEntryPoint extends BasicAuthenticationEntryPoint {

    @Override
    public void commence(final HttpServletRequest request,
                         final HttpServletResponse response,
                         final AuthenticationException authException) throws IOException, ServletException {
        //Authentication failed, send error response.
        response.addHeader("WWW-Authenticate", "BasicCustom realm=" + getRealmName() + "");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        PrintWriter writer = response.getWriter();
        writer.println("{\"msg\":\"" + PetStoreExceptionMsg.INVALID_CREDENTIALS + "\"}");
        return;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        setRealmName("PETSTORE_REALM");
        super.afterPropertiesSet();
    }

}

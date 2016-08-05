package com.auth0.example;

import com.auth0.web.Auth0CallbackHandler;
import com.auth0.web.Auth0User;
import com.auth0.web.SessionUtils;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class CallbackController extends Auth0CallbackHandler {


    private static final String SUBS_PATH = "/subs";
	private static final String PUB_PATH = "/upload";
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "${auth0.loginCallback}", method = RequestMethod.GET)
    protected void callback(final HttpServletRequest req, final HttpServletResponse res)
            throws ServletException, IOException {

	
        logger.debug("Performing callback");
        super.handle(req, res);
    }
    
    @Override
    protected void onSuccess(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
    	
    	final Auth0User user = SessionUtils.getAuth0User(req);
    	logger.debug("user id is : " + user.getUserId());
    	
    	
    	if (user.getUserId().startsWith("linkedin")) {
    		
    		// social login
    		logger.debug("redirecto to subscriptions...");
    		//res.sendRedirect(req.getContextPath() + SUBS_PATH);
    		res.sendRedirect("http://localhost:8080" + SUBS_PATH);
    		
    	} else {
    		
    		// admin login
    		logger.debug("redirecto to publications...");
    		//res.sendRedirect(req.getContextPath() + PUB_PATH);
    		res.sendRedirect("http://localhost:8080" + PUB_PATH);
    		
    	}
    	
    }

}

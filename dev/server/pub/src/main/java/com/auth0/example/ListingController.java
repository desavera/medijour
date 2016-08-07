package com.auth0.example;

import com.auth0.spring.security.mvc.Auth0JWTToken;
import com.auth0.web.Auth0Config;
import com.auth0.web.Auth0User;
import com.auth0.web.SessionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Map;

@Controller
public class ListingController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    AdminService adminService;

    private Auth0Config auth0Config;

    @Autowired
    public ListingController(Auth0Config auth0Config) {
        this.auth0Config = auth0Config;
    }

    @RequestMapping(value="/portal/listing", method = RequestMethod.GET)
    protected String home(final Map<String, Object> model, final HttpServletRequest req, final Principal principal) {
        logger.info("Home page");
        final String name = principal.getName();
        logger.info("Principal name: " + name);
        adminChecks((Auth0JWTToken) principal);
        final Auth0User user = SessionUtils.getAuth0User(req);
        model.put("user", user);


	String[] journalsList = new String[2];
	journalsList[0] = new String("article 1");
	journalsList[1] = new String("article 2");

	model.put("journalsList",journalsList);

        return "listing";
    }

    /**
     *  Simple illustration only
     */
    private void adminChecks(final Auth0JWTToken principal) {
        for(final GrantedAuthority grantedAuthority: principal.getAuthorities()) {
            final String authority = grantedAuthority.getAuthority();
            logger.info(authority);
            if (("ROLE_ADMIN".equals(authority))) {
                // just a simple callout to demonstrate role based authorization at service level
                // non-Admin user would be rejected trying to call this service
                adminService.ensureAdmin();
            }
        }
    }

}

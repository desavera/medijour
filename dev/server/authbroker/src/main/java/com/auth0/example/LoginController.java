package com.auth0.example;

import com.auth0.web.Auth0Config;
import com.auth0.web.NonceUtils;
import com.auth0.web.SessionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class LoginController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private Auth0Config auth0Config;

    private AppConfig appConfig;

    @Autowired
    public LoginController(final Auth0Config auth0Config, final AppConfig appConfig) {
        this.auth0Config = auth0Config;
        this.appConfig = appConfig;
    }

    @RequestMapping(value="/login", method = RequestMethod.GET)
    protected String login(final Map<String, Object> model, final HttpServletRequest req) {
        logger.debug("Performing login");
        detectError(model);
        // add Nonce to storage
        NonceUtils.addNonceToStorage(req);
        model.put("clientId", auth0Config.getClientId());
        model.put("domain", auth0Config.getDomain());
        model.put("loginCallback", auth0Config.getLoginCallback());
        model.put("state", SessionUtils.getState(req));
        model.put("connection", appConfig.getConnection());
        // for this sample only, this just allows configuration to
        // use Lock Widget or Auth0.js for login presentation
        // should only enable loginCustom for DB connection
        return appConfig.isCustomLogin() ? "loginCustom" : "login";
    }

    private void detectError(final Map<String, Object> model) {
        if (model.get("error") != null) {
            model.put("error", true);
        } else {
            model.put("error", false);
        }
    }


}

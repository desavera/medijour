package com.acompany.photoi.test;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import com.acompany.photoi.service.apigateway.APIGatewayApplication;



@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(APIGatewayApplication.class)
@WebIntegrationTest
public class APIGatewayServiceTest {

	
   
    final String uri = "http://localhost:8080/linkedin";
	final Logger logger = Logger.getLogger(APIGatewayServiceTest.class);
	
	RestTemplate template = new TestRestTemplate();
	
	@Autowired
    private WebApplicationContext wac;


	@Test
	public void testAdminLogin() throws Exception {
        String result = template.getForObject(uri, String.class);
        
        logger.debug(result);
	}
	
    
 }

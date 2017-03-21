/**
 * 
 */
package com.jboss.demo.fuse;

import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.spring.boot.CamelContextConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author scottes
 *
 */
@Configuration
public class MyAppConfig {
	@Bean
	CamelContextConfiguration contextConfiguration() {
		return new CamelContextConfiguration() {
			@Override
			public void beforeApplicationStart(CamelContext context) {
		    	((DefaultCamelContext)context).setName("camel-context-1");
			}

			@Override
			public void afterApplicationStart(CamelContext camelContext) {

			}
		};
	}

}

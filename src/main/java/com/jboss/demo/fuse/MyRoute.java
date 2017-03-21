/**
 * 
 */
package com.jboss.demo.fuse;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.servlet.CamelHttpTransportServlet;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author scottes
 *
 */
@Component
public class MyRoute extends RouteBuilder {
	


    @Bean
    ServletRegistrationBean servletRegistrationBean() {
        ServletRegistrationBean servlet = new ServletRegistrationBean(
            new CamelHttpTransportServlet(), "/camel-rest/*");
        servlet.setName("CamelServlet");
        return servlet;
    }
	
	@Override
    public void configure() throws Exception {
//    	((DefaultCamelContext)this.getContext()).setName("camel-context-1");
		restConfiguration()
			.component("servlet")
			.contextPath("/camel-rest").apiContextPath("/api-doc")
	        .apiProperty("api.title", "Camel REST API")
	        .apiProperty("api.version", "1.0")
	        .apiProperty("cors", "true")
	        .apiContextRouteId("doc-api")
	        .bindingMode(RestBindingMode.json)
	        .dataFormatProperty("prettyPrint", "true");
		
    	rest("/say")
	        .get("/hello").to("direct:hello")
	        .get("/bye").to("direct:bye");

	    from("direct:hello")
	        .transform().constant("Hello World");
	    from("direct:bye")
	        .transform().constant("Bye World");
    }

}

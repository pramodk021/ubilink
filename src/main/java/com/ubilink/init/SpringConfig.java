package com.ubilink.init;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.mvc.annotation.DefaultAnnotationHandlerMapping;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author Petri Kainulainen
 */
@Configuration
@EnableWebMvc
@PropertySource("classpath:application.properties")
@ComponentScan(basePackages = { "com.ubilink" })
//@EnableSwagger2
public class SpringConfig extends WebMvcConfigurerAdapter {

	private static final String VIEW_RESOLVER_PREFIX = "/WEB-INF/pages/";
	private static final String VIEW_RESOLVER_SUFFIX = ".jsp";

	// private static final String DATA_SCRIPT_FILE_PATH="/dbscript/ubilink.sql";

	@Bean
	public ViewResolver viewResolver() {

		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();

		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix(VIEW_RESOLVER_PREFIX);
		viewResolver.setSuffix(VIEW_RESOLVER_SUFFIX);

		return viewResolver;
	}

	@Bean
	public HandlerMapping handlerMapping() {
		return new DefaultAnnotationHandlerMapping();
	}

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	/*
	 * @Override public void addResourceHandlers(ResourceHandlerRegistry registry) {
	 * registry.addResourceHandler("/static/**").addResourceLocations("/static/");
	 * registry.addResourceHandler("/resources/**").addResourceLocations(
	 * "/resources/");
	 * 
	 * try { DataInitializer.restoreDatabase(MY_SQL_PATH,
	 * getClass().getResourceAsStream(DATA_SCRIPT_FILE_PATH)); } catch (IOException
	 * | InterruptedException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); }
	 */

	@Bean
	public Docket api() {
		// @formatter:off
		// Register the controllers to swagger
		// Also it is configuring the Swagger Docket
		return new Docket(DocumentationType.SWAGGER_2).select()
				/*
				 * // .apis(RequestHandlerSelectors.any())
				 * .apis(RequestHandlerSelectors.basePackage("com.ubilink.controller"))
				 * .apis(Predicates.not(RequestHandlerSelectors.basePackage(
				 * "org.springframework.boot"))) // .paths(PathSelectors.any())
				 * .paths(PathSelectors.ant("/swagger2-demo")) .build();
				 */
				.apis(RequestHandlerSelectors.any()).paths(PathSelectors.any()).build();
		// @formatter:on
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// enabling swagger-ui part for visual documentation
		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	}

	@Bean(name = "multipartResolver")
	public org.springframework.web.multipart.commons.CommonsMultipartResolver getBeanCommonsMultipartResolver() {
		org.springframework.web.multipart.commons.CommonsMultipartResolver commonsMultipartResolver = new org.springframework.web.multipart.commons.CommonsMultipartResolver();
		return commonsMultipartResolver;
	}

	/*
	 * @Bean public SimpleMappingExceptionResolver exceptionResolver() {
	 * SimpleMappingExceptionResolver exceptionResolver = new
	 * SimpleMappingExceptionResolver();
	 * 
	 * Properties exceptionMappings = new Properties();
	 * 
	 * //exceptionMappings.put(
	 * "net.petrikainulainen.spring.testmvc.todo.exception.TodoNotFoundException",
	 * "error/404"); // exceptionMappings.put("java.lang.Exception", "error/error");
	 * // exceptionMappings.put("java.lang.RuntimeException", "error/error");
	 * 
	 * exceptionResolver.setExceptionMappings(exceptionMappings);
	 * 
	 * Properties statusCodes = new Properties();
	 * 
	 * statusCodes.put("error/404", "404"); statusCodes.put("error/error", "500");
	 * 
	 * exceptionResolver.setStatusCodes(statusCodes);
	 * 
	 * return exceptionResolver; }
	 */

	/*
	 * @Bean(name="fileValidator") public FileValidator getFileValidator() {
	 * FileValidator fileValidator=new FileValidator(); return fileValidator; }
	 */

	/*
	 * Here we register the Hibernate4Module into an ObjectMapper, then set this
	 * custom-configured ObjectMapper to the MessageConverter and return it to be
	 * added to the HttpMessageConverters of our application
	 */
	/*
	 * public MappingJackson2HttpMessageConverter jacksonMessageConverter(){
	 * MappingJackson2HttpMessageConverter messageConverter = new
	 * MappingJackson2HttpMessageConverter();
	 * 
	 * ObjectMapper mapper = new ObjectMapper(); //Registering Hibernate4Module to
	 * support lazy objects mapper.registerModule(new Hibernate4Module());
	 * 
	 * messageConverter.setObjectMapper(mapper); return messageConverter;
	 * 
	 * }
	 * 
	 * @Override public void
	 * configureMessageConverters(List<HttpMessageConverter<?>> converters) { //Here
	 * we add our custom-configured HttpMessageConverter
	 * converters.add(jacksonMessageConverter());
	 * super.configureMessageConverters(converters); }
	 */

}

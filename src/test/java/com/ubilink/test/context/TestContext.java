package com.ubilink.test.context;

import org.mockito.Mockito;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import com.ubilink.service.ImgService;
import com.ubilink.service.MapfavouriteService;
import com.ubilink.service.OfferService;
import com.ubilink.service.PlaceCatService;
import com.ubilink.service.PlaceService;
import com.ubilink.service.ProductCategoryService;
import com.ubilink.service.SocialMediaService;
import com.ubilink.service.UserService;

@Configuration
public class TestContext {

	private static final String MESSAGE_SOURCE_BASE_NAME = "messages";

	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename(MESSAGE_SOURCE_BASE_NAME);
		messageSource.setUseCodeAsDefaultMessage(true);

		return messageSource;
	}

	@Bean
	public LocalValidatorFactoryBean validator() {
		return new LocalValidatorFactoryBean();
	}

	@Bean
	public UserService userService() {
		return Mockito.mock(UserService.class);
	}

	@Bean
	public PlaceService mallService() {
		return Mockito.mock(PlaceService.class);
	}

	@Bean
	public OfferService offerService() {
		return Mockito.mock(OfferService.class);
	}
	
	//NTR
	/*@Bean
	public RetailerService retailerService() {
		return Mockito.mock(RetailerService.class);
	}*/
	
	@Bean
	public ProductCategoryService productCategoryService() {
		return Mockito.mock(ProductCategoryService.class);
	}
	
	@Bean
	public ImgService imageService() {
		return Mockito.mock(ImgService.class);
	}
	
	@Bean
	public MapfavouriteService mapFavouriteService() {
		return Mockito.mock(MapfavouriteService.class);
	}
	
	@Bean
	public SocialMediaService socialMediaService() {
		return Mockito.mock(SocialMediaService.class);
	}
	
	@Bean
	public PlaceCatService placeCatService() {
		return Mockito.mock(PlaceCatService.class);
	}
}
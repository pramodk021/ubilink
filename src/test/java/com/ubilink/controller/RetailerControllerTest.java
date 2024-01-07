/*
 * package com.ubilink.controller;
 * 
 * import static
 * org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
 * import static
 * org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
 * import static
 * org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
 * 
 * import org.junit.Before; import org.junit.Test; import
 * org.junit.runner.RunWith; import org.mockito.Mockito; import
 * org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.test.context.ContextConfiguration; import
 * org.springframework.test.context.junit4.SpringJUnit4ClassRunner; import
 * org.springframework.test.context.web.WebAppConfiguration; import
 * org.springframework.test.web.servlet.MockMvc; import
 * org.springframework.test.web.servlet.setup.MockMvcBuilders; import
 * org.springframework.web.context.WebApplicationContext;
 * 
 * import com.ubilink.init.SpringConfig; import
 * com.ubilink.service.RetailerService; import
 * com.ubilink.test.context.TestContext;
 * 
 * @RunWith(SpringJUnit4ClassRunner.class)
 * 
 * @ContextConfiguration(classes={TestContext.class,SpringConfig.class})
 * 
 * @WebAppConfiguration public class RetailerControllerTest { private MockMvc
 * mockMvc;
 * 
 * @Autowired private RetailerService retailerService;
 * 
 * @Autowired private WebApplicationContext webApplicationContext;
 * 
 * @Before public void setUp() {
 * 
 * // We have to reset our mock between tests because the mock objects // are
 * managed by the Spring container. If we would not reset them, // stubbing and
 * verified behavior would "leak" from one test to another.
 * Mockito.reset(retailerService);
 * 
 * mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext) .build();
 * }
 * 
 * @Test public void getRetailerListByMall() throws Exception {
 * 
 * mockMvc.perform( get("/requestRetailer/list?placeId=347"))
 * .andExpect(status().isOk()) .andExpect(
 * content().contentType(TestUtil.APPLICATION_JSON_UTF8));
 * 
 * } }
 */
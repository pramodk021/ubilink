/*
 * package com.ubilink.controller;
 * 
 * import static
 * org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
 * import static
 * org.springframework.test.web.servlet.result.MockMvcResultMatchers.
 * forwardedUrl; import static
 * org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
 * import static
 * org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
 * 
 * import org.junit.Before; import org.junit.Test; import
 * org.junit.runner.RunWith; import
 * org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.test.context.ContextConfiguration; import
 * org.springframework.test.context.junit4.SpringJUnit4ClassRunner; import
 * org.springframework.test.context.web.WebAppConfiguration; import
 * org.springframework.test.web.servlet.MockMvc; import
 * org.springframework.test.web.servlet.setup.MockMvcBuilders; import
 * org.springframework.web.context.WebApplicationContext;
 * 
 * import com.ubilink.init.SpringConfig; import
 * com.ubilink.test.context.TestContext;
 * 
 * @RunWith(SpringJUnit4ClassRunner.class)
 * 
 * @ContextConfiguration(classes = {TestContext.class,SpringConfig.class})
 * 
 * @WebAppConfiguration public class NavigationControllerTest { private MockMvc
 * mockMvc;
 * 
 * @Autowired private WebApplicationContext webApplicationContext;
 * 
 * @Before public void setUp() { mockMvc =
 * MockMvcBuilders.webAppContextSetup(webApplicationContext).build(); }
 * 
 * @Test public void showHomePage_ShouldRenderHomePage() throws Exception {
 * mockMvc.perform(get("/")) .andExpect(status().isOk())
 * .andExpect(view().name("index"))
 * .andExpect(forwardedUrl("/WEB-INF/pages/index.jsp")); } }
 */
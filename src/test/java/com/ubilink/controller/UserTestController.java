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
 * import com.ubilink.init.SpringConfig; import com.ubilink.service.UserService;
 * import com.ubilink.test.context.TestContext;
 * 
 * @RunWith(SpringJUnit4ClassRunner.class)
 * 
 * @ContextConfiguration(classes = { TestContext.class, SpringConfig.class })
 * 
 * @WebAppConfiguration public class UserTestController {
 * 
 * private MockMvc mockMvc;
 * 
 * @Autowired private UserService userService;
 * 
 * @Autowired private WebApplicationContext webApplicationContext;
 * 
 * @Before public void setUp() {
 * 
 * // We have to reset our mock between tests because the mock objects // are
 * managed by the Spring container. If we would not reset them, // stubbing and
 * verified behavior would "leak" from one test to another.
 * Mockito.reset(userService);
 * 
 * mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
 * }
 * 
 * @Test public void validateUser_InvalidData() throws Exception {
 * 
 * mockMvc.perform(get(
 * "/requestUser/validate?mobileOREmail=amol.dadas46@gmail.com&password=amol123"
 * )) .andExpect(status().isNotFound())
 * .andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8));
 * 
 * }
 * 
 * @Test public void validateUser_ValidData() throws Exception {
 * 
 * mockMvc.perform(
 * get("/requestUser/validate?mobileOREmail=pramd.alo@aloo.com&password=qwertyu"
 * )) .andExpect(status().isOk())
 * .andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8));
 * 
 * }
 * 
 * @Test public void registerUser() throws Exception { mockMvc.perform(
 * get("/requestUser/register?email=amol.dadas46@gmail.com&mobile=9987111973&name=Amol Dadas&password=amol123"
 * )) .andExpect(status().isOk())
 * .andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8)); }
 * 
 * @Test public void verifyUser() throws Exception {
 * mockMvc.perform(get("/verify/verifyCode?mobile=9987909091&vCode=PWSJCV"))
 * .andExpect(status().isOk())
 * .andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8)); } }
 */
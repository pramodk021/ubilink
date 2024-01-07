/*
 * package com.ubilink.controller;
 * 
 * import static
 * org.springframework.test.web.servlet.request.MockMvcRequestBuilders.
 * fileUpload; import static
 * org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
 * import static
 * org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
 * import static
 * org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
 * 
 * import org.junit.Before; import org.junit.Test; import
 * org.junit.runner.RunWith; import org.mockito.Mockito; import
 * org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.http.MediaType; import
 * org.springframework.mock.web.MockHttpSession; import
 * org.springframework.test.context.ContextConfiguration; import
 * org.springframework.test.context.junit4.SpringJUnit4ClassRunner; import
 * org.springframework.test.context.web.WebAppConfiguration; import
 * org.springframework.test.web.servlet.MockMvc; import
 * org.springframework.test.web.servlet.request.
 * MockMultipartHttpServletRequestBuilder; import
 * org.springframework.test.web.servlet.request.MockMvcRequestBuilders; import
 * org.springframework.test.web.servlet.result.MockMvcResultHandlers; import
 * org.springframework.test.web.servlet.result.MockMvcResultMatchers; import
 * org.springframework.test.web.servlet.setup.MockMvcBuilders; import
 * org.springframework.web.context.WebApplicationContext;
 * 
 * import com.ubilink.init.SpringConfig; import
 * com.ubilink.service.PlaceService; import
 * com.ubilink.test.context.TestContext;
 * 
 * 
 * @RunWith(SpringJUnit4ClassRunner.class)
 * 
 * @ContextConfiguration(classes = { TestContext.class, SpringConfig.class })
 * 
 * @WebAppConfiguration public class PlaceControllerTest {
 * 
 * private MockMvc mockMvc;
 * 
 * @Autowired private PlaceService mallService;
 * 
 * @Autowired private WebApplicationContext webApplicationContext;
 * 
 * @Before public void setUp() {
 * 
 * // We have to reset our mock between tests because the mock objects // are
 * managed by the Spring container. If we would not reset them, // stubbing and
 * verified behavior would "leak" from one test to another.
 * Mockito.reset(mallService);
 * 
 * mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext) .build();
 * }
 * 
 * @Test public void createPlace_UploadFileOnly() throws Exception {
 * 
 * mockMvc.perform( post("/requestPlace/createPlace").contentType(MediaType.
 * APPLICATION_FORM_URLENCODED).accept(MediaType.APPLICATION_JSON))
 * .andExpect(status().isOk())
 * .andExpect(content().contentType(MediaType.APPLICATION_JSON));
 * 
 * String name="Korum"; String location="19.203036,72.965154"; String
 * address="Samata Nagar,  Thane West,  Thane"; String
 * description="See Korum latest news, Retailers, and their offers."; String
 * hotspotId ="367"; String placecategoryId="3"; String
 * workingTime="9 am to 10pm"; String contact="9090908877";
 * 
 * mockMvc.perform( MockMvcRequestBuilders.fileUpload("/createPlace")
 * .file("placeImg","id.png".getBytes()) .file("logoImg","pc.png".getBytes())
 * .param("name", name) .param("location", location) .param("address", address)
 * .param("description", description) .param("hotspotId", hotspotId)
 * .param("placecategoryId", placecategoryId) .param("workingTime", workingTime)
 * .param("contact", contact)) .andExpect(MockMvcResultMatchers.status().isOk())
 * .andDo(MockMvcResultHandlers.print()); }
 * 
 * @Test public void savePlace_ValidDataWithAllValidParams() throws Exception {
 * 
 * MockMultipartHttpServletRequestBuilder request =
 * fileUpload("/requestMall/createPlace");
 * request.accept(MediaType.ALL).characterEncoding("UTF-8").session(new
 * MockHttpSession());
 * 
 * String name="Korum"; String location="19.203036,72.965154"; String
 * address="Samata Nagar,  Thane West,  Thane"; String
 * description="See Korum latest news, Retailers, and their offers."; String
 * hotspotId ="609"; String placecategoryId="3"; String
 * workingTime="9 am to 10pm"; String contact="9090908877";
 * 
 * request.param("name", name); request.param("location", location);
 * request.param("address", address); request.param("description", description);
 * request.param("hotspotId", hotspotId); request.param("placecategoryId",
 * placecategoryId); request.param("workingTime", workingTime);
 * request.param("contact", contact);
 * 
 * request.file("placeImg", "the content of the file".getBytes());
 * request.file("logoImg", "the content of the file".getBytes());
 * 
 * mockMvc.perform(request).andExpect(status().isOk());
 * 
 * }
 * 
 * @Test public void savePlace_InvalidDataWithoutRequiredParams() throws
 * Exception {
 * 
 * MockMultipartHttpServletRequestBuilder request =
 * fileUpload("/requestMall/createPlace");
 * request.accept(MediaType.ALL).characterEncoding("UTF-8").session(new
 * MockHttpSession());
 * 
 * String name="Korum"; String location="19.203036,72.965154"; String
 * address="Samata Nagar,  Thane West,  Thane"; String
 * description="See Korum latest news, Retailers, and their offers."; String
 * hotspotId ="367"; String placecategoryId="3"; String
 * workingTime="9 am to 10pm"; String contact="9090908877";
 * 
 * request.param("name", name); request.param("location", location);
 * request.param("address", address); request.param("description", description);
 * request.param("hotspotId", hotspotId); request.param("placecategoryId",
 * placecategoryId); request.param("workingTime", workingTime);
 * request.param("contact", contact);
 * 
 * request.file("placeImg", "the content of the file".getBytes());
 * request.file("logoImg", "the content of the file".getBytes());
 * 
 * mockMvc.perform(request).andExpect(status().isBadRequest());
 * 
 * }
 * 
 * @Test public void savePlace_ValidDataWithRequiredFieldsOnly() throws
 * Exception { MockMultipartHttpServletRequestBuilder request =
 * fileUpload("/requestMall/createPlace");
 * request.accept(MediaType.ALL).characterEncoding("UTF-8").session(new
 * MockHttpSession());
 * 
 * String name="Korum"; String location="19.203036,72.965154"; String
 * address="Samata Nagar,  Thane West,  Thane"; String
 * description="See Korum latest news, Retailers, and their offers."; String
 * hotspotId ="609"; String placecategoryId="3"; String
 * workingTime="9 am to 10pm"; String contact="9090908877";
 * 
 * request.param("name", name); request.param("location", location);
 * request.param("address", address); request.param("description", description);
 * request.param("hotspotId", hotspotId); request.param("placecategoryId",
 * placecategoryId); request.param("workingTime", workingTime);
 * request.param("contact", contact); request.file("placeImg",
 * "the content of the file".getBytes()); request.file("logoImg",
 * "the content of the file".getBytes());
 * 
 * mockMvc.perform(request).andExpect(status().isOk());
 * 
 * }
 * 
 * @Test public void createPlace_InvalidData() throws Exception {
 * 
 * mockMvc.perform( post("/requestMall/createPlace").contentType(MediaType.
 * APPLICATION_FORM_URLENCODED).accept(MediaType.APPLICATION_JSON))
 * .andExpect(status().isOk())
 * .andExpect(content().contentType(MediaType.APPLICATION_JSON));
 * 
 * String name="Korum"; String location="19.203036,72.965154"; String
 * address="Samata Nagar,  Thane West,  Thane"; String
 * description="See Korum latest news, Retailers, and their offers."; String
 * hotspotId ="367"; String placecategoryId="3"; String
 * workingTime="9 am to 10pm"; String contact="9090908877";
 * 
 * mockMvc.perform(MockMvcRequestBuilders.fileUpload("/createPlace")
 * .file("placeImg","id.png".getBytes()) .file("logoImg","pc.png".getBytes())
 * .param("name", name) .param("location", location) .param("address", address)
 * .param("description", description) .param("hotspotId", hotspotId)
 * .param("placecategoryId", placecategoryId) .param("workingTime", workingTime)
 * .param("contact", contact))
 * .andExpect(MockMvcResultMatchers.status().isNotFound())
 * .andDo(MockMvcResultHandlers.print()); } }
 */
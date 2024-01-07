/*
 * package com.ubilink.service;
 * 
 * import static org.hamcrest.Matchers.is; import static
 * org.junit.Assert.assertThat; import static org.mockito.Mockito.mock; import
 * static org.mockito.Mockito.times; import static org.mockito.Mockito.verify;
 * import static org.mockito.Mockito.verifyNoMoreInteractions;
 * 
 * import org.junit.Before; import org.junit.Test; import
 * org.junit.runner.RunWith; import org.mockito.ArgumentCaptor; import
 * org.springframework.test.context.ContextConfiguration; import
 * org.springframework.test.context.junit4.SpringJUnit4ClassRunner; import
 * org.springframework.test.context.transaction.TransactionConfiguration; import
 * org.springframework.test.context.web.WebAppConfiguration; import
 * org.springframework.transaction.annotation.Transactional;
 * 
 * import com.ubilink.init.HibernatePersistenceContext; import
 * com.ubilink.model.User; import com.ubilink.repository.UserRepository;
 * 
 * 
 * @TransactionConfiguration(transactionManager="transactionManager",
 * defaultRollback=false)
 * 
 * @RunWith(SpringJUnit4ClassRunner.class)
 * 
 * @ContextConfiguration(classes={HibernatePersistenceContext.class})
 * 
 * @WebAppConfiguration
 * 
 * @Transactional public class UserServiceTest { private UserServiceImpl
 * userServiceImpl; private UserRepository userRepository;
 * 
 * @Before public void setUp() { userRepository=mock(UserRepository.class);
 * userServiceImpl=new UserServiceImpl(userRepository); }
 * 
 * @Test public void registerUser() { User user = new User(); user.setId(123);
 * user.setName("Test User"); user.setMobile("9987111973");
 * userServiceImpl.create(user);
 * 
 * ArgumentCaptor<User> userArgument = ArgumentCaptor.forClass(User.class);
 * verify(userRepository, times(1)).save(userArgument.capture());
 * verifyNoMoreInteractions(userRepository); User userToVerify
 * =userArgument.getValue(); assertThat(123,is(userToVerify.getId()));
 * assertThat(userToVerify.getMobile(), is(user.getMobile()));
 * assertThat(userToVerify.getName(), is(user.getName()));
 * 
 * } }
 */
/*
 * package com.ubilink.service;
 * 
 * import static org.mockito.Mockito.mock;
 * 
 * import java.util.ArrayList; import java.util.List;
 * 
 * import org.junit.Before; import org.junit.Test; import
 * org.junit.runner.RunWith; import
 * org.springframework.test.context.ContextConfiguration; import
 * org.springframework.test.context.junit4.SpringJUnit4ClassRunner; import
 * org.springframework.test.context.transaction.TransactionConfiguration; import
 * org.springframework.test.context.web.WebAppConfiguration; import
 * org.springframework.transaction.annotation.Transactional;
 * 
 * import com.ubilink.init.HibernatePersistenceContext; import
 * com.ubilink.model.Place; import com.ubilink.repository.PlaceRepository;
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
 * @Transactional public class PlaceServiceTest { private PlaceService
 * placeService; private PlaceRepository placeRepository; private List<String>
 * placeCategories=new ArrayList<String>();
 * 
 * @Before public void setUp() { placeRepository=mock(PlaceRepository.class);
 * placeService=new PlaceServiceImpl(placeRepository); }
 * 
 * @Test public void createPlace() { Place place =new Place();
 * place.setAddress("Kapurbawadi,Thane"); place.setName("Korum Place");
 * place.setLocation("19.712334,72.345455");
 * place.setPlaceCategories(placeCategories); Place parentMall=new Place();
 * parentMall.setId(609); Place createdTestMall=placeService.create(place);
 * System.out.println(createdTestMall.toString()); } }
 */


import com.clover.config.SpringConfig;


import com.clover.service.impl.PoetServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.assertNull;

public class PoetTest2 {

    private AnnotationConfigApplicationContext applicationContext;
    private PoetServiceImpl poetService;

    @BeforeEach
    public void setUp() {
        applicationContext = new AnnotationConfigApplicationContext(SpringConfig.class);
        poetService = applicationContext.getBean(PoetServiceImpl.class);
    }

    @AfterEach
    public void tearDown() {
        applicationContext.close();
    }

    @Test
    public void testPoetService() {
        poetService.findAll();
    }
    @Test
    public void testPoet2() {
        poetService.findById(5);
    }
}
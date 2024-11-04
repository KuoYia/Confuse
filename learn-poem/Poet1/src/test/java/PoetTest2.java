

import com.clover.config.SpringConfig;
import com.clover.service.PoetService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class PoetTest2 {

    private AnnotationConfigApplicationContext applicationContext;
    private PoetService poetService;

    @BeforeEach
    public void setUp() {
        applicationContext = new AnnotationConfigApplicationContext(SpringConfig.class);
        poetService = applicationContext.getBean(PoetService.class);
    }

    @AfterEach
    public void tearDown() {
        applicationContext.close();
    }

    @Test
    public void testPoetService() {
        poetService.findAll();
    }
}
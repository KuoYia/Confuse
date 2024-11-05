

import com.clover.config.SpringConfig;
import com.clover.dao.PoetDao;
import com.clover.pojo.Poet;
import java.time.LocalDate;
import java.util.List;

import com.clover.service.PoetService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

public class PoetTest {

    private AnnotationConfigApplicationContext applicationContext;
    private PoetService poetService;

    @BeforeEach
    public void setUp() {
        // 创建Spring应用上下文
        applicationContext = new AnnotationConfigApplicationContext(SpringConfig.class);
        // 从应用上下文中获取PoetService的bean
        poetService = applicationContext.getBean(PoetService.class);
    }

    @AfterEach
    public void tearDown() {
        // 关闭应用上下文
        applicationContext.close();
    }


    @Test
    public void testFindByMultipleConditions() {
        // 测试根据多个条件查询Poet对象
        PoetDao poetMapper = applicationContext.getBean(PoetDao.class);
        List<Poet> poets = poetService.findByMultipleConditions("李白", "", "");
        // 打印查询到的Poet对象信息
       /* for (Poet poet : poets) {
            System.out.println(poet);
        }*/
    }
}
import com.clover.config.SpringConfig;
import com.clover.dao.PoetDao;

import com.clover.pojo.Poet;
import java.time.LocalDate;

import com.clover.service.PoetService;


import com.clover.service.impl.PoetServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.apache.ibatis.logging.LogFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class PoetTest {

    private AnnotationConfigApplicationContext applicationContext;
    private PoetDao poetMapper;
    private PoetService poetService;

    @BeforeEach
    public void setUp() {
        // 设置MyBatis日志实现为SLF4J
        LogFactory.useSlf4jLogging();

        // 创建Spring应用上下文
        applicationContext = new AnnotationConfigApplicationContext(SpringConfig.class);
        // 从应用上下文中获取PoetMapper的bean
        poetMapper = applicationContext.getBean(PoetDao.class);
        // 从应用上下文中获取PoetService的bean
        poetService = applicationContext.getBean(PoetServiceImpl.class);
    }

    @AfterEach
    public void tearDown() {
        // 关闭应用上下文
        applicationContext.close();
    }
    @Test
    public void testInsertPoet1() {
        // 插入一个新的Poet对象
        Poet poet = new Poet();
        poet.setName("秦大恒");
        poet.setBirthDate(java.sql.Date.valueOf(LocalDate.of(2004, 1, 1)));
        poet.setDeathDate(java.sql.Date.valueOf(LocalDate.of(0000, 1, 1)));
        poet.setDynasty("新");
        poet.setBiography("21世纪伟大领袖诗人");
        //poetMapper.insert(poet);
        //poetService.insert(poet);
        //这里可以捕获异常并且添加进去
        try {
            poetService.insert(poet);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());

        }
    }


    @Test
    public void testDeletePoet2() {
        // 执行删除操作并接收返回值
        boolean deletionResult = poetService.delete(5);

        // 验证删除操作是否成功
        //assertFalse(deletionResult, "Deletion of poet with ID 5 should be successful");
        // 进一步验证诗人是否确实被删除

       Poet deletedPoet = poetService.findById(5);
        assertNull(deletedPoet, "Poet with ID 5 should be null after deletion");
    }

    @Test
    public void testFindByMultipleConditions() {
        // 测试根据多个条件查询Poet对象
        List<Poet> poets = poetMapper.findByMultipleConditions("李白", "", ""); // 调用Mapper的findByMultipleConditions方法查询符合条件的Poet对象
        for (Poet poet : poets) {
            System.out.println(poet); // 打印查询到的Poet对象信息
        }

    }
}
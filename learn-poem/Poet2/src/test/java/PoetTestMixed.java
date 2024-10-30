import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import com.clover.dao.PoetDao;
import com.clover.pojo.Poet;
import com.clover.service.PoetService;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

// 使用SpringExtension来支持Spring测试上下文
@ExtendWith(SpringExtension.class)
// 指定Spring测试上下文的配置文件位置
// 这里的路径需要根据实际项目结构调整，确保能够正确加载配置文件
@ContextConfiguration(locations = "classpath:spring-config-annotation.xml")
public class PoetTestMixed {

    @Autowired
    private PoetService poetService;

    @Autowired
    private PoetDao poetMapper;

    @Test
    public void testInsertPoet1() {
        Poet poet = new Poet();
        poet.setName("秦大恒");
        poet.setBirthDate(java.sql.Date.valueOf(LocalDate.of(2004, 1, 1)));
        poet.setDeathDate(java.sql.Date.valueOf(LocalDate.of(0000, 1, 1)));
        poet.setDynasty("新");
        poet.setBiography("21世纪伟大领袖诗人");

        try {
            poet = poetService.insert(poet);
            assertNotNull(poet.getId(), "Poet ID should not be null after insertion");
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDeletePoet2() {
        boolean deletionResult = poetService.delete(5);
        assertNull(poetService.findById(5), "Poet with ID 5 should be null after deletion");
    }

    @Test
    public void testFindByMultipleConditions() {
        // 假设“李白”是数据库中存在的诗人姓名
        Iterable<Poet> poets = poetService.findByMultipleConditions("李白", "", "");
        assertNotNull(poets);
    }
}
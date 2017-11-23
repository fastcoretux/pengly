package net.fastcoretux.web.pengly.common;

import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Ondrej.Kucera
 * @since 22.11.2017
 */
@RunWith(SpringRunner.class)
public abstract class AbstractJdbcTest extends AbstractTransactionalJUnit4SpringContextTests {

    protected <T> T queryOne(String sql, Class<T> clazz, Object... param) {
        return this.jdbcTemplate.queryForObject(sql, clazz, param);
    }
}

package net.fastcoretux.web.pengly.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;

import net.fastcoretux.web.pengly.common.AbstractJdbcTest;
import net.fastcoretux.web.pengly.domain.LinkAlias;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

/**
 * @author Ondrej.Kucera
 * @since 22.11.2017
 */
@SpringBootTest
public class LinkAliasServiceIntegTest extends AbstractJdbcTest {

    @Autowired
    private LinkAliasService service;

    @Test
    @Sql("classpath:sql/invalid-data.sql")
    public void get_invalid() {
        final Optional<LinkAlias> maxZero = service.get("max-zero");
        assertFalse(maxZero.isPresent());

        final Optional<LinkAlias> inconsistent = service.get("inconsistent");
        assertFalse(inconsistent.isPresent());
    }

    @Test
    public void get_limited() {
        final Map<String, Object> limited = jdbcTemplate.queryForMap("SELECT alias, max_count FROM pengly.link_alias WHERE max_count IS NOT NULL AND ROWNUM = 1");
        final int maxCount = ((BigDecimal)limited.get("MAX_COUNT")).intValue();
        final String alias = (String) limited.get("ALIAS");

        for(int i = 0; i < maxCount; i++) {
            final Optional<LinkAlias> result = service.get(alias);
            assertTrue(result.isPresent());
        }

        final Optional<LinkAlias> result = service.get(alias);
        assertFalse(result.isPresent());
    }
}

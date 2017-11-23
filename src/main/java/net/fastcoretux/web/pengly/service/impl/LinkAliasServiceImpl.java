package net.fastcoretux.web.pengly.service.impl;

import java.util.Optional;

import javax.transaction.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.fastcoretux.web.pengly.dao.LinkAliasDao;
import net.fastcoretux.web.pengly.domain.LinkAlias;
import net.fastcoretux.web.pengly.service.LinkAliasService;
import org.springframework.stereotype.Service;

/**
 * @author Ondrej.Kucera
 * @since 22.11.2017
 */
@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class LinkAliasServiceImpl implements LinkAliasService {

    private final LinkAliasDao dao;

    @Override
    public Optional<LinkAlias> get(final String uuid) {
        final LinkAlias alias = dao.findOne(uuid);
        if (alias == null) {
            // not found
            return Optional.empty();
        }

        alias.updateCount();
        dao.save(alias);
        if (!alias.isValid()) {
            // no longer valid
            log.warn("Alias {} is not valid", alias);
            return Optional.empty();
        }

        return Optional.of(alias);
    }

    @Override
    public String init(final LinkAlias alias) {
        alias.init();
        dao.save(alias);
        return alias.getAlias();
    }
}

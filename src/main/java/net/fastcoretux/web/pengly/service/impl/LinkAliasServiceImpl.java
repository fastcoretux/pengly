package net.fastcoretux.web.pengly.service.impl;

import java.util.Optional;

import javax.transaction.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
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
        val alias = dao.findById(uuid);
        if (!alias.isPresent()) {
            // not found
            return Optional.empty();
        }

        val foundAlias = alias.get();
        foundAlias.updateCount();
        dao.save(foundAlias);
        if (!foundAlias.isValid()) {
            // no longer valid
            log.warn("Alias {} is not valid", alias);
            return Optional.empty();
        }

        return Optional.of(foundAlias);
    }

    @Override
    public String init(final LinkAlias alias) {
        alias.init();
        dao.save(alias);
        return alias.getAlias();
    }
}

package net.fastcoretux.web.pengly.service;

import java.util.Optional;

import net.fastcoretux.web.pengly.domain.LinkAlias;

/**
 * @author Ondrej.Kucera
 * @since 22.11.2017
 */
public interface LinkAliasService {

    Optional<LinkAlias> get(String uuid);

    String init(LinkAlias alias);
}

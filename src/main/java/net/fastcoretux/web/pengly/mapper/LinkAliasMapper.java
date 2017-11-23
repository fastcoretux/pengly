package net.fastcoretux.web.pengly.mapper;

import net.fastcoretux.web.pengly.domain.LinkAlias;
import net.fastcoretux.web.pengly.rest.v1.entity.LinkAliasRE;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

/**
 * @author Ondrej.Kucera
 * @since 22.11.2017
 */
@Mapper(componentModel = "spring")
public interface LinkAliasMapper {

    LinkAlias map(final LinkAliasRE alias);

    @InheritInverseConfiguration
    LinkAliasRE map(final LinkAlias alias);
}

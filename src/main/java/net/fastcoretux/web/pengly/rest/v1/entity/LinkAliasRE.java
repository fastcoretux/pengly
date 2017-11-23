package net.fastcoretux.web.pengly.rest.v1.entity;

import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 * @author Ondrej.Kucera
 * @since 22.11.2017
 */
@Data
public class LinkAliasRE {

    @NotNull
    private String url;
    private int count;
    private Integer maxCount;
}

package net.fastcoretux.web.pengly.rest.v1;

import java.util.Optional;

import javax.validation.Valid;

import lombok.RequiredArgsConstructor;
import net.fastcoretux.web.pengly.domain.LinkAlias;
import net.fastcoretux.web.pengly.mapper.LinkAliasMapper;
import net.fastcoretux.web.pengly.rest.v1.entity.LinkAliasRE;
import net.fastcoretux.web.pengly.service.LinkAliasService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Ondrej.Kucera
 * @since 22.11.2017
 */
@RestController
@RequestMapping("/rest/link/v1")
@RequiredArgsConstructor
public class LinkAliasController {

    private final LinkAliasMapper mapper;
    private final LinkAliasService service;

    @GetMapping("{uuid}")
    ResponseEntity<LinkAliasRE> get(@PathVariable final String uuid) {
        final Optional<LinkAlias> result = service.get(uuid);
        return result.isPresent() ? ResponseEntity.ok(mapper.map(result.get())) : ResponseEntity.notFound().build();
    }

    @PostMapping
    ResponseEntity<String> init(@RequestBody @Valid final LinkAliasRE alias) {
        final String result = service.init(mapper.map(alias));
        return ResponseEntity.ok(result);
    }
}

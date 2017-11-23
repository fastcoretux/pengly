package net.fastcoretux.web.pengly.ui;

import java.util.Optional;

import lombok.RequiredArgsConstructor;
import net.fastcoretux.web.pengly.domain.LinkAlias;
import net.fastcoretux.web.pengly.service.LinkAliasService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Ondrej.Kucera
 * @since 22.11.2017
 */
@Controller
@RequestMapping("/pgly")
@RequiredArgsConstructor
public class LinkUiController {

    private final LinkAliasService service;

    @GetMapping("{uuid}")
    public String get(@PathVariable final String uuid) {
        final Optional<LinkAlias> result = service.get(uuid);
        if(result.isPresent()) {
            return "redirect:" + result.get().getUrl();
        }

        return "invalid";
    }
}

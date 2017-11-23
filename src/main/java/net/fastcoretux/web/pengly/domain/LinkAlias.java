package net.fastcoretux.web.pengly.domain;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * @author Ondrej.Kucera
 * @since 22.11.2017
 */
@Entity
@Table(name = "link_alias", schema = "pengly")
@Data
public class LinkAlias {
    @Id
    private String alias;
    private String url;
    @Column(name = "used_count")
    private int count;
    @Column(name = "max_count")
    private Integer maxCount;
    @Enumerated(EnumType.STRING)
    private Status status;

    public void init() {
        setAlias(UUID.randomUUID().toString());
        setCount(0);
        setStatus(Status.ACTIVE);
    }

    public void updateCount() {
        if(isValid() && checkCount(maxCount, count + 1)) {
            setCount(count + 1);
        } else {
            setStatus(Status.NOT_ACTIVE);
        }

        if(Status.ACTIVE.equals(status) && !isValid()) {
            setStatus(Status.NOT_ACTIVE);
        }
    }

    public boolean isValid() {
        return Status.ACTIVE.equals(status) && checkCount(maxCount, count);
    }

    // INTERNAL
    private boolean checkCount(final Integer limit, final int count) {
        return limit == null ? true : limit.intValue() >= count;
    }

    // CLASS
    public enum Status {
        ACTIVE,
        NOT_ACTIVE
    }
}

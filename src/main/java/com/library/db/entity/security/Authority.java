package com.library.db.entity.security;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
@Embeddable
@Table(name = "authorities")
public class Authority {

    @Length(min = 1, max = 50)
    private String authority;

}

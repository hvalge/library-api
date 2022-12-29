package com.library.db.entity.security;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @Length(min = 1, max = 50)
    private String username;

    @Length(min = 1, max = 255)
    @NotNull
    private String password;

    private boolean enabled;

    @Valid
    @ElementCollection
    @CollectionTable(
            name = "authorities",
            joinColumns=@JoinColumn(name = "username",
                    referencedColumnName = "username")
    )
    private List<Authority> authorities;
}

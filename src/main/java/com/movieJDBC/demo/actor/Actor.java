package com.movieJDBC.demo.actor;

import lombok.*;
import org.springframework.data.relational.core.mapping.Table;


@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Actor {

    Integer id;
    String name;
}

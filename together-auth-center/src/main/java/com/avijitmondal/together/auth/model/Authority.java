package com.avijitmondal.together.auth.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
@Table(name = "authority")
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, updatable = false)
    @ApiModelProperty(notes = "The database generated user, token and session mapping ID.")
    private Long id;

    @NotNull
    @Size(min = 0, max = 50)
    private String name;

}

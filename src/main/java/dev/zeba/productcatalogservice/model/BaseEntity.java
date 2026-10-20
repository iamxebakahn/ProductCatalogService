package dev.zeba.productcatalogservice.model;

import dev.zeba.productcatalogservice.model.enums.State;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public abstract class BaseEntity { //Abstract so its objects can't be instantiated standalone, and made purely for code reuse purpose
    private Long id;
    private Date createdAt;
    private Date lastUpdatedAt;
    private State state; //ACTIVE, INACTIVE : for soft deletes and we dont do hard deletes,from db just mark a row as inactive
    //instead of permanently removing the row from db
}

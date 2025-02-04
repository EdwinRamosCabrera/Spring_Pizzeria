package com.platzi.pizzeria_presto.persistencia.audit;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
@MappedSuperclass
public class Auditable {

    @Column(name = "fecha_creacion")
    @CreatedDate
    @JsonIgnore
    private LocalDateTime createdDate;

    @Column(name = "fecha_modificacion")
    @LastModifiedDate
    @JsonIgnore
    private LocalDateTime modifiedDate;
}

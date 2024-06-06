package com.school.app.siswa_service.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.*;

@Data
@Getter
@Setter
@MappedSuperclass
public class BaseEntity {

    @Id
    private String id;

    @Column(name = "deleted")
    private Integer deleted;
}

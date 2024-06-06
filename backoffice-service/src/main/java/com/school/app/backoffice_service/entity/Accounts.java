package com.school.app.backoffice_service.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_accounts")
public class Accounts extends BaseEntity{

    @Column(name = "fullname", nullable = false)
    private String fullname;

    @Column(name = "roles_id", nullable = false)
    private String rolesId;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "dob", nullable = false)
    private LocalDateTime dob;

}

package com.school.app.siswa_service.entity;


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
@Table(name = "tbl_student")
public class Student extends BaseEntity{

    @Column(name = "fullname", nullable = false, length = 255)
    private String fullname;

    @Column(name = "gender", nullable = false, length = 10)
    private String gender;

    @Column(name = "dob", nullable = false)
    private LocalDateTime dob;

    @Column(name = "email", nullable = false, unique = true, length = 100)
    private String email;

    @Column(name = "phone_no", nullable = false, unique = true, length = 20)
    private String phoneNo;

    @Column(name = "address", nullable = false, length = 100)
    private String address;

    @Column(name = "avatar", columnDefinition = "TEXT")
    private String avatar;
}

package com.school.app.siswa_service.repository;

import com.school.app.siswa_service.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student,String>, JpaSpecificationExecutor<Student> {
}

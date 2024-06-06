package com.school.app.backoffice_service.repository;

import com.school.app.backoffice_service.entity.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountsRepository extends JpaRepository<Accounts,String>, JpaSpecificationExecutor<Accounts> {

    @Query(value = "select * from tbl_accounts where email=:email",nativeQuery = true)
    Optional<Accounts> findByEmail(@Param("email") String email);
}

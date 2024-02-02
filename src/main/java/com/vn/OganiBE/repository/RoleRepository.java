package com.vn.OganiBE.repository;


import com.vn.OganiBE.entity.ERole;
import com.vn.OganiBE.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> findByName(ERole name);
}

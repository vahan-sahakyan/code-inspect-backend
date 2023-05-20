package io.github.vahansahakyan.CodeInspect.repository;

import io.github.vahansahakyan.CodeInspect.domain.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority,Long> {
}

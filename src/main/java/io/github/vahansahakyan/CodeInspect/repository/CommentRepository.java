package io.github.vahansahakyan.CodeInspect.repository;

import io.github.vahansahakyan.CodeInspect.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}

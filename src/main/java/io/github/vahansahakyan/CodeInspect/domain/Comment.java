package io.github.vahansahakyan.CodeInspect.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "comments")
public class Comment {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @JsonIgnore
  @ManyToOne
  private Assignment assignment;
  @ManyToOne
  @JoinColumn(name = "user_id")
  private User createdBy;
  private LocalDateTime createdDate;
  @Column(columnDefinition = "TEXT")
  private String text;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public LocalDateTime getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(LocalDateTime createdDate) {
    this.createdDate = createdDate;
  }

  public User getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(User createdBy) {
    this.createdBy = createdBy;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public Assignment getAssignment() {
    return assignment;
  }

  public void setAssignment(Assignment assignment) {
    this.assignment = assignment;
  }

  @Override
  public String toString() {
    return "COMMENT {" +
        "id=" + id +
        ", assignment=" + assignment +
        ", createdBy=" + createdBy +
        ", createdDate=" + createdDate +
        ", text='" + text + '\'' +
        '}';
  }
}

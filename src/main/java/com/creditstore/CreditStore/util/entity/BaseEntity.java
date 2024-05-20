package com.creditstore.CreditStore.util.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import java.util.Date;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

  @CreatedBy
  @Column(nullable = false, updatable = false)
  protected UUID createdBy;

  @CreatedDate
  @Column(nullable = false, updatable = false)
  private Date dateHourCreation;

  @Column(nullable = false, updatable = false)
  private int status;

}
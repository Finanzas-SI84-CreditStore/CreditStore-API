package com.creditstore.CreditStore.util.entity;

import java.util.Date;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;

@Getter
@Setter
public abstract class BaseEntity {

  @CreatedBy
  protected UUID createdBy;

  @CreatedDate
  private Date dateHourCreation;

  private int status;

}

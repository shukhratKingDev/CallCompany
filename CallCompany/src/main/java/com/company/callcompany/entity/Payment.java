package com.company.callcompany.entity;

import com.company.callcompany.entity.templates.CommonUniqueFields;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Payment  extends CommonUniqueFields {
    private String phoneNumber;
    private double sum;
}

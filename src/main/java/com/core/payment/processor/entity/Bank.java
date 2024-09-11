package com.core.payment.processor.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import java.io.Serializable;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "bank", indexes = {
        @Index(name = "ix_bank_name_uq", columnList = "name", unique = true),
        @Index(name = "ix_bank_code_uq", columnList = "code", unique = true)
})
public class Bank extends AbstractBaseEntity implements Serializable {
    private String name;
    private String code;
    private String iconUrl;
}

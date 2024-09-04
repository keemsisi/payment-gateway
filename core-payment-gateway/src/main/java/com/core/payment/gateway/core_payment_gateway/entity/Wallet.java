package com.core.payment.gateway.core_payment_gateway.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "wallet")
public class Wallet extends AbstractBaseEntity implements Serializable {
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "varchar(100) default 'PRIMARY'")
    private WalletType walletType;
    private String accountName;
    private String reference;

    private enum WalletType {
        PRIMARY, SECONDARY
    }
}
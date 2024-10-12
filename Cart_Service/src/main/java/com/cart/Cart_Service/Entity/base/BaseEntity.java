package com.cart.Cart_Service.Entity.base;

import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@MappedSuperclass
public abstract class BaseEntity {

    private boolean deleted;
    private boolean active;

    protected BaseEntity() {
        this.setActive(true);
        this.setDeleted(false);
    }
}

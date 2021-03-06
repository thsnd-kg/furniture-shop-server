package com.furnitureshop.product.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class ValuePK implements Serializable {
    @Column(name = "variant_id")
    private Long variantId;

    @Column(name = "option_id")
    private Long optionId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ValuePK that = (ValuePK) o;
        return variantId.equals(that.variantId) && optionId.equals(that.optionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(variantId, optionId);
    }
}
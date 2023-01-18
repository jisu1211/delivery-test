package delivery.product.domain;

import delivery.common.BaseEntity;
import delivery.product.exception.ProductExceptionCode;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
public class Product extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Embedded
    private ProductPrice price;

    protected Product() {}

    public Product(String name, BigDecimal price) {
        validate(name);

        this.name = name;
        this.price = new ProductPrice(price);
    }

    private void validate(String name) {
        if (!StringUtils.hasText(name)) {
            throw new IllegalArgumentException(ProductExceptionCode.REQUIRED_NAME.getMessage());
        }
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return this.price.getPrice();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Product product = (Product) o;
        return Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}

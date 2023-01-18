package delivery.product.domain;

import delivery.product.domain.Product;
import delivery.product.exception.ProductExceptionCode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("상품 클래스 테스트")
class ProductTest {
    @DisplayName("동등성 테스트")
    @Test
    void equals() {
        assertEquals(new Product("연필", new BigDecimal(1000)),
                new Product("연필", new BigDecimal(1000)));
    }

    @DisplayName("이름이 null이거나 빈 문자열인 상품을 생성할 수 없다.")
    @ParameterizedTest
    @NullAndEmptySource
    void newProduct(String name) {
        assertThatThrownBy(() -> {
            new Product(name, new BigDecimal(18000));
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ProductExceptionCode.REQUIRED_NAME.getMessage());
    }
}

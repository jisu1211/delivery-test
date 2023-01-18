package delivery.product.domain;

import delivery.product.exception.ProductExceptionCode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("상품 가격 클래스 테스트")
class ProductPriceTest {
    @DisplayName("상품 가격은 null 일 수 없다.")
    @Test
    void newProduct() {
        assertThatThrownBy(() -> {
            new ProductPrice(null);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ProductExceptionCode.REQUIRED_PRICE.getMessage());
    }

    @DisplayName("상품 가격은 0보다 작을 수 없다.")
    @ParameterizedTest
    @ValueSource(ints = { -1, -100, -500, -1000 })
    void newProduct2(int price) {
        assertThatThrownBy(() -> {
            new ProductPrice(new BigDecimal(price));
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ProductExceptionCode.INVALID_PRICE.getMessage());
    }

}

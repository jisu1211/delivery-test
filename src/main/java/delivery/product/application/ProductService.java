package delivery.product.application;

import delivery.product.domain.Product;
import delivery.product.domain.ProductRepository;
import delivery.product.dto.ProductRequest;
import delivery.product.dto.ProductResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    public ProductResponse create(ProductRequest request) {
        return ProductResponse.of(productRepository.save(new Product(request.getName(), request.getPrice())));
    }

}

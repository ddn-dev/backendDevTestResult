package com.ddn.backenddevtest.web.controller.product;

import com.ddn.backenddevtest.web.dto.ProductDTO;
import com.ddn.backenddevtest.web.exception.NotFoundException;
import com.ddn.backenddevtest.web.exception.ServerErrorException;
import com.ddn.backenddevtest.web.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Entry points for the management of products.
 *
 * @author Daniel Diaz Navas
 */
@RestController
@RequestMapping("product")
@RequiredArgsConstructor
public class ProductController implements ProductApi {

  private final ProductService productService;

  /** {@inheritDoc} */
  @Override
  public ResponseEntity<List<ProductDTO>> getSimilarProductsById(Long id) {
    List<ProductDTO> productDTOS;
    try {
      productDTOS = productService.getSimilarProductsById(id);
    } catch (NotFoundException nfe) {
      return ResponseEntity.notFound().build();
    } catch (ServerErrorException nfe) {
      return ResponseEntity.internalServerError().build();
    }
    if (ObjectUtils.isEmpty(productDTOS)) {
      return ResponseEntity.noContent().build();
    }
    return ResponseEntity.ok(productDTOS);
  }
}

package com.rashi.webfluxproject;

import com.rashi.webfluxproject.controller.ProductController;
import com.rashi.webfluxproject.dto.ProductDTO;
import com.rashi.webfluxproject.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebFluxTest(ProductController.class)
class WebFluxProjectIiiApplicationTests {

   @Autowired
   private WebTestClient webTestClient;

    @MockBean
    private ProductService productService;
@Test
  public void addProductTest(){
       Mono<ProductDTO> productDTOMono= Mono.just(new ProductDTO("102","mobile",1,100000.0));
       when(productService.saveProduct(productDTOMono)).thenReturn(productDTOMono);

        webTestClient.post()
                .uri("/products")
                .body(Mono.just(productDTOMono),ProductDTO.class)
                .exchange()
                .expectStatus().isOk();



  }

  @Test
    public void getProductsTest(){
      Flux<ProductDTO> productDTOFlux= Flux.just(new ProductDTO("102","mobile",1,100000.0),
      new ProductDTO("103","TV",1,1000.0));
      when(productService.getProducts()).thenReturn(productDTOFlux);

      Flux<ProductDTO> responseBody = webTestClient.get().uri("/products")
              .exchange()
              .returnResult(ProductDTO.class)
              .getResponseBody();

      StepVerifier.create(responseBody)
              .expectSubscription()
              .expectNext(new ProductDTO("102","mobile",1,100000.0))
              .expectNext(new ProductDTO("103","TV",1,1000.0))
              .verifyComplete();
  }
    @Test
  public void getProductTest(){
    Mono<ProductDTO> productDTOMono=Mono.just(new ProductDTO("102","mobile",1,100000.0));
    when(productService.getProduct(any())).thenReturn(productDTOMono);

      Flux<ProductDTO> responseBody = webTestClient.get()
              .uri("/products/102")
              .exchange()
              .returnResult(ProductDTO.class)
              .getResponseBody();

      StepVerifier.create(responseBody)
              .expectSubscription()
              .expectNextMatches(p ->p.getName().equals("mobile"))
              .verifyComplete();
  }

  @Test
  public void updateProductTest(){
    Mono<ProductDTO> productDTOMono=Mono.just(new ProductDTO("102","mobile",1,100000.0));
    when(productService.updateProduct(productDTOMono,"102")).thenReturn(productDTOMono);

      webTestClient.put()
              .uri("/products/update/102")
              .body(Mono.just(productDTOMono),ProductDTO.class)
              .exchange()
              .expectStatus().isOk();
  }
    @Test
  public void deleteProductTest(){
    given(productService.deleteProduct(any())).willReturn(Mono.empty());


    webTestClient.delete()
            .uri("/products/delete/102")
            .exchange()
            .expectStatus().isOk();
  }


}

package dev.zeba.productcatalogservice.controller;

import dev.zeba.productcatalogservice.dto.CategoryDto;
import dev.zeba.productcatalogservice.dto.ProductDto;
import dev.zeba.productcatalogservice.dto.ProductPitchDto;
import dev.zeba.productcatalogservice.model.Product;
import dev.zeba.productcatalogservice.service.IProductService;
import dev.zeba.productcatalogservice.service.PitchService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //it has @Component inside it, why restcontroller n why not component? : coz we need Spring to do other stuff too
//mapping handler stuff too
@RequestMapping("/products")
public class ProductController {

    // @Autowired //Field injection: not being used here, not autowired constructor injection being used here
    private final IProductService storageService; //Field injection: directly autowiring the field not used in production though, objects added via constructor
    //private DummyProductService dummyProductService; //Dependency Inversion violated - 1 concrete dependent on other concrete
    //to write APIs (function +endpoint)
    //@Component for Spring IOC to create object directly/indirectly : Beans = objects created by Spring IOC, if created by us
    // they are called as Java Objects (Bean is Java object managed by Spring IOC and not programmer)
    //@Autowired to access object


    public ProductController(IProductService storageService, PitchService pitchService, @Qualifier("dummyProductService") IProductService supplierService) {
        this.storageService = storageService;
        this.pitchService = pitchService;
        this.supplierService = supplierService;
    }

    private final PitchService pitchService;
    //API to get all products
    //1. make normal java function
    private final IProductService supplierService;
    @GetMapping //attach end point or mapping to it so that users can call it
    List<Product> getAllProducts(){
        //call service layer, repo layer
        return List.of();
    }

    //Path variable - when i/p is in link
    //Ctrler or client layer never directly get/not exposed to entities : due to security issues(hackers can spoof it)
    //if send entire entity: makes APIs slow, send DTO instead(POJO to transfer data, not stored anywhere)
    //if entire object is user taken in i/p and saved in db: ; only business fields are taken and other attributes are based
    //on business logic, clients dont deal with real entities: we make DTOs, hence return Product DTO n't product entity
    //Get rid of /products and use RequestMapping
    @GetMapping("/{id}")
    ResponseEntity<ProductDto> getProductById(@PathVariable("id") Long id){ //PathVariable maps path variable to actual java variable
        if(id<0)
            return new ResponseEntity<>((HttpHeaders) null,HttpStatus.BAD_REQUEST);

        Product product = storageService.getProductById(id);
        //Product product = supplierService.getProductById(id);

        if(product ==null)
            return new ResponseEntity<>((HttpHeaders) null,HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(from(product), HttpStatus.OK);
    }

    //http response has 3 parts: headers, body/content, status code
    //ResponseEntity : for response body + status code (for web API, response body is default reponse type)

    private  ProductDto from(Product product){
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setDescription(product.getDescription());
        productDto.setPrice(product.getPrice());
        productDto.setImageUrl(product.getImageUrl());

        if(product.getCategory()!=null) {
            CategoryDto categoryDto = new CategoryDto();
            categoryDto.setDescription(product.getCategory().getDescription());
            categoryDto.setId(product.getCategory().getId());
            categoryDto.setName(product.getCategory().getName());
            productDto.setCategoryDto(categoryDto);
        }
        return productDto;
    }

    @GetMapping("/{id}/pitch")
    public ResponseEntity<ProductPitchDto> generatePitch(@PathVariable Long id){
        if(id<0)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        Product product = storageService.getProductById(id);
        if(product == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        ProductPitchDto productPitchDto = new ProductPitchDto();
        productPitchDto.setId(product.getId());
        productPitchDto.setPitch(pitchService.generatePitch(product));
        return new ResponseEntity<>(productPitchDto,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto){
        Product product = storageService.createProduct(productDto);
        return new ResponseEntity<>(from(product),HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id){

            boolean isDeleted = storageService.deleteProdcutById(id);
            if(isDeleted)
                return new ResponseEntity<>(HttpStatus.NO_CONTENT); //204: i dont have content to return but your request was successful
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> replaceProduct(@PathVariable("id") Long id, @RequestBody ProductDto productDto){
        if(id<0){
            return ResponseEntity.badRequest().build();
        }
        Product product = storageService.replaceProduct(id,productDto);
        if(product == null){
            return ResponseEntity.notFound().build();
        }

        return new ResponseEntity<>(from(product),HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProductDto> patchProduct(@PathVariable("id") Long id, @RequestBody ProductDto productDto){
        if(id<0){
            return ResponseEntity.badRequest().build();
        }
        Product product = storageService.patchProduct(id, productDto);
        if(product == null){
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(from(product),HttpStatus.OK);
    }
}

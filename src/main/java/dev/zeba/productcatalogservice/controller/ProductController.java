package dev.zeba.productcatalogservice.controller;

import dev.zeba.productcatalogservice.model.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController //it has @Component inside it, why restcontroller n why not component? : coz we need Spring to do other stuff too
//mapping handler stuff too
public class ProductController {
    //to write APIs (function +endpoint)
    //@Component for Spring IOC to create object directly/indirectly : Beans = objects created by Spring IOC, if created by us
    // they are called as Java Objects (Bean is Java object managed by Spring IOC and not programmer)
    //@Autowired to access object

    //API to get all products
    //1. make normal java function
    @GetMapping("/products") //attach end point or mapping to it so that users can call it
    List<Product> getAllProducts(){
        //call service layer, repo layer
        return List.of();
    }
}

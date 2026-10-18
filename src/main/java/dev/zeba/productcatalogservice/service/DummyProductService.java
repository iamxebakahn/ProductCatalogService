package dev.zeba.productcatalogservice.service;

import dev.zeba.productcatalogservice.model.Product;
import org.springframework.boot.restclient.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DummyProductService implements IProductService {
    //For 3rd party API calls via spring: Rest Template, Rest Client, Feign Client (we use Rest Template)
    //Study Feign Client

    private RestTemplate restTemplate;

    //Java expects RestTemplate object to be created with the builder of RestTemplate
    //builder is not present here though: so we add rest client
    //To get resttamplatebuilder to create resttamplate object, maven repository has dependency code for starter restclient
    //added dependency code in .pom (coz m using SB4 and it has separate dependency for restclient)
    public DummyProductService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Product getProductById(Long id) {
        return null;
    }
}

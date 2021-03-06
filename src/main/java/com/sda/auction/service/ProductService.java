package com.sda.auction.service;

import com.sda.auction.dto.ProductDto;
import com.sda.auction.mapper.ProductMapper;
import com.sda.auction.model.Product;
import com.sda.auction.model.User;
import com.sda.auction.repository.ProductRepository;
import com.sda.auction.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    // == fields
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final UserRepository userRepository;

    // == constructor
    @Autowired
    public ProductService(ProductRepository productRepository, ProductMapper productMapper, UserRepository userRepository){
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.userRepository = userRepository;
    }

    public void addProduct(ProductDto productDto, String loggedUserEmail, MultipartFile multipartFile) {
        Product product = productMapper.map(productDto, multipartFile);
        assignSeller(loggedUserEmail, product);
        productRepository.save(product);

    }

    public Date getParse(String endBiddingTime) throws ParseException {
        return new SimpleDateFormat("dd-MM-yyyy hh:mm").parse(endBiddingTime);
    }

    public List<ProductDto> getActiveProductDtoList(String authenticatedUserEmail) {
        List<Product> productList = productRepository.findAllActive(LocalDateTime.now());
        return productMapper.map(productList, authenticatedUserEmail);
    }

    public List<ProductDto> getProductDtoListByBidder(String authenticatedUserEmail) {
        List<Product> productList = productRepository.findAllByBidder(authenticatedUserEmail);
        return productMapper.map(productList, authenticatedUserEmail);
    }

    private void assignSeller(String loggedUserEmail, Product product) {
        Optional<User> optionalUser = userRepository.findByEmail(loggedUserEmail);

        if (optionalUser.isPresent()){
            User user = optionalUser.get();   // we return a user from a container
            product.setSeller(user);
        }
    }

    public Optional<ProductDto> getProductDtoById(String productId, String authenticatedUserEmail) {
        Optional<Product> optionalProduct = productRepository.findById(Integer.parseInt(productId));
        if(!optionalProduct.isPresent()) {
            return Optional.empty();
        }
        ProductDto productDto = productMapper.map(optionalProduct.get(), authenticatedUserEmail);
        return Optional.of(productDto);
    }
}

package service;

import model.Product;

import java.util.List;

public interface IProductService {
    void addNewProduct(Product p);

    List<Product> searchProduct(String name);

    boolean updateProduct(Product p);

    boolean deleteProduct(int id);

    List<Product> showAllProducts();
}

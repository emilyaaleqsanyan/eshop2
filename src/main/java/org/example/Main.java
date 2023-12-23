package org.example;

import org.example.manager.ProductManager;
import org.example.model.Category;
import org.example.model.Commands;
import org.example.model.Product;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main implements Commands {

    private final static Scanner SCANNER = new Scanner(System.in);
    private static ProductManager productManager = new ProductManager();

    public static void main(String[] args) {

        boolean isRun = true;
        while (isRun) {
            Commands.printMainCommands();
            String command = SCANNER.nextLine();
            switch (command) {
                case EXIT:
                    isRun = false;
                    break;
                case ADD_CATEGORY:
                    addCategory();
                    break;
                case EDIT_CATEGORY_BY_ID:
                    editCategoryById();
                    break;
                case DELETE_CATEGORY_BY_ID:
                    deleteCategory();
                    break;
                case ADD_PRODUCT:
                    addProduct();
                    break;
                case EDIT_PRODUCT_BY_ID:
                    editProductById();
                    break;
                case DELETE_PRODUCT_BY_ID:
                    deleteProduct();
                    break;
                case PRINT_SUM_OF_PRODUCTS:
                    productManager.printSumOfProduct();
                    break;
                case PRINT_MAX_OF_PRICE_PRODUCT:
                    productManager.printMaxOfPrice();
                    break;
                case PRINT_MIN_OF_PRICE_PRODUCT:
                    productManager.printMinOfPrice();
                    break;
                case PRINT_AVG_OF_PRICE_PRODUCT:
                    productManager.printAvgOfPrice();
                    break;
                default:
                    System.out.println("Unknown Command!");
            }
        }
    }

    private static void editProductById() {
        System.out.println("Please input product id, name,description,price, quantity");
        String productDataStr = SCANNER.nextLine();
        String[] productDataArr = productDataStr.split(",");
        Product product = new Product();
        try {
            product.setId(Integer.parseInt(productDataArr[0]));
            product.setName(productDataArr[1]);
            product.setDescription(productDataArr[2]);
            product.setPrice(Double.parseDouble(productDataArr[3]));
            product.setQuantity(Integer.parseInt(productDataArr[4]));
//
        } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        }

        productManager.updateProduct(product);


    }


    private static void editCategoryById() {
        System.out.println("Please input category id ");
        int categoryId = Integer.parseInt(SCANNER.nextLine());
        System.out.println("Please input category name");
        String categoryNameEdit = SCANNER.nextLine();
        Category category = new Category(categoryId, categoryNameEdit);
        productManager.updateCategory(category);
    }

    private static void deleteProduct() {
        System.out.println("Please input product id");
        int productId = Integer.parseInt(SCANNER.nextLine());
        productManager.deleteProductById(productId);
    }

    private static void deleteCategory() {
        System.out.println("Please choose category id");
        List<Category> allCategory = productManager.getAllCategory();
        for (Category category : allCategory) {
            System.out.println(category);
        }
        int categoryId = Integer.parseInt(SCANNER.nextLine());
        productManager.deleteCategoryById(categoryId);
    }

    private static void addProduct() {
        System.out.println("Please input product name, description,price,quantity");
        String productDataStr = SCANNER.nextLine();
        String[] productDataArr = productDataStr.split(",");
        try {
            Product product = new Product();
            product.setName(productDataArr[0]);
            product.setDescription(productDataArr[1]);
            product.setPrice(Double.parseDouble(productDataArr[2]));
            product.setQuantity(Integer.parseInt(productDataArr[3]));
            System.out.println("Please choose category id");
            List<Category> allCategory = productManager.getAllCategory();
            for (Category category : allCategory) {
                System.out.println(category);
            }
            int categoryId = Integer.parseInt(SCANNER.nextLine());
            Category categoryFromTable = productManager.getCategoryById(categoryId);
            product.setCategory(categoryFromTable);
            productManager.addProduct(product);
            System.out.println("Product added!");
        } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException e) {
            System.out.println("Invalid data: " + e.getMessage());
        }
    }


    private static void addCategory() {
        System.out.println("Please input category name");
        String categoryName = SCANNER.nextLine();
        Category category = new Category();
        category.setName(categoryName);
        productManager.addCategory(category);

    }


}

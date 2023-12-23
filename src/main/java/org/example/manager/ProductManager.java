package org.example.manager;

import org.example.db.DBConnectionProvider;
import org.example.model.Category;
import org.example.model.Product;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductManager {
    private Connection connection = DBConnectionProvider.getInstance().getConnection();


    public void addCategory(Category category) {
        String query = "INSERT INTO category(name)VALUES (?)";
        try (PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, category.getName());

            ps.executeUpdate();
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                int id = generatedKeys.getInt(1);
                category.setId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addProduct(Product product) {
        String query = "INSERT INTO product(name,description,price,quantity,category)VALUES(?,?,?,?,?)";
        try (PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, product.getName());
            ps.setString(2, product.getDescription());
            ps.setDouble(3, product.getPrice());
            ps.setInt(4, product.getQuantity());
            ps.setInt(5, product.getCategory().getId());

            ps.executeUpdate();
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                int id = generatedKeys.getInt(1);
                product.setId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<Category> getAllCategory() {

        String sql = "SELECT* FROM category";
        List<Category> result = new ArrayList<>();

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                Category category = new Category(id, name);
                result.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }


    public Category getCategoryById(int categoryId) {

        String sql = "SELECT*FROM category WHERE id = " + categoryId;
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                return new Category(id, name);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    public void deleteCategoryById(int categoryId) {
        if (getCategoryById(categoryId) == null) {
            System.out.println("Category with " + categoryId + " dose not exists");
            return;
        }
        String sql = "DELETE FROM category WHERE id = " + categoryId;
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
            System.out.println("category was removed!");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void deleteProductById(int productId) {
        if (getProductById(productId) == null) {
            System.out.println("Product with " + productId + " does not exists");
            return;
        }
        String sql = "DELETE FROM product WHERE id = " + productId;
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
            System.out.println("Product was removed! ");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public Product getProductById(int productId) {

        String sql = "SELECT*FROM product WHERE id = " + productId;
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                Double price = resultSet.getDouble("price");
                int quantity = resultSet.getInt("quantity");
                return new Product(productId,name, description, price, quantity);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    public void updateCategory(Category category) {
        if (getCategoryById(category.getId()) == null) {
            System.out.println("Category with " + category.getId() + " does not exists");
            return;
        }
        String query = "UPDATE category SET name = '%s' WHERE id = " + category.getId();
        String sql = String.format(query, category.getName());
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
            System.out.println("Category updated!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateProduct(Product product) {
        if ((getProductById(product.getId()) == null )) {
            System.out.println("Product with " + product.getId() + " does not exists");
            return;
        }
        String query = "UPDATE product SET name = '%s', description = '%s', price = %.2f, quantity = %d  WHERE id = %d";
        String sql = String.format(query, product.getName(), product.getDescription(), product.getPrice(), product.getQuantity(), product.getId());
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
            System.out.println("Product updated!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void printSumOfProduct() {
        String sql = "SELECT SUM(quantity) FROM product";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet resultSet = ps.executeQuery();
            if(resultSet.next()){
                String sum = resultSet.getString("Sum(quantity)");
                System.out.println(sum);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void printMaxOfPrice() {
        String sql = "SELECT MAX(price) FROM product";
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ResultSet resultSet = ps.executeQuery();
            if(resultSet.next()){
                String max = resultSet.getString("Max(price)");
                System.out.println(max);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void printMinOfPrice() {
        String sql = "SELECT MIN(price) FROM product";
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ResultSet resultSet = ps.executeQuery();
            if(resultSet.next()){
                String min = resultSet.getString("Min(price)");
                System.out.println(min);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void printAvgOfPrice() {
        String sql = "SELECT AVG(price) FROM product";
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ResultSet resultSet = ps.executeQuery();
            if(resultSet.next()){
                String avg = resultSet.getString("Avg(price)");
                System.out.println(avg);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}

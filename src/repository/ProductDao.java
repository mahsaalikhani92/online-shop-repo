package repository;

import model.Order;
import model.Product;
import model.enumeration.ProductCategory;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Mahsa Alikhani m-58
 */
public class ProductDao {
    private final Connection connection;

    public ProductDao() throws SQLException, ClassNotFoundException {
        this.connection = BaseDao.getConnection();
    }

    public List<Product> findAllProducts() throws SQLException {
        List<Product> products = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from products");
        while (resultSet.next()) {
            Product product = new Product();
            product.setId(resultSet.getInt("id"));
            product.setName(resultSet.getString("name"));
            product.setPrice(resultSet.getInt("price"));
            product.setStock(resultSet.getInt("stock"));
            product.setCategory(ProductCategory.valueOf(resultSet.getString("category")));
            products.add(product);
        }
        return products;
    }

    public Integer findItemPriceById(int id) throws Exception {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select price from products" +
                " where id = '"+id+"'");
        while (resultSet.next()) {
            if (resultSet == null) {
                throw new Exception("Item's price is not set!");
            }
            int price = resultSet.getInt("price");
            return price;
        }
        return null;
    }

    public int findStockByProductId(int id) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select stock from products" +
                " where id = '"+id+"'");
        while (resultSet.next()) {
            int stock = resultSet.getInt("stock");
            return stock;
        }
        return 0;
    }

    public int findProductIdByOrderId(int orderId) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select product_id from orders where id = '"+orderId+"'");
        while (resultSet.next()){
            return resultSet.getInt("product_id");
        }
        return 0;
    }

    public void updateProductStock(int id, int newStock) throws SQLException {
        Statement statement = connection.createStatement();
        String sqlQuery = "update products set stock = '" + newStock + "'" +
                " where id = '" + id + "'";
        statement.executeUpdate(sqlQuery);
    }
}

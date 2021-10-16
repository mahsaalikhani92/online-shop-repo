package repository;

import model.Electronic;
import model.enumeration.ElectronicTypes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Mahsa Alikhani m-58
 */
public class ElectronicDao extends BaseDao{
    private final Connection connection;

    public ElectronicDao() throws SQLException, ClassNotFoundException {
        this.connection = BaseDao.getConnection();
    }

    public Electronic findItem () throws SQLException {
        PreparedStatement statement = connection.prepareStatement("select * from products");
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()){
            Electronic electronic = new Electronic();
            electronic.setId(resultSet.getInt("id"));
            electronic.setName(resultSet.getString("name"));
            electronic.setPrice(resultSet.getInt("price"));
            electronic.setStock(resultSet.getInt("stock"));
            electronic.setElectronicTypes(ElectronicTypes.valueOf(resultSet.getString("category")));
        }
    }

    public List<Electronic> saveNewItem(int id) throws SQLException {
        String sqlQuery = "insert into order_details (product_id, quantity, price)" +
                "values(?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sqlQuery);

    }
}
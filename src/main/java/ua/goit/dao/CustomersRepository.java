package ua.goit.dao;

import ua.goit.config.DatabaseConnectionManager;
import ua.goit.dao.model.CustomersDAO;
import ua.goit.service.customers.CustomersConverter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomersRepository implements Repository<CustomersDAO> {

    private final DatabaseConnectionManager connectionManager;

    private static final String INSERT = "INSERT INTO customers (customer_id, customer_name" +
            "VALUES (default, ?);";
    private static final String SELECT_CUSTOMERS_BY_ID = "SELECT customer_id, customer_name" +
            "FROM customers WHERE customer_id = ?;";
    private static final String UPDATE = "UPDATE customers SET customer_name=?" +
            "WHERE customer_id=?;";
    private static final String DELETE = "DELETE FROM customers WHERE customer_id=?;";

    private static final String SELECT_CUSTOMERS_BY_NAME = "SELECT customer_id, customer_name" +
            "FROM customers WHERE customer_name = ?;";

    public CustomersRepository(DatabaseConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    @Override
    public CustomersDAO findById(long id) {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CUSTOMERS_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return CustomersConverter.toCustomer(resultSet);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    @Override
    public CustomersDAO findByUniqueValue(String customerName) {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CUSTOMERS_BY_NAME)) {
            preparedStatement.setString(1, customerName);
            ResultSet resultSet = preparedStatement.executeQuery();
            return CustomersConverter.toCustomer(resultSet);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    @Override
    public void create(CustomersDAO customersDAO) {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT)) {
            preparedStatement.setString(1, customersDAO.getCustomerName());
            preparedStatement.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void update(CustomersDAO customersDAO) {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
            preparedStatement.setString(1, customersDAO.getCustomerName());
            preparedStatement.setLong(3, customersDAO.getCustomerId());
            preparedStatement.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void delete(String name) {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {
            preparedStatement.setString(1, name);
            preparedStatement.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}

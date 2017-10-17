package krzysztoflema.models.dao.daoImpl;


import krzysztoflema.models.ClientModel;
import krzysztoflema.models.MySqlConnector;
import krzysztoflema.models.dao.ClientDao;

import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ClientDaoImpl implements ClientDao {

    private MySqlConnector connector = MySqlConnector.getInstace();


    public boolean addClient(String name, String surname, String nip, String street, String city, String cityCode, String phone, String comments) {
        try {

            PreparedStatement statement = connector.getConnection().prepareStatement("INSERT INTO client VALUES (?,?,?,?,?,?,?,?,?)");
            statement.setInt(1, 0);
            statement.setString(2, name);
            statement.setString(3, surname);
            statement.setString(4, nip);
            statement.setString(5, street);
            statement.setString(6, city);
            statement.setString(7, cityCode);
            statement.setString(8, phone);
            statement.setString(9, comments);
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public List<ClientModel> getAllClients() {

        List<ClientModel> clientModels = new ArrayList<ClientModel>();
        try {
            PreparedStatement statement = connector.getConnection().prepareStatement(
                    "SELECT * FROM client");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                clientModels.add(new ClientModel(
                        resultSet.getString("name"),
                        resultSet.getString("surname"),
                        resultSet.getString("nip"),
                        resultSet.getString("street"),
                        resultSet.getString("city"),
                        resultSet.getString("cityCode"),
                        resultSet.getString("phoneNumber")));
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientModels;
    }


    @Override
    public boolean removeClient(String name, String nip) {
        try {
            PreparedStatement statement = connector.getConnection().prepareStatement(
                    "DELETE FROM client WHERE name=? AND nip=?");
            statement.setString(1, name);
            statement.setString(2, nip);
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean editClient(String newName, String newSurname, String newNip, String newStreet, String newCity, String newCityCode, String newPhone, String newComments, String name, String nip) {
        try {
            PreparedStatement statement = connector.getConnection().prepareStatement(
                    "UPDATE client SET name=?, surname=?,nip=?,street=?,city=?,cityCode=?,phoneNumber=?,comments=? WHERE name=? AND nip=?");
            statement.setString(1,newName);
            statement.setString(2,newSurname);
            statement.setString(3,newNip);
            statement.setString(4,newStreet);
            statement.setString(5,newCity);
            statement.setString(6,newCityCode);
            statement.setString(7,newPhone);
            statement.setString(8,newComments);
            statement.setString(9,name);
            statement.setString(10,nip);
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean collectClient(String name, String nip) {
        try {
            PreparedStatement statement = connector.getConnection().prepareStatement(
                    "SELECT name FROM client WHERE name=? AND nip=?");
            statement.setString(1, name);
            statement.setString(2, nip);
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

}

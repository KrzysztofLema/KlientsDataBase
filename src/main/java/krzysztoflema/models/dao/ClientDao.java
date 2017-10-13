package krzysztoflema.models.dao;

import krzysztoflema.models.ClientModel;

import java.util.List;

public interface ClientDao {
    boolean addClient(String name, String surname, String nip, String street, String city, String cityCode, String phone, String comments);
    List<ClientModel> getAllClients();
    boolean removeClient(String name,String nip);
    boolean editClient(String name);


}

package krzysztoflema.models.dao;

public interface ClientDao {
    boolean addClient(String name, String surname, String nip, String street, String city, String cityCode, String phone, String comments );
}

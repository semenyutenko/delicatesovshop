package models;

public class Client {

    private int clientId;
    private String name = "Unknown";
    private int phone;
    private String clientDescription = "";

    public Client(String name) {
        this.name = name;
    }

    public Client(int phone) {
        this.phone = phone;
    }

    public Client(String name, int phone) {
        this.name = name;
        this.phone = phone;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getClientDescription() {
        return clientDescription;
    }

    public void setClientDescription(String clientDescription) {
        this.clientDescription = clientDescription;
    }
}

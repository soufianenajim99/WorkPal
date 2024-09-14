package org.workpal.Models;

public class DefaultService extends Service{
    public DefaultService() {
    }

    public DefaultService(String name, String description, double price) {
        super(name, description, price);
    }

    public DefaultService(int id, String name, String description, double price) {
        super(id, name, description, price);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}

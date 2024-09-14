package org.workpal.Models;

public class AdditionalService extends Service{
    public AdditionalService() {
    }

    public AdditionalService(String name, String description, double price) {
        super(name, description, price);
    }

    public AdditionalService(int id, String name, String description, double price) {
        super(id, name, description, price);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}

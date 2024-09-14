package org.workpal.Models;

public class SubscriptionService {
    private int subscriptionId;
    private int serviceId;

    public SubscriptionService(int subscriptionId, int serviceId) {
        this.subscriptionId = subscriptionId;
        this.serviceId = serviceId;
    }

    public int getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(int subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    @Override
    public String toString() {
        return "SubscriptionService{" +
                "subscriptionId=" + subscriptionId +
                ", serviceId=" + serviceId +
                '}';
    }
}

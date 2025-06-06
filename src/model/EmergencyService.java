package model;

import java.io.Serializable;
import interfaces.Alertable;
import interfaces.Reportable;

public class EmergencyService extends CityResource implements Serializable,Alertable, Reportable {
    private String serviceType; // Police or Fire
    private int responseTime; // in minutes

    public EmergencyService(String resourceID, String location, String status, String serviceType, int responseTime) {
        super(resourceID, location, status);
        this.serviceType = serviceType;
        this.responseTime = responseTime;
    }

    public EmergencyService(String resourceID, String location, String status, String serviceType) {
        this(resourceID, location, status, serviceType, 5); // default to 5 mins
    }


    public String getServiceType() {
        return serviceType;
    }

    public int getResponseTime() {
        return responseTime;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public void setResponseTime(int responseTime) {
        this.responseTime = responseTime;
    }

    @Override
    public double calculateMaintenanceCost() {
        return 500 + (10 * responseTime);
    }

    @Override
    public void sendEmergencyAlert(String message) {
        System.out.println("[" + serviceType + " Unit Alert] " + message);
    }

    @Override
    public String generateUsageReport() {
        return "Emergency Service (" + serviceType + ") - Response Time: " + responseTime + " min";
    }

    @Override
    public String toString() {
        return super.toString() + ", Type: Emergency (" + serviceType + "), Response Time: " + responseTime + " min";
    }
}

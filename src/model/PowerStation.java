package model;

import java.io.Serializable;
import interfaces.Alertable;
import interfaces.Reportable;

public class PowerStation extends CityResource implements Serializable,Alertable, Reportable {
    private double energyOutput; // in kWh

    public PowerStation(String resourceID, String location, String status, double energyOutput) {
        super(resourceID, location, status);
        this.energyOutput = energyOutput;
    }

    public double getEnergyOutput() {
        return energyOutput;
    }

    public void setEnergyOutput(double energyOutput) {
        this.energyOutput = energyOutput;
    }

    @Override
    public double calculateMaintenanceCost() {
        return energyOutput * 0.05;
    }

    @Override
    public void sendEmergencyAlert(String message) {
        System.out.println("[PowerStation Alert] " + message);
    }

    @Override
    public String generateUsageReport() {
        return "PowerStation Output: " + energyOutput + " kWh";
    }

    @Override
    public String toString() {
        return super.toString() + ", Type: Power Station, Output: " + energyOutput + " kWh";
    }
}

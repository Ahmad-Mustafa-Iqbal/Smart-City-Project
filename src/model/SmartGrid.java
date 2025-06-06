package model;

import java.io.Serializable;
import java.util.ArrayList;

public class SmartGrid implements Serializable{
    private ArrayList<PowerStation> stations;

    public SmartGrid() {
        stations = new ArrayList<>();
    }

    public void addPowerStation(PowerStation ps) {
        stations.add(ps);
    }

    public double getTotalEnergyOutput() {
        double total = 0;
        for (PowerStation ps : stations) {
            total += ps.getEnergyOutput();
        }
        return total;
    }

    public String generateGridReport() {
        StringBuilder sb = new StringBuilder("=== Smart Grid Report ===\n");
        for (PowerStation ps : stations) {
            sb.append(ps.generateUsageReport()).append("\n");
        }
        sb.append("Total Energy Output: ").append(getTotalEnergyOutput()).append(" kWh\n");
        return sb.toString();
    }

    public ArrayList<PowerStation> getAllStations() {
        return stations;
    }
}

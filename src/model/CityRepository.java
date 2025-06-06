package model;

import java.io.Serializable;
import java.util.ArrayList;

import interfaces.Reportable;

public class CityRepository<T extends CityResource> implements Serializable{
    private ArrayList<T> resources;

    public CityRepository() {
        resources = new ArrayList<>();
    }

    public void generateAllReports() {
        for (T resource : resources) {
            if (resource instanceof Reportable) {
                System.out.println(((Reportable) resource).generateUsageReport());
            }
        }
    }
    

    public void addResource(T resource) {
        if (findById(resource.getResourceID()) == null) {
            resources.add(resource);
        } else {
            System.out.println("Duplicate Resource ID: " + resource.getResourceID());
        }
    }


    public void removeResource(T resource) {
        resources.remove(resource);
    }

    public T findById(String resourceID) {
        for (T resource : resources) {
            if (resource.getResourceID().equals(resourceID)) {
                return resource;
            }
        }
        return null;
    }

    public ArrayList<T> getAllResources() {
        return resources;
    }

    public void displayAllResources() {
        for (T resource : resources) {
            System.out.println(resource);
        }
    }

    public int getTotalResources() {
        return resources.size();
    }
}
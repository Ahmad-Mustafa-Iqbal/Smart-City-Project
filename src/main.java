// import model.*;
// import java.util.ArrayList;

// import interfaces.Alertable;
// import interfaces.Reportable;

// public class main {
//     public static void main(String[] args) {
//         CityRepository<CityResource> repo = new CityRepository<>();

//         repo.addResource(new TransportUnit("T01", "Central", "Active", 100, 3.2));
//         repo.addResource(new PowerStation("P01", "North", "Operational", 15000));
//         repo.addResource(new EmergencyService("E01", "South", "Ready", "Ambulance", 2));

//         System.out.println("All Resources:");
//         for (CityResource cr : repo.getAllResources()) {
//             System.out.println(cr);
//         }

//         FileManager.saveToFile(repo.getAllResources(), "test_resources.dat");

//         ArrayList<CityResource> loaded = FileManager.loadFromFile("test_resources.dat");
//         System.out.println("\nLoaded From File:");
//         for (CityResource cr : loaded) {
//             System.out.println(cr);
//         }

//         CityResource t = new TransportUnit("T02", "East", "Active", 80, 2.5);
//         CityResource p = new PowerStation("P02", "West", "Operational", 20000);
//         CityResource e = new EmergencyService("E02", "Downtown", "Standby", "Fire", 3);

//         System.out.println("\nTesting Alerts and Reports:");

//         if (t instanceof Alertable) {
//             ((Alertable) t).sendEmergencyAlert("Help there is a fire in the unit");
//         } else {
//             System.out.println("TransportUnit does not support alerts.");
//         }

//         if (p instanceof Reportable) {
//             ((Reportable) p).generateUsageReport();
//         } else {
//             System.out.println("PowerStation does not support reports.");
//         }

//         if (e instanceof Alertable) {
//             ((Alertable) e).sendEmergencyAlert("A A Chud Gai chud gai ");
//         } else {
//             System.out.println("EmergencyService does not support alerts.");
//         }

//         if (e instanceof Reportable) {
//             ((Reportable) e).generateUsageReport();
//         } else {
//             System.out.println("EmergencyService does not support reports.");
//         }

//         System.out.println("\nFiltered: Emergency Services Only");
//         for (CityResource cr : repo.getAllResources()) {
//             if (cr instanceof EmergencyService) {
//                 System.out.println(cr);
//             }
//         }
//     }
// }

// import gui.SmartCityGUI;
// import javax.swing.SwingUtilities;

// public class main {
//     public static void main(String[] args) {
//         // Launch the GUI on the Event Dispatch Thread (EDT)
//         SwingUtilities.invokeLater(new Runnable() {
//             public void run() {
//                 new SmartCityGUI(); // GUI class you created
//             }
//         });
//     }
// }

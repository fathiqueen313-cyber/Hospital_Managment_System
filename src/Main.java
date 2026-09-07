import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final PatientBST patientBST = new PatientBST();
    private static final EmergencyQueue emergencyQueue = new EmergencyQueue();
    private static final TreatmentStack treatmentStack = new TreatmentStack();

    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("MINI HOSPITAL EMERGENCY MANAGEMENT SYSTEM");
        System.out.println("========================================");

        loadSampleData();
        showMenu();
    }

    private static void showMenu() {
        int choice;

        do {
            System.out.println("\n1. Register New Patient");
            System.out.println("2. Search Patient");
            System.out.println("3. Delete Patient");
            System.out.println("4. Display All Patients");
            System.out.println("5. Add Patient to Emergency Queue");
            System.out.println("6. View Emergency Queue");
            System.out.println("7. Call Next Patient for Treatment");
            System.out.println("8. Complete Treatment");
            System.out.println("9. View Treatment History");
            System.out.println("10. Add Patient Visit");
            System.out.println("11. Remove Patient Visit");
            System.out.println("12. Search Patient Visit");
            System.out.println("13. Display Patient Visit History");
            System.out.println("14. Exit");
            System.out.print("Enter your choice: ");

            choice = readInt(1, 14);

            switch (choice) {
                case 1:
                    registerPatient();
                    break;
                case 2:
                    searchPatient();
                    break;
                case 3:
                    deletePatient();
                    break;
                case 4:
                    displayAllPatients();
                    break;
                case 5:
                    addToEmergencyQueue();
                    break;
                case 6:
                    viewEmergencyQueue();
                    break;
                case 7:
                    callNextPatient();
                    break;
                case 8:
                    completeTreatment();
                    break;
                case 9:
                    viewTreatmentHistory();
                    break;
                case 10:
                    addPatientVisit();
                    break;
                case 11:
                    removePatientVisit();
                    break;
                case 12:
                    searchPatientVisit();
                    break;
                case 13:
                    displayPatientVisitHistory();
                    break;
                case 14:
                    System.out.println("Thank you for using the Mini Hospital Emergency Management System.");
                    break;
                default:
                    System.out.println("Invalid menu choice. Please try again.");
            }
        } while (choice != 14);
    }

    private static void loadSampleData() {
        Patient p1 = new Patient(101, "Alice Johnson", 28, "0712345678", "Fever");
        Patient p2 = new Patient(105, "Brian Smith", 42, "0723456789", "Fracture");
        Patient p3 = new Patient(110, "Carla Davis", 35, "0734567890", "Asthma");
        Patient p4 = new Patient(115, "Daniel Green", 51, "0745678901", "Chest Pain");
        Patient p5 = new Patient(120, "Eva Brown", 24, "0756789012", "Food Poisoning");

        patientBST.insert(p1);
        patientBST.insert(p2);
        patientBST.insert(p3);
        patientBST.insert(p4);
        patientBST.insert(p5);

        p1.getVisitHistory().addVisit(new Visit(1, "2025-01-10", "Dr. Patel", "Cold", "Rest and Fluids"));
        p1.getVisitHistory().addVisit(new Visit(2, "2025-04-16", "Dr. Khan", "Migraine", "Pain Relief"));
        p3.getVisitHistory().addVisit(new Visit(3, "2025-02-08", "Dr. Silva", "Asthma Attack", "Nebulizer"));

        emergencyQueue.enqueue(p2);
        emergencyQueue.enqueue(p4);
        emergencyQueue.enqueue(p1);

        treatmentStack.push(new TreatmentRecord("Dr. Harper", "Wound cleaning", p2.getPatientId(), p2.getPatientName(), "Bandaging and antibiotics"));
        treatmentStack.push(new TreatmentRecord("Dr. Lopez", "Blood pressure check", p3.getPatientId(), p3.getPatientName(), "Medication adjustment"));

        System.out.println("Sample dataset loaded successfully for demonstration.");
    }

    private static void registerPatient() {
        System.out.println("\nRegister New Patient");

        int id = readIntRange("Enter Patient ID: ", 1, Integer.MAX_VALUE);
        if (patientBST.search(id) != null) {
            System.out.println("Patient ID " + id + " already exists. Duplicate IDs are not allowed.");
            return;
        }

        String name = readRequiredText("Enter Patient Name: ");
        int age = readIntRange("Enter Age: ", 1, 120);
        String contact = readRequiredText("Enter Contact Number: ");
        String condition = readRequiredText("Enter Medical Condition: ");

        Patient patient = new Patient(id, name, age, contact, condition);
        boolean inserted = patientBST.insert(patient);
        if (inserted) {
            System.out.println("Patient registered successfully.");
            System.out.println(patient);
        }
    }

    private static void searchPatient() {
        System.out.println("\nSearch Patient");
        int id = readIntRange("Enter Patient ID to search: ", 1, Integer.MAX_VALUE);
        Patient patient = patientBST.search(id);

        if (patient == null) {
            System.out.println("Patient with ID " + id + " was not found.");
        } else {
            System.out.println("Patient found:");
            System.out.println(patient);
        }
    }

    private static void deletePatient() {
        System.out.println("\nDelete Patient");
        int id = readIntRange("Enter Patient ID to delete: ", 1, Integer.MAX_VALUE);

        if (patientBST.search(id) == null) {
            System.out.println("Patient with ID " + id + " was not found.");
            return;
        }

        boolean deleted = patientBST.delete(id);
        if (deleted) {
            System.out.println("Patient with ID " + id + " has been deleted successfully.");
        } else {
            System.out.println("Unable to delete patient with ID " + id + ".");
        }
    }

    private static void displayAllPatients() {
        System.out.println("\nDisplay All Patients");
        patientBST.displayInOrder();
    }

    private static void addToEmergencyQueue() {
        System.out.println("\nAdd Patient to Emergency Queue");
        int id = readIntRange("Enter Patient ID: ", 1, Integer.MAX_VALUE);
        Patient patient = patientBST.search(id);

        if (patient == null) {
            System.out.println("Patient not found in records. Register the patient first.");
            return;
        }

        emergencyQueue.enqueue(patient);
        System.out.println("Patient " + patient.getPatientName() + " added to emergency queue.");
    }

    private static void viewEmergencyQueue() {
        System.out.println("\nView Emergency Queue");
        emergencyQueue.displayQueue();
    }

    private static void callNextPatient() {
        System.out.println("\nCall Next Patient for Treatment");
        Patient patient = emergencyQueue.dequeue();

        if (patient == null) {
            System.out.println("Emergency queue is empty. No patient to call.");
        } else {
            System.out.println("Now treating: " + patient.getPatientName() + " (ID: " + patient.getPatientId() + ")");
        }
    }

    private static void completeTreatment() {
        System.out.println("\nComplete Treatment");
        int patientId = readIntRange("Enter Patient ID: ", 1, Integer.MAX_VALUE);
        Patient patient = patientBST.search(patientId);

        if (patient == null) {
            System.out.println("Patient not found.");
            return;
        }

        String doctorName = readRequiredText("Enter Doctor Name: ");
        String diagnosis = readRequiredText("Enter Treatment / Diagnosis: ");
        String date = readRequiredText("Enter Treatment Date (YYYY-MM-DD): ");

        TreatmentRecord record = new TreatmentRecord(doctorName, diagnosis, patientId, patient.getPatientName(), date);
        treatmentStack.push(record);

        System.out.println("Treatment completed and recorded successfully.");
        System.out.println(record);
    }

    private static void viewTreatmentHistory() {
        System.out.println("\nView Treatment History");
        treatmentStack.display();
    }

    private static void addPatientVisit() {
        System.out.println("\nAdd Patient Visit");
        int patientId = readIntRange("Enter Patient ID: ", 1, Integer.MAX_VALUE);
        Patient patient = patientBST.search(patientId);

        if (patient == null) {
            System.out.println("Patient not found.");
            return;
        }

        int visitId = readIntRange("Enter Visit ID: ", 1, Integer.MAX_VALUE);
        String date = readRequiredText("Enter Visit Date (YYYY-MM-DD): ");
        String doctor = readRequiredText("Enter Doctor Name: ");
        String diagnosis = readRequiredText("Enter Diagnosis: ");
        String treatment = readRequiredText("Enter Treatment: ");

        Visit visit = new Visit(visitId, date, doctor, diagnosis, treatment);
        patient.getVisitHistory().addVisit(visit);
        System.out.println("Visit added successfully for patient " + patient.getPatientName() + ".");
    }

    private static void removePatientVisit() {
        System.out.println("\nRemove Patient Visit");
        int patientId = readIntRange("Enter Patient ID: ", 1, Integer.MAX_VALUE);
        Patient patient = patientBST.search(patientId);

        if (patient == null) {
            System.out.println("Patient not found.");
            return;
        }

        int visitId = readIntRange("Enter Visit ID to remove: ", 1, Integer.MAX_VALUE);
        boolean removed = patient.getVisitHistory().removeVisit(visitId);

        if (removed) {
            System.out.println("Visit ID " + visitId + " removed successfully.");
        } else {
            System.out.println("Visit ID " + visitId + " was not found.");
        }
    }

    private static void searchPatientVisit() {
        System.out.println("\nSearch Patient Visit");
        int patientId = readIntRange("Enter Patient ID: ", 1, Integer.MAX_VALUE);
        Patient patient = patientBST.search(patientId);

        if (patient == null) {
            System.out.println("Patient not found.");
            return;
        }

        int visitId = readIntRange("Enter Visit ID to search: ", 1, Integer.MAX_VALUE);
        Visit visit = patient.getVisitHistory().searchVisit(visitId);

        if (visit == null) {
            System.out.println("Visit ID " + visitId + " not found for patient " + patient.getPatientName() + ".");
        } else {
            System.out.println("Visit found:");
            System.out.println(visit);
        }
    }

    private static void displayPatientVisitHistory() {
        System.out.println("\nDisplay Patient Visit History");
        int patientId = readIntRange("Enter Patient ID: ", 1, Integer.MAX_VALUE);
        Patient patient = patientBST.search(patientId);

        if (patient == null) {
            System.out.println("Patient not found.");
            return;
        }

        patient.getVisitHistory().displayVisits();
    }

    private static int readInt(int min, int max) {
        while (true) {
            try {
                int value = Integer.parseInt(scanner.nextLine().trim());
                if (value >= min && value <= max) {
                    return value;
                }
                System.out.println("Please enter a number between " + min + " and " + max + ".");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid whole number.");
            }
        }
    }

    private static int readIntRange(String prompt, int min, int max) {
        System.out.print(prompt);
        return readInt(min, max);
    }

    private static String readRequiredText(String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = scanner.nextLine().trim();
            if (!value.isEmpty()) {
                return value;
            }
            System.out.println("This field cannot be empty. Please enter a valid value.");
        }
    }
}

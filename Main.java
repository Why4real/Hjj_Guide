import java.text.SimpleDateFormat;
import java.util.*;

class Person {
    // Protected fields for storing basic information about a person
    protected String name;
    protected int age;
    protected String idNumber;

    // Constructor to initialize all fields
    public Person(String name, int age, String idNumber) {
        this.name = name;
        this.age = age;
        this.idNumber = idNumber;
    }

    // Default constructor initializing fields with default values
    public Person() {
        this.name = "";
        this.age = 0;
        this.idNumber = "";
    }

    // Getter for 'name' field

    public String getName() {
        return name;
    }

    // Setter for 'name' field

    public void setName(String name) {
        this.name = name;
    }

    // Getter for 'age' field

    public int getAge() {
        return age;
    }

    // Setter for 'age' field

    public void setAge(int age) {
        this.age = age;
    }

    // Getter for 'idNumber' field

    public String getIdNumber() {
        return idNumber;
    }

    // Setter for 'idNumber' field

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    //Method to validate and get a string input containing only letters and spaces

    public String getValidStringInput(String prompt) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();

            // Split input into parts by commas and validate each part

            String[] parts = input.split(",");
            boolean valid = true;

            for (String part : parts) {
                part = part.trim();
                if (!part.matches("[a-zA-Z ]+")) { //Check if part contains only letters and spaces
                    valid = false;
                    break;
                }
            }

            // Return valid input or prompt user to re-enter

            if (valid) {
                return input;
            } else {
                System.out.println("Invalid input. Please enter only letters and spaces for each item, separated by commas.");
            }
        }
    }

    // Private method to validate and get an integer input
    private int getValidIntInput(String prompt) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print(prompt);
            try {
                int input = Integer.parseInt(scanner.nextLine());
                return input;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }

    // Method to register a person's details (name and age)

    public void register(Scanner scanner) {
        setName(getValidStringInput("Enter Pilgrim's name: "));
        setAge(getValidIntInput("Enter Pilgrim's age: "));
    }
}

class Pilgrim extends Person {
    //Private Fields
    private String nationality;
    private String passportNumber;
    private String visaNum;
    private MedicalProfile medicalProfile;
    private TransportSchedule transportSchedule;
    private Accommodation accommodation;
    private PermitSystem permitSystem;

    //This is the constructor of the Pilgrim class. It initializes the object's properties with the provided values
    public Pilgrim(String nationality, String visaNum, String name, int age, String idNumber, String passportNumber, PermitSystem permitSystem, Accommodation accommodation) {
        super(name, age, idNumber);
        this.nationality = nationality;
        this.passportNumber = passportNumber;
        this.visaNum = visaNum;
        this.medicalProfile = new MedicalProfile();
        this.transportSchedule = new TransportSchedule();
        this.accommodation = accommodation;
        this.permitSystem = permitSystem;
    }

    //Getter and Setter Methods
    public String getNationality() { return nationality; }
    public void setNationality(String nationality) { this.nationality = nationality; }
    public String getPassportNumber() { return passportNumber; }
    public void setPassportNumber(String passportNumber) { this.passportNumber = passportNumber; }
    public String getVisaNum() { return visaNum; }
    public void setVisaNum(String visaNum) { this.visaNum = visaNum; }
    public MedicalProfile getMedicalProfile() { return medicalProfile; }
    public TransportSchedule getTransportSchedule() { return transportSchedule; }
    public Accommodation getAccommodation() { return accommodation; }
    public PermitSystem getPermitSystem() { return permitSystem; }

    //guides the user through the registration process for the pilgrim.
    //It prompts the user for information like nationality, passport number, visa number, and medical details.
    @Override
    public void register(Scanner scanner) {
        boolean done = false;
        while (!done) {
            System.out.print("Are you from a Gulf country? (yes/no): ");
            String isFromGulf = scanner.nextLine().toLowerCase();

            if (isFromGulf.equals("yes")) {
                System.out.println("Select your Gulf country from the list:");
                for (int i = 0; i < TransportSchedule.GULF_COUNTRIES.length; i++) {
                    System.out.println((i + 1) + ". " + TransportSchedule.GULF_COUNTRIES[i].substring(0, 1).toUpperCase() + TransportSchedule.GULF_COUNTRIES[i].substring(1));
                }

                int choice;
                while (true) {
                    System.out.print("Enter the number of your Gulf country: ");
                    try {
                        choice = Integer.parseInt(scanner.nextLine());
                        if (choice >= 1 && choice <= TransportSchedule.GULF_COUNTRIES.length) {
                            setNationality(TransportSchedule.GULF_COUNTRIES[choice - 1]);
                            break;
                        } else {
                            System.out.println("Invalid choice. Please select a number between 1 and " + TransportSchedule.GULF_COUNTRIES.length);
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a valid number.");
                    }
                }

                setIdNumber(getValidTenDigitInput("Enter Pilgrim's ID number (10 digits): "));
                done = true;
            } else if (isFromGulf.equals("no")) {
                setNationality(getValidStringInput("Enter Pilgrim's nationality: "));
                setPassportNumber(getValidPassportNumber("Enter Pilgrim's passport number: "));
                setVisaNum(getValidTenDigitInput("Enter Pilgrim's visa number (10 digits): "));
                done = true;
            } else {
                System.out.println("Invalid input. Please enter YES or NO.");
            }
        }

        medicalProfile.addMedicalInfo();
    }

    //input validation: only letters
    @Override
    public String getValidStringInput(String prompt) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();

            String[] parts = input.split(",");
            boolean valid = true;

            for (String part : parts) {
                part = part.trim();
                if (!part.matches("[a-zA-Z ]+")) {
                    valid = false;
                    break;
                }
            }

            if (valid) {
                return input;
            } else {
                System.out.println("Invalid input. Please enter only letters and spaces for each item, separated by commas.");
            }
        }
    }

    //input validation: 10 digits
    private String getValidTenDigitInput(String prompt) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (input.matches("\\d{10}")) {
                return input;
            } else {
                System.out.println("Invalid input. Please enter exactly 10 digits.");
            }
        }
    }

    //input validation: only numbers
    private String getValidPassportNumber(String prompt) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (input.matches("\\d+")) {
                return input;
            } else {
                System.out.println("Invalid input. Please enter numbers only.");
            }
        }
    }

    //displays the information of the registered pilgrim
    public void viewPilgrimInfo() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nPilgrim Information:");

        if (isGulfCountry()) {
            System.out.println("ID Number: " + getIdNumber());
        } else {
            System.out.println("Passport Number: " + getPassportNumber());
            System.out.println("Visa Number: " + getVisaNum());
        }

        medicalProfile.viewMedicalInfo();
        System.out.println("Accommodation: ");
        System.out.println(accommodation.viewAmenities());

        if (permitSystem.getPermitNumber() != null) {
            permitSystem.viewPermit();
        } else {
            System.out.println("Permit: Did not apply ");
        }
    }

    private boolean isGulfCountry() {
        return Arrays.asList(TransportSchedule.GULF_COUNTRIES).contains(getNationality());
    }
}

class Admin extends Person {
    //Private Fields
    private Accommodation accommodation;
    private MedicalProfile medicalProfile;
    private final TransportSchedule transportSchedule;
    private String password;

    //constructor that initializes the object's properties with the provided values
    public Admin(String name, int age, String idNumber, String password) {
        super(name, age, idNumber);
        this.password = password;
        this.transportSchedule = new TransportSchedule();
        this.accommodation = new Accommodation();
        this.medicalProfile = new MedicalProfile();
    }

    //checks if the provided inputPassword matches the admin's stored password
    public boolean checkPassword(String inputPassword) {
        return this.password.equals(inputPassword);
    }

    private int getValidIntegerInput(String prompt) {
        Scanner scanner = new Scanner(System.in);
        int choice;
        while (true) {
            try {
                System.out.print(prompt);
                choice = Integer.parseInt(scanner.nextLine());
                if (choice > 0) {
                    return choice;
                } else {
                    System.out.println("Please enter a valid positive number.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }

    //the core method of the class, It provides a menu-driven interface for the admin to manage various aspects of a Pilgrim object
    public void managePilgrim(Pilgrim pilgrim) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            showAdminMenu();
            int choice = getValidIntegerInput("Choose an option: ");
            switch (choice) {
                case 1:
                    pilgrim.viewPilgrimInfo();
                    break;
                case 2:
                    manageMedicalProfile(pilgrim, scanner);
                    break;
                case 3:
                    manageTransportSchedule(pilgrim);
                    break;
                case 4:
                    manageAccommodation(pilgrim);
                    break;
                case 5:
                    System.out.println("Exiting Admin Menu...");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

            if (!askToContinue(scanner)) {
                running = false;
            }
        }
    }

    //asks the user if they want to continue or exit the menu
    private boolean askToContinue(Scanner scanner) {
        System.out.print("Do you want to go back to the menu or exit? (B for back, E for exit): ");
        String choice = scanner.nextLine().toUpperCase();
        return choice.equals("B");
    }


    //handles the management of the pilgrim's medical profile, including viewing and updating
    private void manageMedicalProfile(Pilgrim pilgrim, Scanner scanner) {
        boolean done = false;
        System.out.println("\nMedical Profile:");
        System.out.println("1. View Medical Profile");
        System.out.println("2. Update Medical Profile");
        System.out.println("3. Exit Medical Profile");
        System.out.print("Choose an option: ");
        String choice = scanner.nextLine();

        while(!done) {
            switch (choice) {
                case "1":
                    pilgrim.getMedicalProfile().viewMedicalInfo();
                    done = true;
                    break;
                case "2":
                    updateMedicalInfo(pilgrim, scanner);
                    done = true;
                    break;
                case "3":
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    //updates the pilgrim's medical information based on user input
    private void updateMedicalInfo(Pilgrim pilgrim, Scanner scanner) {
        System.out.println("Current Medical Profile:");
        pilgrim.getMedicalProfile().viewMedicalInfo();

        pilgrim.getMedicalProfile().updateMedicalInfo();
        System.out.println("Medical profile updated.");
    }

    //handles the management of the pilgrim's transport schedule, including viewing and updating
    private void manageTransportSchedule(Pilgrim pilgrim) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nTransport Schedule:");
        System.out.println("1. View Transport Schedule");
        System.out.println("2. Update Transport Schedule");
        int choice = getValidIntegerInput("Choose an option: ");
        if (choice == 1) {

        } else if (choice == 2) {
            updateTransportSchedule(pilgrim);
        } else {
            System.out.println("Invalid choice.");
        }
    }

    //updates the pilgrim's transport schedule based on user input
    private void updateTransportSchedule(Pilgrim pilgrim) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter new transport schedule (comma-separated times): ");
        String schedule = scanner.nextLine();
        System.out.println("Transport schedule updated.");
    }

    //handles the management of the pilgrim's accommodation, including viewing and updating
    private void manageAccommodation(Pilgrim pilgrim) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nAccommodation:");
        System.out.println("1. View Accommodation");
        System.out.println("2. Update Accommodation");
        int choice = getValidIntegerInput("Choose an option: ");
        if (choice == 1) {
            System.out.println(pilgrim.getAccommodation().viewAmenities());
        } else if (choice == 2) {
            updateAccommodation(pilgrim);
        } else {
            System.out.println("Invalid choice.");
        }
    }

    //updates the pilgrim's accommodation details based on user input
    private void updateAccommodation(Pilgrim pilgrim) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Current Accommodation: " + pilgrim.getAccommodation().viewAmenities());
        System.out.print("Enter new accommodation ID: ");
        String accommodationId = scanner.nextLine();
        System.out.print("Enter new Hotel: ");
        String hotel = scanner.nextLine();
        System.out.print("Enter new Room: ");
        String room = scanner.nextLine();
        System.out.print("Enter new Food Package: ");
        String foodPackage = scanner.nextLine();
        System.out.print("Enter new Location: ");
        String location = scanner.nextLine();
        System.out.print("Enter new Total Cost: ");
        double cost = scanner.nextDouble();
        pilgrim.getAccommodation().viewAmenities();

        System.out.println("Accommodation updated.");
    }

    //displays the admin menu options to the user
    private void showAdminMenu() {
        System.out.println("\nAdmin Menu:");
        System.out.println("1. View Pilgrim Info");
        System.out.println("2. Manage Medical Profile");
        System.out.println("3. Manage Transport Schedule");
        System.out.println("4. Manage Accommodation");
        System.out.println("5. Exit");
    }

    //prompts the user for string input and validates the input
    public String getValidStringInput(String prompt) {
        Scanner scanner = new Scanner(System.in);
        String input;
        while (true) {
            System.out.print(prompt);
            input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            } else {
                System.out.println("Invalid input. Please enter a valid string.");
            }
        }
    }
}

class TransportSchedule {
    public static final String[] GULF_COUNTRIES = {"saudi", "uae", "kuwait", "bahrain", "qatar", "oman"}; //constant array holding the names of Gulf countries
    private Map<String, Map<String, List<String>>> routes; //nested Map to store route information
    private Map<String, String> bookedTickets; //Map to store booked tickets
    private int ticketCounter = 1000; //integer to generate unique ticket numbers. It starts at 1000

    //Constructor to Initializes the routes and bookedTickets maps. It also adds some predefined routes with their respective times
    public TransportSchedule() {
        routes = new HashMap<>();
        bookedTickets = new HashMap<>();

        addRoute("Jabal Alnoor", "Al Haram", Arrays.asList("08:00 AM", "10:00 AM", "12:00 PM"));
        addRoute("Mina Camp", "Al Haram", Arrays.asList("09:00 AM", "11:00 AM", "13:00 PM"));
        addRoute("Arafat Plain", "Al Haram", Arrays.asList("07:00 AM", "09:00 AM", "11:00 AM"));
        addRoute("Muzdalifah", "Al Haram", Arrays.asList("06:00 AM", "08:00 AM", "10:00AM"));
    }

    //Adds a new route to the routes map
    public void addRoute(String location, String destination, List<String> times) {
        routes.putIfAbsent(location, new HashMap<>());
        routes.get(location).put(destination, times);
    }

    //Prints a numbered list of available departure locations
    public void showAvailableLocations() {
        System.out.println("\nAvailable Bus Locations:");
        System.out.println("1. Jabal Alnoor");
        System.out.println("2. Mina Camp");
        System.out.println("3. Arafat Plain");
        System.out.println("4. Muzdalifah");
    }

    //Prints the available departure times from a given location to "Al Haram"
    public void showAvailableTimes(String location) {
        Map<String, List<String>> destinationMap = routes.get(location);
        if (destinationMap == null) {
            System.out.println("No routes available for this location.");
            return;
        }

        System.out.println("Available routes to Al Haram from " + location + ":");
        List<String> times = destinationMap.get("Al Haram");
        int count = 1;
        for (String time : times) {
            System.out.println(count + ". " + time);
            count++;
        }
    }

    //Books a ticket and returns the unique ticket number. It increments the ticketCounter for each booking
    public String bookTicket(String location, String destination, String time) {
        String ticketNumber = String.valueOf(ticketCounter++);
        bookedTickets.put(ticketNumber, location + " to " + destination + " at " + time);
        return ticketNumber;
    }

    //Cancels a ticket by removing it from the bookedTickets map
    public void cancelTicket(String ticketNumber) {
        if (bookedTickets.containsKey(ticketNumber)) {
            bookedTickets.remove(ticketNumber);
            System.out.println("Ticket " + ticketNumber + " has been canceled.");
        } else {
            System.out.println("Invalid ticket number.");
        }
    }

    //Displays the details of a booked ticket
    public void viewTicketDetails(String ticketNumber) {
        if (bookedTickets.containsKey(ticketNumber)) {
            System.out.println("Ticket " + ticketNumber + " details: " + bookedTickets.get(ticketNumber));
        } else {
            System.out.println("Invalid ticket number.");
        }
    }

    //This is the main menu-driven method for interacting with the transportation system
    public void transportList(Scanner scanner) {
        System.out.println("\n****** Transportation System ******");

        while (true) {
            System.out.println("\n1. View Transportation Options");
            System.out.println("2. Book a Ticket");
            System.out.println("3. View Your Ticket Details");
            System.out.println("4. Cancel Your Ticket");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    showAvailableLocations();
                    break;
                case "2":
                    bookTicketProcess(scanner);
                    break;
                case "3":
                    System.out.print("Enter your ticket number: ");
                    String ticketNumber = scanner.nextLine();
                    viewTicketDetails(ticketNumber);
                    break;
                case "4":
                    System.out.print("Enter your ticket number: ");
                    String cancelTicketNumber = scanner.nextLine();
                    cancelTicket(cancelTicketNumber);
                    break;
                case "5":
                    return;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    //handles the process of booking a ticket
    private void bookTicketProcess(Scanner scanner) {
        showAvailableLocations();
        System.out.print("Enter the location number: ");
        String locationChoice = scanner.nextLine();

        String location = null;
        switch (locationChoice) {
            case "1":
                location = "Jabal Alnoor";
                break;
            case "2":
                location = "Mina Camp";
                break;
            case "3":
                location = "Arafat Plain";
                break;
            case "4":
                location = "Muzdalifah";
                break;
            default:
                System.out.println("Invalid choice! Returning to menu.");
                return;
        }

        String destination = "Al Haram";

        showAvailableTimes(location);
        System.out.print("Enter the time number: ");
        int timeChoice;
        try {
            timeChoice = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input! Returning to menu.");
            return;
        }

        List<String> availableTimes = routes.get(location).get(destination);
        if (timeChoice < 1 || timeChoice > availableTimes.size()) {
            System.out.println("Invalid time choice! Returning to menu.");
            return;
        }

        String time = availableTimes.get(timeChoice - 1);

        String ticketNumber = bookTicket(location, destination, time);
        System.out.println("Ticket booked successfully! Your ticket number is: " + ticketNumber);
    }


}

class MedicalProfile {
    //variables store the medical information
    private String bloodType;
    private String allergies;
    private String medicalConditions;
    private String emergencyContactName;
    private String emergencyContactPhone;
    private String vaccinationHospital;
    private String vaccinationAppointmentDate;
    private String vaccinationAppointmentTime;
    private int appointmentNumber;

    //constructor initializes all string variables to empty strings and the appointmentNumber to 0
    public MedicalProfile() {
        this.bloodType = "";
        this.allergies = "";
        this.medicalConditions = "";
        this.emergencyContactName = "";
        this.emergencyContactPhone = "";
        this.vaccinationHospital = "";
        this.vaccinationAppointmentDate = "";
        this.vaccinationAppointmentTime = "";
        this.appointmentNumber = 0;
    }

    //checks if a given string is a valid blood type
    public boolean isBloodTypeValid(String bloodType) {
        String[] validBloodTypes = {"A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-"};
        //compare the input (case-insensitively and after trimming whitespace) to an array of valid blood types
        for (String validType : validBloodTypes) {
            if (validType.equalsIgnoreCase(bloodType.trim())) {
                return true;
            }
        }
        return false;
    }

    //Ensures the input contains only letters and spaces, separated by commas.
    private String getValidStringInput(String prompt) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();

            String[] parts = input.split(",");
            boolean valid = true;

            for (String part : parts) {
                part = part.trim();

                if (!part.matches("[a-zA-Z ]+")) {
                    valid = false;
                    break;
                }
            }

            if (valid) {
                return input;
            } else {
                System.out.println("Invalid input. Please enter only letters and spaces for each item, separated by commas.");
            }
        }
    }

    //Ensures the input contains only letters.
    private String getValidEmergencyContactName(String prompt) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();

            if (input.matches("[a-zA-Z]+")) {
                return input;
            } else {
                System.out.println("Invalid input. Emergency contact name should contain only letters.");
            }
        }
    }

    //Ensures the input contains exactly 10 digits.
    private String getValidTenDigitInput(String prompt) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (input.matches("\\d{10}")) {
                return input;
            } else {
                System.out.println("Invalid input. Please enter exactly 10 digits.");
            }
        }
    }

    //prompts the user for medical information
    public void addMedicalInfo() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Enter blood type: ");
            String inputBloodType = scanner.nextLine();
            if (isBloodTypeValid(inputBloodType)) {
                this.bloodType = inputBloodType.toUpperCase();
                break;
            } else {
                System.out.println("Invalid blood type. Please enter a valid blood type (A+, O+, B+, AB+, A-, O-, B-, AB-).");
            }
        }

        this.allergies = getValidStringInput("Enter allergies (comma-separated): ");
        this.medicalConditions = getValidStringInput("Enter medical conditions (comma-separated): ");
        this.emergencyContactName = getValidEmergencyContactName("Enter emergency contact name: ");
        this.emergencyContactPhone = getValidTenDigitInput("Enter emergency contact phone number (10 digits): ");
    }

    //displays the stored medical information to the user
    public void viewMedicalInfo() {
        System.out.println("Blood Type: " + (this.bloodType.trim().isEmpty() ? "Not Provided" : this.bloodType));
        System.out.println("Allergies: " + (this.allergies.trim().isEmpty() ? "Not Provided" : this.allergies));
        System.out.println("Medical Conditions: " + (this.medicalConditions.trim().isEmpty() ? "Not Provided" : this.medicalConditions));
        System.out.println("Emergency Contact Name: " + (this.emergencyContactName.trim().isEmpty() ? "Not Provided" : this.emergencyContactName));
        System.out.println("Emergency Contact Number: " + (this.emergencyContactPhone.trim().isEmpty() ? "Not Provided" : this.emergencyContactPhone));
    }

    //allows the user to update their medical information
    public void updateMedicalInfo() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Enter new blood type: ");
            String inputBloodType = scanner.nextLine();
            if (isBloodTypeValid(inputBloodType)) {
                this.bloodType = inputBloodType.toUpperCase();
                break;
            } else {
                System.out.println("Invalid blood type. Please enter a valid blood type (A+, O+, B+, AB+, A-, O-, B-, AB-).");
            }
        }

        this.allergies = getValidStringInput("Enter new allergies (comma-separated): ");
        this.medicalConditions = getValidStringInput("Enter new medical conditions (comma-separated): ");

        this.emergencyContactName = getValidEmergencyContactName("Enter new emergency contact name: ");
        this.emergencyContactPhone = getValidTenDigitInput("Enter new emergency contact phone number (10 digits): ");
    }

    //allows the user to book a vaccination appointment
    public void bookVaccinationAppointment() {
        Scanner scanner = new Scanner(System.in);
        boolean done = false;
        boolean Done = false;

        while (!done) {
            System.out.println("\nChoose a hospital in Makkah for the vaccination:");
            System.out.println("1. King Abdullah Medical City");
            System.out.println("2. Al Noor Specialist Hospital");
            System.out.println("3. Ajyad Emergency Hospital");
            System.out.println("4. Go back");
            System.out.print("Enter your choice: ");
            String hospitalChoice = scanner.nextLine();

            switch (hospitalChoice) {
                case "1":
                    this.vaccinationHospital = "King Abdullah Medical City";
                    done = true;
                    break;
                case "2":
                    this.vaccinationHospital = "Al Noor Specialist Hospital";
                    done = true;
                    break;
                case "3":
                    this.vaccinationHospital = "Ajyad Emergency Hospital";
                    done = true;
                    break;
                case "4":
                    return;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }

        System.out.print("Enter the date for the vaccination appointment (e.g., 2024-12-20): ");
        this.vaccinationAppointmentDate = scanner.nextLine();

        while (!Done) {
            System.out.println("\nAvailable time slots:");
            System.out.println("1. 8:00 AM");
            System.out.println("2. 10:00 AM");
            System.out.println("3. 12:00 PM");
            System.out.println("4. 2:00 PM");
            System.out.println("5. 4:00 PM");
            System.out.print("Enter your choice: ");
            String hospitalChoice = scanner.nextLine();

            switch (hospitalChoice) {
                case "1":
                    this.vaccinationAppointmentTime = "8:00 AM";
                    Done = true;
                    break;
                case "2":
                    this.vaccinationAppointmentTime = "10:00 AM";
                    Done = true;
                    break;
                case "3":
                    this.vaccinationAppointmentTime = "12:00 PM";
                    Done = true;
                    break;
                case "4":
                    this.vaccinationAppointmentTime = "2:00 PM";
                    Done = true;
                    break;
                case "5":
                    this.vaccinationAppointmentTime = "4:00 PM";
                    Done = true;
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }

        this.appointmentNumber = generateAppointmentNumber();

        System.out.println("Vaccination appointment booked successfully!");
        System.out.println("Hospital: " + vaccinationHospital);
        System.out.println("Date: " + vaccinationAppointmentDate);
        System.out.println("Time: " + vaccinationAppointmentTime);
        System.out.println("Your appointment number is: " + appointmentNumber);
    }

    //Generates a random 4-digit appointment number
    private int generateAppointmentNumber() {
        Random random = new Random();
        return random.nextInt(9000) + 1000;
    }

    //presents a menu to the user, allowing them to view, update, or book appointments
    public void medicalList(Scanner scanner) {
        System.out.println("\n*****Medical Services System*****");
        while (true) {
            System.out.println("\n1. View your Medical ID");
            System.out.println("2. Update your Medical ID");
            System.out.println("3. Book a Vaccination Appointment");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    viewMedicalInfo();
                    break;
                case "2":
                    updateMedicalInfo();
                    break;
                case "3":
                    bookVaccinationAppointment();
                    break;
                case "4":
                    return;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }
}

class Accommodation {
    //Private Fields
    private String ArafatTent;
    private String mina;
    private String room;
    private String hotel;
    private String location;
    private String foodPackage;
    private double cost = 0.0;
    private boolean ArafatisBooked;
    private boolean MinaisBooked;
    private boolean HotelBooked;
    private boolean foodisBooked;

    //Booking Method: Arafat Tent
    public void arafatTent() {
        System.out.println(" ");
        System.out.println("** Book Arafat Tent **");
        if(ArafatisBooked == true){
            System.out.println("You Already booked an Arafat Tent! ");
            return;
        }
        this.ArafatTent = "Arafat Tent";
        System.out.println("Arafat Tent is booked successfully! ");
        ArafatisBooked = true;
    }

    //cancel Arafat Tent booking and update the booking flags
    public void cancleArafatTent() {
        if(!ArafatisBooked){
            System.out.println("You already Did not Book an Arafat Tent! ");
            return;
        }
        this.ArafatTent = null;
        ArafatisBooked = false;
        System.out.println("Arafat Tent booking has been cancelled successfully! ");
    }

    //Booking Method: Mina Camp
    public void minaCamp() {
        Scanner scanner = new Scanner(System.in);

        System.out.println(" ");
        System.out.println("** Book Mina Camp **");
        if(MinaisBooked == true){
            System.out.println("You Already booked a mina Camp! ");
            return;
        }
        while(MinaisBooked != true) {
            System.out.println("\nWhich Mina Camp do you want to Book?");
            System.out.println("1. Majar Al-Kabsh Camp");
            System.out.println("2. Al-Muaisim Camp");
            System.out.print("Enter your choice: ");
            String MinaChoice = scanner.nextLine();

            switch (MinaChoice) {
                case "1":
                    this.mina = "Majar Al-Kabsh Camp";
                    System.out.println("Mina Camp is booked successfully! ");
                    this.MinaisBooked = true;
                    break;
                case "2":
                    this.mina = "Al-Muaisim Camp";
                    System.out.println("Mina Camp is booked successfully! ");
                    this.MinaisBooked = true;
                    break;
                default:
                    System.out.println("Invalid choice!");
                    break;
            }
        }
    }

    //cancel Mina Camp booking and update the booking flags
    public void cancleMinaCamp() {
        if(!MinaisBooked){
            System.out.println("You already Did not Book a Mian Camp! ");
            return;
        }
        this.mina = null;
        MinaisBooked = false;
        System.out.println("Mina Camp booking has been cancelled successfully! ");
    }

    //Booking Method: Hotel
    public void hotelBooking() {
        Scanner scanner = new Scanner(System.in);

        boolean hotel = false;

        System.out.println(" ");
        System.out.println("** Book a Hotel **");
        if(HotelBooked == true){
            System.out.println("You Already booked a Hotel room! ");
            return;
        }

        while(hotel != true) {
            System.out.println("\nWhat Hotel do you want to Book? ");
            System.out.println("1. 5-Star Hotel");
            System.out.println("2. 4-Star Hotel");
            System.out.println("3. 3-Star Hotel or Residential Building");
            System.out.print("Enter your choice: ");
            String HotelChoice = scanner.nextLine();

            switch (HotelChoice) {
                case "1":
                    this.hotel = "5-Star Hotel";
                    this.location = "Dar Al Tawhid Intercontinental Makkah";
                    hotel = true;
                    break;
                case "2":
                    this.hotel = "4-Star Hotel";
                    this.location = "Park Inn by Radisson Makkah";
                    hotel = true;
                    break;
                case "3":
                    this.hotel = "3-Star Hotel or Residential Building";
                    this.location = "Al Rayyan Towers";
                    hotel = true;
                    break;
                default:
                    System.out.println("Invalid choice!");
                    break;
            }
        }
    }

    //Booking Method: Hotel Room
    public void roomBooking() {
        Scanner scanner = new Scanner(System.in);
        while(HotelBooked != true) {
            System.out.println("\nChoose room type:");
            System.out.println("1. Single");
            System.out.println("2. Double");
            System.out.println("3. Triple");
            System.out.println("4. Quadruple");
            System.out.print("Enter your choice: ");
            String roomChoice = scanner.nextLine();

            switch (roomChoice) {
                case "1":
                    this.room = "Single";
                    System.out.println("Hotel is booked successfully! ");
                    this.HotelBooked = true;
                    break;
                case "2":
                    this.room = "Double";
                    System.out.println("Hotel is booked successfully! ");
                    this.HotelBooked = true;
                    break;
                case "3":
                    this.room = "Triple";
                    System.out.println("Hotel is booked successfully! ");
                    this.HotelBooked = true;
                    break;
                default:
                    System.out.println("Invalid choice!");
                    break;
            }
        }
    }

    //cancel Hotel booking and update the booking flags
    public void cancleHotel() {
        if(!HotelBooked){
            System.out.println("You already Did not Book a Hotel! ");
            return;
        }
        this.hotel = null;
        this.room = null;
        this.location = null;
        this.HotelBooked = false;
        this.cost = 0.0;
        foodPackage = null;
        foodisBooked = false;
        System.out.println("Hotel booking has been cancelled successfully! ");
    }

    //provides a menu-driven interface for the user to interact with the accommodation booking system
    public void addAccommodationDetails() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n** Accommodation Booking System **");
            System.out.println("1. Book Arafat Tent");
            System.out.println("2. Book Mina Camp");
            System.out.println("3. Book a Hotel");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    arafatTent();
                    break;
                case "2":
                    minaCamp();
                    break;
                case "3":
                    hotelBooking();
                    roomBooking();
                    food();
                    break;
                case "4":
                    System.out.println("Exiting accommodation booking system.");
                    return;
                default:
                    System.out.println("Invalid choice!");
                    break;
            }
        }
    }

    //Booking Method: Food Package
    public void food(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n** Choose Food Package **");
        while (foodisBooked != true) {
            System.out.println("\n1. Basic Meal Plan (Breakfast only)");
            System.out.println("2. Standard Meal Plan (Breakfast, Lunch, and Dinner)");
            System.out.println("3. Premium Meal Plan (All meals and snacks)");
            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    this.foodPackage = "Basic Meal Plan (Breakfast only)";
                    System.out.println("Basic Meal Plan selected.");
                    foodisBooked = true;
                    break;
                case "2":
                    this.foodPackage = "Standard Meal Plan (Breakfast, Lunch, and Dinner)";
                    System.out.println("Standard Meal Plan selected.");
                    foodisBooked = true;
                    break;
                case "3":
                    this.foodPackage = "Premium Meal Plan (All meals and snacks)";
                    System.out.println("Premium Meal Plan selected.");
                    foodisBooked = true;
                    break;
                default:
                    System.out.println("Invalid choice!");
                    break;
            }
        }
    }

    //presents a menu to the user for canceling specific bookings
    public void cancle() {
        Scanner scanner = new Scanner(System.in);

        while(ArafatisBooked == true || MinaisBooked == true || HotelBooked == true) {
            System.out.println("\nChoose what to cancle");
            System.out.println("1. Arafat Tent");
            System.out.println("2. Mina Camp");
            System.out.println("3. Hotel");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine();

            switch(choice) {
                case "1":
                    cancleArafatTent();
                    break;
                case "2":
                    cancleMinaCamp();
                    break;
                case "3":
                    cancleHotel();
                    break;
                case "4":
                    return;
                default:
                    System.out.println("Invalid choice!");
                    break;
            }
        }
    }

    //calculates the total cost of the accommodation
    public double calculateAccommodationCost() {
        cost = 0.0;

        if (hotel != null) {
            switch (hotel) {
                case "5-Star Hotel":
                    cost += 10000.0;
                    break;
                case "4-Star Hotel":
                    cost += 6500.0;
                    break;
                case "3-Star Hotel or Residential Building":
                    cost += 4500.0;
                    break;
                default:
                    System.out.println("No hotel booked.");
                    break;
            }
        }

        if (room != null) {
            switch (room) {
                case "Single":
                    cost += 100.0;
                    break;
                case "Double":
                    cost += 200.0;
                    break;
                case "Triple":
                    cost += 300.0;
                    break;
                default:
                    System.out.println("No room type selected.");
                    break;
            }
        } else {
            System.out.println("No room booked.");
        }

        return cost;
    }

    //Sets the accommodation details manually
    public void setAccommodationDetails(String ArafatTent, String mina, String hotel, String room, String location, double cost) {
        this.ArafatTent = ArafatTent;
        this.mina = mina;
        this.hotel = hotel;
        this.room = room;
        this.foodPackage = foodPackage;
        this.location = location;
        this.cost = cost;
    }

    //displays the details of the booked accommodations
    public String viewAmenities() {
        return "\nArafat Tent: " + (ArafatTent != null ? ArafatTent : "Not Booked") +
                "\nMina Camp: " + (mina != null ? mina : "Not Booked") +
                "\nHotel: " + (hotel != null ? hotel : "Not Booked") +
                "\nRoom: " + (room != null ? room : "Not Booked") +
                "\nFood Package: " + (foodPackage != null ? foodPackage : "Not Booked") +
                "\nHotel Location: " + (location != null ? location : "Not Booked") +
                "\nCost: " + (cost > 0 ? cost : "Not Calculated");
    }

    //provides a menu-driven interface for the user to interact with the accommodation booking system
    public void accommodationList(Scanner scanner) {
        System.out.println("\n****** Accommodation System ******");
        while (true) {
            System.out.println("\n1. Accommodation booking services");
            System.out.println("2. Cancel Accommodation booking");
            System.out.println("3. View your accommodation details");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    addAccommodationDetails();
                    calculateAccommodationCost();
                    break;
                case "2":
                    cancle();
                    break;
                case "3":
                    setAccommodationDetails(ArafatTent, mina, hotel, room, location, cost);
                    System.out.println(viewAmenities());
                    break;
                case "4":
                    return;
                default:
                    System.out.println("Invalid choice!");
                    break;
            }
        }
    }

}

class PermitSystem {
    //Private fields to store permit
    private String permitNumber; //Private fields to store permit details
    private String issueDate; //Date when the permit was issued
    private String expiryDate; //Date when the permit will expire

    //Constructor to intialize the permit system with a type and expiry date
    public PermitSystem(String permitType, String expiryDate) {
        this.permitNumber = null; //permit number is initially null
        this.issueDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date()); //Current date as issue date
        this.expiryDate = expiryDate; //Provided expiry date
    }

    //Getter method to retrieve the permit
    public String getPermitNumber() {
        return permitNumber;
    }

    //Method to generate a new permit
    public void generatePermit(Scanner scanner) {
        boolean done = false;
        while (!done) {
            //Display options for permit types
            System.out.println("\nSelect the type of permit you need:");
            System.out.println("1. Umrah Permit");
            System.out.println("2. Prayer in Al-Rawdah Permit");
            System.out.println("3. Domestic Hajj Permit");
            System.out.println("4. International Hajj Permit");
            System.out.print("Enter your choice: ");

            String choice = scanner.nextLine();

            String permitType = ""; //Type of the permit
            String expiryDate = generateExpiryDate(); //Generate expiry date dynamically

            //Determine permit type based on user input
            switch (choice) {
                case "1":
                    permitType = "Umrah Permit";
                    done = true; //Exit the loop
                    break;
                case "2":
                    permitType = "Prayer in Al-Rawdah Permit";
                    done = true;
                    break;
                case "3":
                    permitType = "Domestic Hajj Permit";
                    done = true;
                    break;
                case "4":
                    permitType = "International Hajj Permit";
                    done = true;
                    break;
                default:
                    //Invalid choice message
                    System.out.println("\nInvalid choice!");
                    return;
            }

            //Generate a unique permit number qnd set issue/expiry dates
            this.permitNumber = "HUP-" + System.currentTimeMillis(); //Unique ID based on timestamp
            this.issueDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date()); //Current date as issue date
            this.expiryDate = expiryDate; //set expiry date

            //Display generated permit details
            System.out.println("Permit generated: " + getPermitNumber());
            System.out.println("Permit Type: " + permitType);
            System.out.println("Issue Date: " + issueDate);
            System.out.println("Expiry Date: " + expiryDate);
        }
    }

    //Private helper method to generate an expiry date 7 days from the current date
    private static String generateExpiryDate() {
        Calendar calendar = Calendar.getInstance(); //Get current date
        calendar.add(Calendar.DAY_OF_YEAR, 7); // Add 7 days to current date
        Date expiryDate = calendar.getTime(); //Compute expiry date
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); //Format date as a string
        return sdf.format(expiryDate); //Return formatted expiry date
    }

    //Method to view the details if an issued permit
    public void viewPermit() {
       //Check if a permit has been issued
        if (permitNumber == null || permitNumber.isEmpty()) {
            System.out.println("No permit has been issued for this pilgrim.");
        } else {
            System.out.println("Permit Number: " + permitNumber);
            System.out.println("Issue Date: " + issueDate);
            System.out.println("Expiry Date: " + expiryDate);
        }
    }

    //Main method to manage the permit system menu
    public void permitList(Scanner scanner) {
        System.out.println("\n****** Permit System ******");
        while (true) {
            System.out.println("\n1. Apply for a permit");
            System.out.println("2. View your permit");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    generatePermit(scanner); //Call method to apply for a permit
                    break;
                case "2":
                    viewPermit(); //Call method to view permit details
                    break;
                case "3":
                    return; //Exit the loop
                default:
                    //Invalid choice message
                    System.out.println("\nInvalid choice!");
                    break;
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Create instances of various classes
        Accommodation accommodation = new Accommodation(); //Accommodation services
        PermitSystem permitSystem = new PermitSystem("Umrah", "2024-12-17"); // Permit system details
        Person person = new Person();
        Pilgrim pilgrim = new Pilgrim("saudi", "12345", "John Doe", 30, "ID123", "U2005", permitSystem, accommodation); // Pilgrim details
        MedicalProfile medicalProfile = new MedicalProfile(); // Medical profile of the pilgrim
        TransportSchedule transportSchedule = new TransportSchedule();// Transport schedule for the pilgrim
        Admin admin = new Admin("Admin", 35, "adminID", "admin123");//Admin details

        // Main menu
        while (true) {
            System.out.println("\nMain Menu:");
            System.out.println("1. Pilgrim Section");// Access pilgrim-specific options
            System.out.println("2. Admin Section"); //Access admin-specific options
            System.out.println("3. Exit"); //Exit the program
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine();

            // Handle user choices in the main menu
            switch (choice) {
                case "1":
                    pilgrimSection(scanner, person, pilgrim, permitSystem, medicalProfile, transportSchedule);
                    break;
                case "2":
                    adminSection(scanner, admin, pilgrim);
                    break;
                case "3":
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice!");
                    break;
            }
        }
    }

    // Pilgrim section menu
    private static void pilgrimSection(Scanner scanner, Person person, Pilgrim pilgrim, PermitSystem permitSystem, MedicalProfile medicalProfile, TransportSchedule transportSchedule) {
        //Register basic details of the person and pilgrim
        person.register(scanner);
        pilgrim.register(scanner);

        // Pilgrim section
        while (true) {
            System.out.println("\nPilgrim Section:");
            System.out.println("1. Permit System");// Access permit system options
            System.out.println("2. Your Medical ID & Medical Services"); //View or manage medical profile
            System.out.println("3. Transportation Services");//Access transportation services
            System.out.println("4. Accommodation Services");//View accommodation options
            System.out.println("5. Go Back to Main Menu"); //Return to the main menu
            System.out.print("Choose an option: ");
            String pilgrimChoice = scanner.nextLine();

            // Handle pilgrim section options
            switch (pilgrimChoice) {
                case "1":
                    pilgrim.getPermitSystem().permitList(scanner);// Access permit-related functionalities
                    break;
                case "2":
                    pilgrim.getMedicalProfile().medicalList(scanner); // Access medical-related functionalities
                    break;
                case "3":
                    pilgrim.getTransportSchedule().transportList(scanner);// Access transportation-related functionalities
                    break;
                case "4":
                    pilgrim.getAccommodation().accommodationList(scanner);// Access accommodation-related functionalities
                    break;
                case "5":
                    return;// Go back to the main menu
                default:
                    System.out.println("Invalid choice! Please try again.");// Error message for invalid input
                    break;
            }
        }
    }

    // Admin section menu
    private static void adminSection(Scanner scanner, Admin admin, Pilgrim pilgrim) {
        // Prompt admin for password
        System.out.print("Enter Admin Password: ");
        String inputPassword = scanner.nextLine();

        // Check if the entered password is correct
        if (admin.checkPassword(inputPassword)) {
            System.out.println("Access Granted.");// Successful login message
            while (true) {
                System.out.println("\nAdmin Section:");
                System.out.println("1. Manage Pilgrim");// Option to manage pilgrim details
                System.out.println("2. Go Back to Main Menu"); // Return to the main menu
                System.out.print("Choose an option: ");
                String adminChoice = scanner.nextLine();

                // Handle admin section options
                switch (adminChoice) {
                    case "1":
                        admin.managePilgrim(pilgrim); // Allow admin to manage pilgrim details
                        break;
                    case "2":
                        return;// Go back to the main menu
                    default:
                        System.out.println("Invalid choice!");// Error message for invalid input
                        break;
                }
            }
        } else {
            System.out.println("Access Denied. Incorrect Password.");// Error message for incorrect password
        }
    }
}
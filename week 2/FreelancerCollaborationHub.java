import java.util.*;

//======================== USER ========================
class User {

    private int id;
    private String name;
    private String email;
    private String password;

    static int totalUsers = 0;

    public User() {
        totalUsers++;
    }

    public User(int id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        totalUsers++;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public boolean checkPassword(String pwd) {
        return password.equals(pwd);
    }

    public void login() {
        System.out.println("Login Successful");
    }

    public void displayProfile() {
        System.out.println("ID : " + id);
        System.out.println("Name : " + name);
        System.out.println("Email : " + email);
    }
}

//===================== INTERFACE =====================
interface Reviewable {
    void giveReview();
}

//===================== FREELANCER =====================
class Freelancer extends User implements Reviewable {

    private String skill;
    private double hourlyRate;

    public Freelancer(int id, String name, String email,
                      String password, String skill,
                      double hourlyRate) {

        super(id, name, email, password);
        this.skill = skill;
        this.hourlyRate = hourlyRate;
    }

    @Override
    public void displayProfile() {

        System.out.println("\n----- Freelancer -----");
        System.out.println("Name : " + getName());
        System.out.println("Email : " + getEmail());
        System.out.println("Skill : " + skill);
        System.out.println("Hourly Rate : " + hourlyRate);
    }

    @Override
    public void giveReview() {
        System.out.println("Review Submitted Successfully.");
    }

    public String getSkill() {
        return skill;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }
}

//===================== CLIENT =====================
class Client extends User implements Reviewable {

    private String companyName;

    public Client(int id, String name, String email,
                  String password, String companyName) {

        super(id, name, email, password);
        this.companyName = companyName;
    }

    @Override
    public void displayProfile() {

        System.out.println("\n----- Client -----");
        System.out.println("Name : " + getName());
        System.out.println("Email : " + getEmail());
        System.out.println("Company : " + companyName);
    }

    @Override
    public void giveReview() {
        System.out.println("Client Review Submitted.");
    }

    public String getCompanyName() {
        return companyName;
    }
}

//===================== PROJECT =====================
class Project {

    private int projectId;
    private String title;
    private String description;
    private double budget;

    public Project(int projectId,
                   String title,
                   String description,
                   double budget) {

        this.projectId = projectId;
        this.title = title;
        this.description = description;
        this.budget = budget;
    }

    public void displayProject() {

        System.out.println("\n----- Project -----");
        System.out.println("Project ID : " + projectId);
        System.out.println("Title : " + title);
        System.out.println("Description : " + description);
        System.out.println("Budget : " + budget);
    }

    public String getTitle() {
        return title;
    }

    public double getBudget() {
        return budget;
    }
}

//===================== PROPOSAL =====================
class Proposal {

    private String freelancerName;
    private String projectTitle;
    private double bidAmount;

    public Proposal(String freelancerName,
                    String projectTitle,
                    double bidAmount) {

        this.freelancerName = freelancerName;
        this.projectTitle = projectTitle;
        this.bidAmount = bidAmount;
    }

    public void displayProposal() {

        System.out.println("\n----- Proposal -----");
        System.out.println("Freelancer : " + freelancerName);
        System.out.println("Project : " + projectTitle);
        System.out.println("Bid Amount : " + bidAmount);
    }
}

//===================== ABSTRACT PAYMENT =====================
abstract class Payment {

    protected double amount;

    public Payment(double amount) {
        this.amount = amount;
    }

    // Abstract Method
    public abstract double calculatePayment();

    // Concrete Method
    public void displayPayment() {
        System.out.println("Payment Amount : " + calculatePayment());
    }
}

//===================== FIXED PAYMENT =====================
class FixedPayment extends Payment {

    public FixedPayment(double amount) {
        super(amount);
    }

    @Override
    public double calculatePayment() {
        return amount;
    }
}

//===================== HOURLY PAYMENT =====================
class HourlyPayment extends Payment {

    private int hours;

    public HourlyPayment(double rate, int hours) {
        super(rate);
        this.hours = hours;
    }

    @Override
    public double calculatePayment() {
        return amount * hours;
    }
}

//===================== LOGIN (METHOD OVERLOADING) =====================
class Login {

    // Default Login
    public void login() {
        System.out.println("Default Login");
    }

    // Overloaded Login
    public void login(HashMap<String, Freelancer> freelancers,
                      HashMap<String, Client> clients,
                      String email,
                      String password) {

        if (freelancers.containsKey(email)) {

            Freelancer f = freelancers.get(email);

            if (f.checkPassword(password)) {
                System.out.println("\nFreelancer Login Successful");
                System.out.println("Welcome " + f.getName());
            } else {
                System.out.println("Incorrect Password");
            }

        }
        else if (clients.containsKey(email)) {

            Client c = clients.get(email);

            if (c.checkPassword(password)) {
                System.out.println("\nClient Login Successful");
                System.out.println("Welcome " + c.getName());
            } else {
                System.out.println("Incorrect Password");
            }

        }
        else {

            System.out.println("User Not Found.");

        }
    }
}

//===================== ADMIN =====================
class Admin extends User {

    public Admin(int id,
                 String name,
                 String email,
                 String password) {

        super(id, name, email, password);
    }

    @Override
    public void displayProfile() {

        System.out.println("\n----- Admin -----");
        System.out.println("Name : " + getName());
    }

    public void generateReport(int users,
                               int freelancers,
                               int clients,
                               int projects) {

        System.out.println("\n========== ADMIN REPORT ==========");
        System.out.println("Total Users        : " + users);
        System.out.println("Total Freelancers  : " + freelancers);
        System.out.println("Total Clients      : " + clients);
        System.out.println("Total Projects     : " + projects);
        System.out.println("==================================");
    }
}
public class FreelancerCollaborationHub {

    static Scanner sc = new Scanner(System.in);

    static HashMap<String, Freelancer> freelancers = new HashMap<>();
    static HashMap<String, Client> clients = new HashMap<>();
    static ArrayList<Project> projects = new ArrayList<>();

    static Login login = new Login();

    //================ Register Freelancer =================
public static void registerFreelancer() {

    System.out.print("Enter ID : ");
    int fid = sc.nextInt();
    sc.nextLine();

    System.out.print("Enter Name : ");
    String fname = sc.nextLine();

    System.out.print("Enter Email : ");
    String femail = sc.nextLine();

    if (freelancers.containsKey(femail) || clients.containsKey(femail)) {
        System.out.println("Email Already Exists.");
        return;
    }

    System.out.print("Enter Password : ");
    String fpass = sc.nextLine();

    System.out.print("Enter Skill : ");
    String skill = sc.nextLine();

    System.out.print("Enter Hourly Rate : ");
    double rate = sc.nextDouble();
    sc.nextLine();

    Freelancer freelancer =
            new Freelancer(fid, fname, femail, fpass, skill, rate);

    freelancers.put(femail, freelancer);

    System.out.println("Freelancer Registered Successfully.");
}

//================ Register Client =================
public static void registerClient() {

    System.out.print("Enter ID : ");
    int cid = sc.nextInt();
    sc.nextLine();

    System.out.print("Enter Name : ");
    String cname = sc.nextLine();

    System.out.print("Enter Email : ");
    String cemail = sc.nextLine();

    if (clients.containsKey(cemail) || freelancers.containsKey(cemail)) {
        System.out.println("Email Already Exists.");
        return;
    }

    System.out.print("Enter Password : ");
    String cpass = sc.nextLine();

    System.out.print("Enter Company Name : ");
    String company = sc.nextLine();

    Client client =
            new Client(cid, cname, cemail, cpass, company);

    clients.put(cemail, client);

    System.out.println("Client Registered Successfully.");
}

//================ Post Project =================
public static void postProject() {

    System.out.print("Enter Project ID : ");
    int pid = sc.nextInt();
    sc.nextLine();

    System.out.print("Enter Project Title : ");
    String title = sc.nextLine();

    System.out.print("Enter Description : ");
    String desc = sc.nextLine();

    System.out.print("Enter Budget : ");
    double budget = sc.nextDouble();
    sc.nextLine();

    Project project = new Project(pid, title, desc, budget);

    projects.add(project);

    System.out.println("Project Posted Successfully.");
}

//================ View Freelancers =================
public static void viewFreelancers() {

    if (freelancers.isEmpty()) {
        System.out.println("No Freelancers Available.");
    } else {

        for (Freelancer f : freelancers.values()) {
            f.displayProfile();
        }

    }
}

//================ View Clients =================
public static void viewClients() {

    if (clients.isEmpty()) {
        System.out.println("No Clients Available.");
    } else {

        for (Client c : clients.values()) {
            c.displayProfile();
        }

    }
}

//================ View Projects =================
public static void viewProjects() {

    if (projects.isEmpty()) {
        System.out.println("No Projects Available.");
    } else {

        for (Project p : projects) {
            p.displayProject();
        }

    }
}

//================ Login =================
public static void loginUser() {

    System.out.print("Enter Email : ");
    String email = sc.nextLine();

    System.out.print("Enter Password : ");
    String password = sc.nextLine();

    login.login(freelancers, clients, email, password);
}

//================ Fixed Payment =================
public static void fixedPayment() {

    System.out.print("Enter Fixed Amount : ");
    double amount = sc.nextDouble();
    sc.nextLine();

    Payment payment = new FixedPayment(amount);

    payment.displayPayment();
}

//================ Hourly Payment =================
public static void hourlyPayment() {

    System.out.print("Enter Rate Per Hour : ");
    double rate = sc.nextDouble();
    sc.nextLine();

    System.out.print("Enter Hours : ");
    int hours = sc.nextInt();
    sc.nextLine();

    Payment payment = new HourlyPayment(rate, hours);

    payment.displayPayment();
}

//================ Give Review =================
public static void giveReview() {

    System.out.print("Enter Freelancer Email : ");
    String email = sc.nextLine();

    if (freelancers.containsKey(email)) {

        freelancers.get(email).giveReview();

    } else {

        System.out.println("Freelancer Not Found.");

    }
}

//================ Admin Report =================
public static void adminReport() {

    Admin admin =
            new Admin(1,
                    "Admin",
                    "admin@gmail.com",
                    "123");

    admin.generateReport(
            User.totalUsers,
            freelancers.size(),
            clients.size(),
            projects.size());
}

    public static void main(String[] args) {

        int choice;

        do {

            System.out.println("\n========== Freelancer Collaboration Hub ==========");
            System.out.println("1. Register Freelancer");
            System.out.println("2. Register Client");
            System.out.println("3. Post Project");
            System.out.println("4. View Freelancers");
            System.out.println("5. View Clients");
            System.out.println("6. View Projects");
            System.out.println("7. Login");
            System.out.println("8. Calculate Fixed Payment");
            System.out.println("9. Calculate Hourly Payment");
            System.out.println("10. Give Review");
            System.out.println("11. Admin Report");
            System.out.println("12. Exit");

            System.out.print("Enter Choice : ");

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    registerFreelancer();
                    break;

                case 2:
                    registerClient();
                    break;

                case 3:
                    postProject();
                    break;

                case 4:
                    viewFreelancers();
                    break;

                case 5:
                    viewClients();
                    break;

                case 6:
                    viewProjects();
                    break;

                case 7:
                    loginUser();
                    break;

                case 8:
                    fixedPayment();
                    break;

                case 9:
                    hourlyPayment();
                    break;

                case 10:
                    giveReview();
                    break;

                case 11:
                    adminReport();
                    break;

                case 12:
                    System.out.println("Thank You...");
                    break;

                default:
                    System.out.println("Invalid Choice");
            }

        } while (choice != 12);

        sc.close();
    }
}
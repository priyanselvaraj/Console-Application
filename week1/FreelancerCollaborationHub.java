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
    
    public boolean checkPassword(String pwd) {
        return password != null && password.equals(pwd);
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
        System.out.println("Skill : " + skill);
        System.out.println("Hourly Rate : " + hourlyRate);
    }

    @Override
    public void giveReview() {
        System.out.println("Review submitted for Freelancer.");
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

        System.out.println("\nProject ID : " + projectId);
        System.out.println("Title : " + title);
        System.out.println("Description : " + description);
        System.out.println("Budget : " + budget);

    }

    public double getBudget() {

        return budget;

    }

    public String getTitle() {

        return title;

    }

}
//===================== ABSTRACT PAYMENT =====================
abstract class Payment {

    protected double amount;

    public Payment(double amount) {
        this.amount = amount;
    }

    public abstract double calculatePayment();

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

//===================== LOGIN (METHOD OVERLOADING) =====================
class Login {

    public void login() {

        System.out.println("Default Login");

    }

    public void login(String email, String password) {

        System.out.println("Login Successful");
        System.out.println("Email : " + email);

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
                               int projects) {

        System.out.println("\n========== REPORT ==========");
        System.out.println("Total Users : " + users);
        System.out.println("Total Projects : " + projects);
        System.out.println("============================");

    }

}
//===================== MAIN CLASS =====================
public class FreelancerCollaborationHub {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        ArrayList<Freelancer> freelancers = new ArrayList<>();
        ArrayList<Client> clients = new ArrayList<>();
        ArrayList<Project> projects = new ArrayList<>();

        Login login = new Login();

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

                    System.out.print("Enter ID : ");
                    int fid = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter Name : ");
                    String fname = sc.nextLine();

                    System.out.print("Enter Email : ");
                    String femail = sc.nextLine();

                    System.out.print("Enter Password : ");
                    String fpass = sc.nextLine();

                    System.out.print("Enter Skill : ");
                    String skill = sc.nextLine();

                    System.out.print("Enter Hourly Rate : ");
                    double rate = sc.nextDouble();

                    freelancers.add(new Freelancer(fid, fname, femail, fpass, skill, rate));

                    System.out.println("Freelancer Registered Successfully.");
                    break;

                case 2:

                    System.out.print("Enter ID : ");
                    int cid = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter Name : ");
                    String cname = sc.nextLine();

                    System.out.print("Enter Email : ");
                    String cemail = sc.nextLine();

                    System.out.print("Enter Password : ");
                    String cpass = sc.nextLine();

                    System.out.print("Enter Company Name : ");
                    String company = sc.nextLine();

                    clients.add(new Client(cid, cname, cemail, cpass, company));

                    System.out.println("Client Registered Successfully.");
                    break;

                case 3:

                    System.out.print("Enter Project ID : ");
                    int pid = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter Project Title : ");
                    String title = sc.nextLine();

                    System.out.print("Enter Description : ");
                    String desc = sc.nextLine();

                    System.out.print("Enter Budget : ");
                    double budget = sc.nextDouble();

                    projects.add(new Project(pid, title, desc, budget));

                    System.out.println("Project Posted Successfully.");
                    break;

                case 4:

                    if (freelancers.isEmpty()) {
                        System.out.println("No Freelancers Available.");
                    } else {
                        for (Freelancer f : freelancers) {
                            f.displayProfile();
                        }
                    }
                    break;

                case 5:

                    if (clients.isEmpty()) {
                        System.out.println("No Clients Available.");
                    } else {
                        for (Client c : clients) {
                            c.displayProfile();
                        }
                    }
                    break;

                case 6:

                    if (projects.isEmpty()) {
                        System.out.println("No Projects Available.");
                    } else {
                        for (Project p : projects) {
                            p.displayProject();
                        }
                    }
                    break;

                case 7:

                    System.out.print("Enter Email : ");
                    String email = sc.nextLine();

                    System.out.print("Enter Password : ");
                    String password = sc.nextLine();

                    login.login(email, password);
                    break;

                case 8:

                    System.out.print("Enter Fixed Amount : ");
                    double amount = sc.nextDouble();

                    Payment p1 = new FixedPayment(amount);

                    System.out.println("Payment = " + p1.calculatePayment());

                    break;

                case 9:

                    System.out.print("Enter Rate Per Hour : ");
                    double r = sc.nextDouble();

                    System.out.print("Enter Hours : ");
                    int h = sc.nextInt();

                    Payment p2 = new HourlyPayment(r, h);

                    System.out.println("Payment = " + p2.calculatePayment());

                    break;

                case 10:

                    if (!freelancers.isEmpty()) {
                        freelancers.get(0).giveReview();
                    } else {
                        System.out.println("No Freelancer Available.");
                    }

                    break;

                case 11:

                    Admin admin = new Admin(1, "Admin", "admin@gmail.com", "123");

                    admin.generateReport(User.totalUsers, projects.size());

                    break;

                case 12:

                    System.out.println("Thank You...");
                    break;

                default:

                    System.out.println("Invalid Choice.");

            }

        } while (choice != 12);

        sc.close();

    }
}
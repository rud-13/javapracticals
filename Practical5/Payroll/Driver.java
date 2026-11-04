public class Driver {
    public static void main(String[] args) {
        Employee[] employees = {
            new FullTime("Pratham", 101, 30000),
            new PartTime("Yash", 102, 80, 200),
            new Intern("Krish", 103, 10000)
        };

        double total = 0;

        for (Employee e : employees) {
            double salary = e.monthlySalary();

            System.out.println("Name: " + e.name);
            System.out.println("ID: " + e.id);
            System.out.println("Monthly Salary: " + salary);

            if (e instanceof Intern) {
                System.out.println("Note: This employee is an intern.");
            }

            total += salary;
            System.out.println();
        }

        System.out.println("Total Payroll: " + total);
    }
}
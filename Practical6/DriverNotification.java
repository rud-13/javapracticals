public class DriverNotification {
    public static void main(String[] args) {
        Notifier email = message ->
            System.out.println("Email: " + message);

        Notifier sms = message ->
            System.out.println("SMS: " + message);

        Notifier urgentEmail = (Notifier & Urgent) message ->
            System.out.println("Urgent Email: " + message);

        Notifier[] senders = {email, sms, urgentEmail};

        String message = "Your bank account has been updated.";

        System.out.println("Broadcasting Message:");

        for (Notifier sender : senders) {
            sender.send(message);

            if (sender instanceof Urgent) {
                System.out.println("Sending urgent message again:");
                sender.send(message);
            }
        }
    }
}
import java.io.*;
import java.util.Arrays;

class UserSession implements Serializable {
    private static final long serialVersionUID = 1L;
    private String username;
    private transient String sessionSecretToken; // Not persisted
    private int loginCount;

    public UserSession(String username, String sessionSecretToken, int loginCount) {
        this.username = username;
        this.sessionSecretToken = sessionSecretToken;
        this.loginCount = loginCount;
    }

    @Override
    public String toString() {
        return "UserSession[user=" + username + ", secret=" + sessionSecretToken + ", logins=" + loginCount + "]";
    }
}

public class SerializationDemo {
    public static void main(String[] args) {
        File file = new File("sessions.dat");
        UserSession[] original = {
            new UserSession("alice", "SECRET_TOKEN_XYZ_123", 4),
            new UserSession("bob", "SECRET_TOKEN_ABC_999", 12)
        };

        System.out.println("=== Before Serialization ===");
        System.out.println(Arrays.toString(original));

        // Save to file
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(original);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Load from file
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            UserSession[] restored = (UserSession[]) ois.readObject();
            System.out.println("\n=== After Deserialization ===");
            System.out.println(Arrays.toString(restored));
            System.out.println("Notice: sessionSecretToken is restored as null because it is transient!");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            file.delete();
        }
    }
}
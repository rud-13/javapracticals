import java.lang.annotation.*;
import java.lang.reflect.Field;
import java.util.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface Id {}

class Repository<T> {
    private final Map<String, T> storage = new HashMap<>();

    private String extractId(T entity) {
        Class<?> clazz = entity.getClass();
        while (clazz != null) {
            for (Field f : clazz.getDeclaredFields()) {
                if (f.isAnnotationPresent(Id.class)) {
                    f.setAccessible(true);
                    try {
                        Object idVal = f.get(entity);
                        if (idVal != null) return idVal.toString();
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException("Cannot access @Id field", e);
                    }
                }
            }
            clazz = clazz.getSuperclass();
        }
        throw new IllegalArgumentException("No @Id annotation found on " + entity.getClass().getName());
    }

    public T save(T entity) {
        String id = extractId(entity);
        storage.put(id, entity);
        return entity;
    }

    @SafeVarargs
    public final void saveAll(T... items) {
        for (T item : items) {
            save(item);
        }
    }

    public T findById(String id) {
        return storage.get(id);
    }

    public List<T> findAll() {
        return new ArrayList<>(storage.values());
    }

    public boolean delete(String id) {
        return storage.remove(id) != null;
    }

    public static <N extends Number> double average(N[] values) {
        double sum = 0;
        for (N v : values) sum += v.doubleValue();
        return sum / values.length;
    }
}

class AccountEntity {
    @Id
    private String accountNumber;
    private String owner;
    private long balance;

    public AccountEntity(String accountNumber, String owner, long balance) {
        this.accountNumber = accountNumber;
        this.owner = owner;
        this.balance = balance;
    }

    @Override public String toString() { return "[" + accountNumber + "] " + owner + " (₹" + balance + ")"; }
}

public class MiniBankLab13 {
    public static void main(String[] args) {
        Repository<AccountEntity> repo = new Repository<>();

        AccountEntity a1 = new AccountEntity("AC0001", "Riya", 5000);
        AccountEntity a2 = new AccountEntity("AC0002", "Arjun", 8000);
        AccountEntity a3 = new AccountEntity("AC0003", "Karan", 15000);

        repo.saveAll(a1, a2, a3);
        System.out.println("Saved 3 accounts via @SafeVarargs saveAll.");

        AccountEntity found = repo.findById("AC0002");
        System.out.println("findById('AC0002'): " + found);

        System.out.println("\nAll accounts in generic repository:");
        for (AccountEntity a : repo.findAll()) {
            System.out.println(" - " + a);
        }

        Long[] balances = { 5000L, 8000L, 15000L };
        System.out.println("\nAverage Balance calculated via Bounded Generic Method: ₹" + Repository.average(balances));
    }
}
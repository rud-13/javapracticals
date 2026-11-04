import java.lang.reflect.Field;
import java.util.ArrayList;

public class FormValidator {
    public static ArrayList<String> validate(Object obj) {
        ArrayList<String> errors = new ArrayList<>();

        Field[] fields = obj.getClass().getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);

            try {
                Object value = field.get(obj);

                if (field.isAnnotationPresent(NotBlank.class)) {
                    if (value == null || value.toString().trim().isEmpty()) {
                        errors.add(field.getName() + " must not be blank");
                    }
                }

                if (field.isAnnotationPresent(MaxLength.class)) {
                    int max = field.getAnnotation(MaxLength.class).value();

                    if (value != null && value.toString().length() > max) {
                        errors.add(field.getName() + " exceeds maximum length " + max);
                    }
                }

            } catch (IllegalAccessException e) {
                errors.add("Cannot access " + field.getName());
            }
        }

        return errors;
    }
}
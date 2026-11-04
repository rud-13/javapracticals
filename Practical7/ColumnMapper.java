import java.lang.reflect.Field;

public class ColumnMapper {
    public static void populate(Object obj, String[] headers, String[] data) {
        Field[] fields = obj.getClass().getDeclaredFields();

        for (Field field : fields) {
            if (field.isAnnotationPresent(Column.class)) {
                String columnName =
                    field.getAnnotation(Column.class).name();

                field.setAccessible(true);
                boolean found = false;

                for (int i = 0; i < headers.length; i++) {
                    if (headers[i].equalsIgnoreCase(columnName)) {
                        try {
                            field.set(obj, data[i]);
                            found = true;
                        } catch (Exception e) {
                            System.out.println("Mapping error");
                        }
                        break;
                    }
                }

                if (!found) {
                    System.out.println("Missing column: " + columnName);
                }
            }
        }
    }
}
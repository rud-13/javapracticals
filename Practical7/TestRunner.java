import java.lang.reflect.Method;

public class TestRunner {
    public static int runTests(Object obj) {
        int count = 0;

        Method[] methods = obj.getClass().getDeclaredMethods();

        for (Method method : methods) {
            if (method.isAnnotationPresent(Run.class)
                    && method.getParameterCount() == 0) {
                try {
                    method.invoke(obj);
                    count++;
                } catch (Exception e) {
                    System.out.println("Test failed: " + method.getName());
                }
            }
        }

        return count;
    }
}
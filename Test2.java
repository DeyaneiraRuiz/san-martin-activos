import java.lang.reflect.Constructor;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;

public class Test2 {
    public static void main(String[] args) throws Exception {
        for (Constructor<?> c : DaoAuthenticationProvider.class.getConstructors()) {
            System.out.println(c);
        }
    }
}

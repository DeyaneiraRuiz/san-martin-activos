import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import java.lang.reflect.Method;
public class Test {
    public static void main(String[] args) throws Exception {
        Method m = DaoAuthenticationProvider.class.getMethod("setUserDetailsService", org.springframework.security.core.userdetails.UserDetailsService.class);
        System.out.println("Deprecated: " + m.isAnnotationPresent(Deprecated.class));
    }
}

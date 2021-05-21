package webLogic.security;

import java.util.*;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class LoginInterceptor implements Interceptor {
    @Override
    public void destroy() {

    }

    @Override
    public void init() {

    }

    @Override
    public String intercept(ActionInvocation invocation) throws Exception {
        Map<String, Object> session = invocation.getInvocationContext().getSession();
        if ((session.get("admin") != null && (Boolean) session.get("admin")) || (session.get("login") != null && (Boolean) session.get("login"))) {
            System.out.println(session);
            System.out.println("HEre now");
            return invocation.invoke();

        }
        return "authError";
    }

}

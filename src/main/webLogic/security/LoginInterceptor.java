package webLogic.action;

import java.util.*;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import org.apache.struts2.interceptor.SessionAware;

public class LoginInterceptor extends AbstractInterceptor  {

    private Map<String, Object> session;
    public String intercept(ActionInvocation invocation) throws Exception {

        /* let us do some pre-processing */

        System.out.println("test");
        String output = "Pre-Processing";
        System.out.println(output);
        return "failure";
    }

}

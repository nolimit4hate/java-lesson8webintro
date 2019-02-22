package listeners;

import controllers.AttributeName;
import model.UserRolePool;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 *      SessionCreationDestroyListener used for getting information about creation and destroyed sessions that output in
 * server console and on creation set attribute with name = userrole, value = UserRolePool.UNKNOWN to session.
 * Its for managing client roles and uri path
 */
@WebListener
public class SessionCreationDestroyListener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        session.setAttribute(AttributeName.USER_ROLE, UserRolePool.UNKNOWN);
        System.out.println("#SessionWasCreated#id={" + session.getId() + "}" + "\n" +
                "#SessionIsCreated#set role={" + UserRolePool.UNKNOWN + "}");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        System.out.println("#SessionWasDestroyed#id={" + se.getSession().getId() + "}");
    }
}

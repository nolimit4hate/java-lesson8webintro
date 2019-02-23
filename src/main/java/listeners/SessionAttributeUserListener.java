package listeners;

import controllers.ConstAttributeNames;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

/**
 * this listener just for getting info about changing session attributes: 'user', 'userRole'.
 */

@WebListener
public class SessionAttributeUserListener implements HttpSessionAttributeListener {

    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {
        HttpSession session = event.getSession();
        session.getId();
        System.out.println("#SessionAddAttr# id={" + session.getId() +
                "}\n#Session# user={" + session.getAttribute(ConstAttributeNames.USER) +
                "}\n#Session# role={" + session.getAttribute(ConstAttributeNames.USER_ROLE) + "}");
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent event) {

    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent event) {
        HttpSession session = event.getSession();
        session.getId();
        System.out.println("#SessionReplaceAttr# id={" + session.getId() +
                "}\n#Session# user={" + session.getAttribute(ConstAttributeNames.USER) +
                "}\n#Session# role={" + session.getAttribute(ConstAttributeNames.USER_ROLE) + "}");
    }


}

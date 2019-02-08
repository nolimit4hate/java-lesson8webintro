package listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class MyHttpSessionListener implements HttpSessionListener  {

    @Override
    public void sessionCreated(HttpSessionEvent sessionEvent){
//        System.out.println("Session created: {ID=" + sessionEvent.getSession().getId() + "}");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent sessionEvent){
//        System.out.println("Session destroyed: {ID=" + sessionEvent.getSession().getId() + "}");
    }
}

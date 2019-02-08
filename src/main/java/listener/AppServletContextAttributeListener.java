package listener;

import javax.servlet.ServletContextAttributeEvent;

public class AppServletContextAttributeListener implements javax.servlet.ServletContextAttributeListener {

    @Override
    public void attributeAdded(ServletContextAttributeEvent servletContextAttributeEvent){
//        System.out.println("Servlet context attribute added: {" + servletContextAttributeEvent.getName() +
//        "; value: " + servletContextAttributeEvent.getValue() + "}");
    }

    @Override
    public void attributeReplaced(ServletContextAttributeEvent servletContextAttributeEvent){
//        System.out.println("Servlet context attribute replaced: {" + servletContextAttributeEvent.getName() +
//                "; value: " + servletContextAttributeEvent.getValue() + "}");
    }

    @Override
    public void attributeRemoved(ServletContextAttributeEvent servletContextAttributeEvent){
//        System.out.println("Servlet context attribute Removed: {" + servletContextAttributeEvent.getName() +
//                "; value: " + servletContextAttributeEvent.getValue() + "}");
    }
}

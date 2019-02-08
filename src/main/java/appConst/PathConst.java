package appConst;

public abstract class PathConst {

    public final static String REDIRECT_TO_CONTEXT_PATH = "/mypr";
    public final static String REDIRECT_TO_HOME = REDIRECT_TO_CONTEXT_PATH + "/home";

    public final static String PART_OF_URL_FROM_HOME_TO_JSP = "/jsp_fold";
    public final static String PART_OF_URL_FROM_HOME_TO_HTML = "/pages/htmlpart";

    public final static String PART_OF_URL_JSP_TAIL_LOGIN_SUCCESS = "/loginSuccess.jsp";
    public final static String PART_OF_URL_HTML_TAIL_LOGIN = "/loginForm.html";

    public final static String REDIRECT_TO_LOGIN_SUCCESS = REDIRECT_TO_CONTEXT_PATH + PART_OF_URL_FROM_HOME_TO_JSP +
            PART_OF_URL_JSP_TAIL_LOGIN_SUCCESS;

    public final static String REDIRECT_TO_CHECKOUT = REDIRECT_TO_CONTEXT_PATH + PART_OF_URL_FROM_HOME_TO_JSP +
            "/checkoutPage.jsp";

    public final static String REDIRECT_TO_LOGIN = REDIRECT_TO_CONTEXT_PATH + PART_OF_URL_FROM_HOME_TO_HTML +
            PART_OF_URL_HTML_TAIL_LOGIN;
}

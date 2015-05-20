package org.xine.marketplace.frontend.views.controller.security;

import org.xine.marketplace.frontend.views.util.jsf.FacesUtil;
import org.xine.marketplace.frontend.views.util.jsf.HRequest;

import java.io.IOException;
import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The Class LoginBean.
 */
@Named
@SessionScoped
public class LoginBean implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The email. */
    private String email;

    /** The faces context. */
    @Inject
    private FacesContext facesContext;

    /** The request. */
    @Inject
    @HRequest
    private HttpServletRequest request;

    /** The response. */
    @Inject
    private HttpServletResponse response;

    /**
     * Execute.
     * @throws ServletException
     *             the servlet exception
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */
    public void execute() throws ServletException, IOException {
        final RequestDispatcher dispatcher = this.request
                .getRequestDispatcher("/j_spring_security_check");
        dispatcher.forward(this.request, this.response);

        this.facesContext.responseComplete();

    }

    /**
     * Pre render.
     */
    public void preRender() {
        if ("true".equals(this.request.getParameter("invalid"))) {
            FacesUtil.addErrorMessage("email or password invalid");
        }
    }

    /**
     * Gets the email.
     * @return the email
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Sets the email.
     * @param email
     *            the email to set
     */
    public void setEmail(final String email) {
        this.email = email;
    }

}

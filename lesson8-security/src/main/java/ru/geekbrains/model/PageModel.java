package ru.geekbrains.model;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;

@Named("page")
@RequestScoped
public class PageModel implements Serializable {

    @Inject
    private Service service;

    public void logout() throws IOException {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.invalidateSession();
        context.redirect(context.getRequestContextPath() + "/index.xhtml");
    }

    public void action() {
        try {
            service.action();
        } catch (Exception ex) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Error",  ex.getLocalizedMessage()));
        }
    }
}

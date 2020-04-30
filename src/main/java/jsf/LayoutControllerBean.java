package jsf;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named(value = "layout")
@ApplicationScoped
public class LayoutControllerBean {
    private boolean shouldShowSidebar = false;
    private boolean isLoading = false;

    public LayoutControllerBean() {
    }


    public void showSidebar() {
        this.setShouldShowSidebar(true);
    }


    public boolean isLoading() {
        return isLoading;
    }

    public void setLoading(boolean loading) {
        System.out.println(String.format("setting loading to %s", loading));
        isLoading = loading;
    }

    public void displayFacesMessage(String title, String description, FacesMessage.Severity severity) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(severity, title, description));
    }

    public void ensureSidebarHidden() {
        this.setShouldShowSidebar(false);
    }

    public boolean isShouldShowSidebar() {
        return shouldShowSidebar;
    }

    public void setShouldShowSidebar(boolean shouldShowSidebar) {
        this.shouldShowSidebar = shouldShowSidebar;
    }
}

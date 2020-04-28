package jsf;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@Named(value = "layout")
@ApplicationScoped
public class LayoutControllerBean {
    private boolean shouldShowSidebar = false;

    public LayoutControllerBean() {
    }


    public void showSidebar() {
        this.setShouldShowSidebar(true);
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

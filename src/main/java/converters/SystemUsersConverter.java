package converters;

import dao.systemuser.SystemUserDao;
import dto.SystemUserDto;
import ejb.UserService;
import jsf.TransferMoneyBean;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import java.io.Serializable;


@FacesConverter(forClass = SystemUserDto.class)
public class SystemUsersConverter implements Converter<SystemUserDto>, Serializable {
    @Override
    public SystemUserDto getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        // Get Bean that holds our DAO
        TransferMoneyBean moneyBean = facesContext.getApplication()
                .evaluateExpressionGet(facesContext, "#{transfer}", TransferMoneyBean.class);

        UserService service = moneyBean.getUserService();

        if (s == null || s.isEmpty()) {
            return null;
        }
        try {
            return service.findUser(Long.parseLong(s));
        } catch (NumberFormatException e) {
            throw new ConverterException(new FacesMessage(s + " is not a valid System user!"));
        }
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, SystemUserDto systemUserDto) {
        if (systemUserDto == null) {
            return "";
        }
        System.out.println(systemUserDto.toString());
        return systemUserDto.toString();
    }
}

package rw.ac.auca.photostudiomanagementsystem.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("notBlankValidator")
public class NotBlankValidator implements Validator {
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) {
        if (value == null || value.toString().trim().isEmpty()) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "This field cannot be blank or just spaces.", null));
        }
    }
}
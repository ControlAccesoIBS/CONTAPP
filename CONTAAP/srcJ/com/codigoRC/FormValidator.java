package com.codigoRC;

import java.util.Map;

import org.zkoss.bind.Property;
import org.zkoss.bind.ValidationContext;
import org.zkoss.bind.validator.AbstractValidator;


public class FormValidator extends AbstractValidator {

	@Override
	public void validate(ValidationContext ctx) {
		// TODO Auto-generated method stub
		 Map<String,Property> beanProps = ctx.getProperties(ctx.getProperty().getBase());
         
	        //first let's check the passwords match
	        validatePasswords(ctx, (String)beanProps.get("password").getValue(), (String)ctx.getValidatorArg("c_password"));
	}

	private void validatePasswords(ValidationContext ctx, String password, String c_password) {
		if(password == null || c_password == null || (!password.equals(c_password))) {
            this.addInvalidMessage(ctx, "password", "Las contraseñas ingresadas no coinciden");
        }
	}
	

}

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
	        validateCaptcha(ctx, (String)ctx.getValidatorArg("captcha"), (String)ctx.getValidatorArg("captchaInput"));
	}

	private void validatePasswords(ValidationContext ctx, String password, String c_password) {
		if(password == null || c_password == null || (!password.equals(c_password))) {
            this.addInvalidMessage(ctx, "password", "Las contraseñas ingresadas no coinciden o estan en blanco");
        }
	}
	private void validateCaptcha(ValidationContext ctx, String captcha, String captchaInput) {
        if(captchaInput == null || !captcha.equals(captchaInput)) {
            this.addInvalidMessage(ctx, "captcha", "Captcha invalido");
        }
    }
	

}

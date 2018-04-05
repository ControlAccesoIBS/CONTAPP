package com.codigoRC;

public class UserForm {
	private RandomStringGenerator rsg = new RandomStringGenerator(4);
	private ModeloRC userCpass = new ModeloRC();
	private String c_password;
	private String captcha = rsg.getRandomString(), captchaInput;
	
	public String getC_password() {
		return c_password;
	}

	public void setC_password(String c_password) {
		this.c_password = c_password;
	}

	public ModeloRC getUserCpass() {
		return userCpass;
	}

	public String getCaptcha() {
		return captcha;
	}

	public String getCaptchaInput() {
		return captchaInput;
	}

	public void setUserCpass(ModeloRC userCpass) {
		this.userCpass = userCpass;
	}

	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}

	public void setCaptchaInput(String captchaInput) {
		this.captchaInput = captchaInput;
	}

	public void regenerateCaptcha() {
		this.captcha = rsg.getRandomString();
	}

}

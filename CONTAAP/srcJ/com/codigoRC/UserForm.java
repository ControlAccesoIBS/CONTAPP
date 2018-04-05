package com.codigoRC;

public class UserForm {
	private RandomStringGenerator rsg = new RandomStringGenerator(4);
	private ModeloRC userCpass = new ModeloRC();
	private String captcha = rsg.getRandomString(), captchaInput;
	
	
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
	
}

package com.codigoRC;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;



import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.event.InputEvent;

public class FormViewModel extends UserForm {
	
	@Command
	@NotifyChange("captcha")
	public void regenerate() {
		this.regenerateCaptcha();
	}

	@Command
	public void submit() {
	}

}

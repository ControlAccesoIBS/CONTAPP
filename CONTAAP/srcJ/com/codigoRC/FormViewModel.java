package com.codigoRC;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;

public class FormViewModel extends UserForm {
	@Command
    @NotifyChange("captcha")
    public void regenerate() {
        this.regenerate();
    }

	
 
}

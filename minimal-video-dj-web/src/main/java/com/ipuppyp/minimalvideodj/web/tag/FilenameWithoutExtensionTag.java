package com.ipuppyp.minimalvideodj.web.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.google.common.io.Files;

public class FilenameWithoutExtensionTag extends SimpleTagSupport {
	private String fileNameWithoutExtension;
	


	public void doTag() throws JspException, IOException {
		getJspContext().getOut().print(fileNameWithoutExtension);
	}

	public void setFileName(String fileName) {
		this.fileNameWithoutExtension = Files.getNameWithoutExtension(fileName);
	}
	
}

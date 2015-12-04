package com.ipuppyp.minimalvideodj.web.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.google.common.base.Preconditions;

public class NormalizeFileNameTag extends SimpleTagSupport {
	private String normalizedFileName;
	
	public void doTag() throws JspException, IOException {
		getJspContext().getOut().print(normalizedFileName);
	}

	public void setFileName(String fileName) {
		Preconditions.checkNotNull(fileName);
		this.normalizedFileName = fileName.replaceAll(" |\\.", "_");
	}
	
}

package com.cyb.jstl;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class IfTag extends SimpleTagSupport {
    private boolean test;
    public void setTest(boolean test) {
              this.test = test;
    }

    @Override

    public void doTag() throws JspException, IOException {

              if(test)//如果有EL表达式就会先执行
                       this.getJspBody().invoke(null);
              else
                       super.doTag();         
    }
}

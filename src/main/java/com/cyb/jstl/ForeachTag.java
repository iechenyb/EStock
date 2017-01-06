package com.cyb.jstl;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class ForeachTag extends SimpleTagSupport {
    // 标签属性
    private String items;
    private String var;
   //这里省略 getter setter方法

    @Override
    public void doTag() throws JspException, IOException {
        Iterator ite = null;
        Object tempItem = getJspContext().getAttribute(items);
        // 如果是集合
        if (tempItem instanceof Collection) {
            ite = ((Collection) tempItem).iterator();
        }
        // 如果是数组
        else if (tempItem instanceof Object[]) {
            ite = Arrays.asList((Object[]) tempItem).iterator();
        } else {
            throw new RuntimeException("不能转换");
        }
        // 进行迭代
        while (ite.hasNext()) {
            Object obj = ite.next();
            getJspContext().setAttribute(var, obj);
            //输出标签体
            getJspBody().invoke(null);
        }
    }

	public String getItems() {
		return items;
	}

	public void setItems(String items) {
		this.items = items;
	}

	public String getVar() {
		return var;
	}

	public void setVar(String var) {
		this.var = var;
	}
}

package com.zwk.tool;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 * 正则控制
 */
public class MyDocument extends PlainDocument {
	private Pattern pattern;
	private Matcher m;
	public MyDocument(String pat)
	{
		super();
		this.pattern=Pattern.compile(pat);
	}
	@Override
	public void insertString
			(int offset, String str, AttributeSet attr)
			throws BadLocationException {
		if (str == null){
			return;
		}
		String tmp=getText(0, offset).concat(str);
		m=pattern.matcher(tmp);
		if(m.matches())
			super.insertString(offset, str, attr);
	}
}

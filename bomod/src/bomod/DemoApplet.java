package bomod;

import java.applet.Applet;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.util.HashMap;

public abstract class DemoApplet extends Applet implements ActionListener, KeyListener {

	private static final long serialVersionUID = 1L;
	
	protected HashMap<String,Object> parameters = new HashMap<String,Object>();
	
	public static final String PLAY_DELAY_PARAM = "playdelay";
	public static final String BACKGROUND_COLOR_PARAM = "backgroundcolor";
	public static final String CONSOLE_BACKGROUND_COLOR_PARAM = "consolebackgroundcolor";
	public static final String CONSOLE_TEXT_COLOR_PARAM = "consoletextcolor";
	public static final String MEMORY_DEFAULT_FORGROUND_COLOR_PARAM = "memorydefaultforgroundcolor";
	public static final String MEMORY_DEFAULT_BACKGROUND_COLOR_PARAM = "memorydefaultbackgroundcolor";
	public static final String HINT_TEXT_COLOR_PARAM = "hinttextcolor";
	public static final String DEFAULT_CODE_COLOR_PARAM = "defaultcodecolor";
	public static final String CODE_COLOR1_PARAM = "codecolor1";
	public static final String CODE_COLOR2_PARAM = "codecolor2";
	public static final String CODE_COLOR3_PARAM = "codecolor3";
	public static final String CODE_COLOR4_PARAM = "codecolor4";
	public static final String STACK_CONTENTS_COLOR_PARAM = "stackcontentscolor";
	public static final String RETURN_POINTER_COLOR_PARAM = "returnpointercolor";
	
	public DemoApplet(){
		// default settings
		setParameter(PLAY_DELAY_PARAM, new Integer(2750));
		setParameter(BACKGROUND_COLOR_PARAM, new Color(0, 0, 128));
		setParameter(HINT_TEXT_COLOR_PARAM, Color.WHITE);
		setParameter(CONSOLE_BACKGROUND_COLOR_PARAM, Color.GRAY);
		setParameter(MEMORY_DEFAULT_FORGROUND_COLOR_PARAM, Color.WHITE);
		setParameter(MEMORY_DEFAULT_BACKGROUND_COLOR_PARAM, Color.GRAY);
		setParameter(CONSOLE_TEXT_COLOR_PARAM, Color.WHITE);
		setParameter(DEFAULT_CODE_COLOR_PARAM, Color.WHITE);
		setParameter(CODE_COLOR1_PARAM, new Color(255, 255, 0));
		setParameter(CODE_COLOR2_PARAM, new Color(128, 255, 64));
		setParameter(CODE_COLOR3_PARAM, new Color(255, 0, 255));
		setParameter(CODE_COLOR4_PARAM, new Color(0, 255, 255));
		setParameter(STACK_CONTENTS_COLOR_PARAM, Color.BLACK);
		setParameter(RETURN_POINTER_COLOR_PARAM, Color.BLACK);
	}
	
	public void setParameter(String name, Object value){
		parameters.put(name, value);
	}
	
	public void init() {
		try {
			if(this.getParameter(PLAY_DELAY_PARAM) != null){
				setParameter(PLAY_DELAY_PARAM, this.getParameter(PLAY_DELAY_PARAM));
			}
			
			String backgroundColorParam = new String(this.getParameter(BACKGROUND_COLOR_PARAM));
			setParameter(BACKGROUND_COLOR_PARAM, new Color(Integer.parseInt(backgroundColorParam.substring(0, 2), 16), 
													  	   Integer.parseInt(backgroundColorParam.substring(2, 4), 16), 
													  	   Integer.parseInt(backgroundColorParam.substring(4, 6), 16)));

			String memoryDefaultBackgroundColorParam = new String(this.getParameter(MEMORY_DEFAULT_BACKGROUND_COLOR_PARAM));
			setParameter(MEMORY_DEFAULT_BACKGROUND_COLOR_PARAM, new Color(Integer.parseInt(memoryDefaultBackgroundColorParam.substring(0, 2), 16), 
													  	   Integer.parseInt(memoryDefaultBackgroundColorParam.substring(2, 4), 16), 
													  	   Integer.parseInt(memoryDefaultBackgroundColorParam.substring(4, 6), 16)));
			
			String memoryDefaultForgroundColorParam = new String(this.getParameter(MEMORY_DEFAULT_FORGROUND_COLOR_PARAM));
			setParameter(MEMORY_DEFAULT_FORGROUND_COLOR_PARAM, new Color(Integer.parseInt(memoryDefaultForgroundColorParam.substring(0, 2), 16), 
													  	   Integer.parseInt(memoryDefaultForgroundColorParam.substring(2, 4), 16), 
													  	   Integer.parseInt(memoryDefaultForgroundColorParam.substring(4, 6), 16)));
			
			String consoleBackgroundColorParam = new String(this.getParameter(CONSOLE_BACKGROUND_COLOR_PARAM));
			setParameter(CONSOLE_BACKGROUND_COLOR_PARAM, new Color(Integer.parseInt(consoleBackgroundColorParam.substring(0, 2), 16), 
													  	   Integer.parseInt(consoleBackgroundColorParam.substring(2, 4), 16), 
													  	   Integer.parseInt(consoleBackgroundColorParam.substring(4, 6), 16)));
			
			String consoleTextColorParam = new String(this.getParameter(CONSOLE_TEXT_COLOR_PARAM));
			setParameter(CONSOLE_TEXT_COLOR_PARAM, new Color(Integer.parseInt(consoleTextColorParam.substring(0, 2), 16), 
													  	   Integer.parseInt(consoleTextColorParam.substring(2, 4), 16), 
													  	   Integer.parseInt(consoleTextColorParam.substring(4, 6), 16)));
			
			String hintTextColorParam = new String(this.getParameter(HINT_TEXT_COLOR_PARAM));
			setParameter(HINT_TEXT_COLOR_PARAM, new Color(Integer.parseInt(hintTextColorParam.substring(0, 2), 16), 
													  	   Integer.parseInt(hintTextColorParam.substring(2, 4), 16), 
													  	   Integer.parseInt(hintTextColorParam.substring(4, 6), 16)));
			
			String defaultCodeColorParam = new String(this.getParameter(DEFAULT_CODE_COLOR_PARAM));
			setParameter(DEFAULT_CODE_COLOR_PARAM, new Color(Integer.parseInt(defaultCodeColorParam.substring(0, 2), 16), 
												 	  Integer.parseInt(defaultCodeColorParam.substring(2, 4), 16), 
												 	  Integer.parseInt(defaultCodeColorParam.substring(4, 6), 16)));
			
			String codeColor1Param = new String(this.getParameter(CODE_COLOR1_PARAM));
			setParameter(CODE_COLOR1_PARAM, new Color(Integer.parseInt(codeColor1Param.substring(0, 2), 16), 
												 	  Integer.parseInt(codeColor1Param.substring(2, 4), 16), 
												 	  Integer.parseInt(codeColor1Param.substring(4, 6), 16)));
			
			String codeColor2Param = new String(this.getParameter(CODE_COLOR2_PARAM));
			setParameter(CODE_COLOR2_PARAM, new Color(Integer.parseInt(codeColor2Param.substring(0, 2), 16), 
											     	 	Integer.parseInt(codeColor2Param.substring(2, 4), 16), 
											     	 	Integer.parseInt(codeColor2Param.substring(4, 6), 16)));

			String codeColor3Param = new String(this.getParameter(CODE_COLOR3_PARAM));
			setParameter(CODE_COLOR3_PARAM, new Color(Integer.parseInt(codeColor3Param.substring(0, 2), 16), 
													  Integer.parseInt(codeColor3Param.substring(2, 4), 16), 
													  Integer.parseInt(codeColor3Param.substring(4, 6), 16)));

			String codeColor4Param = new String(this.getParameter(CODE_COLOR4_PARAM));
			setParameter(CODE_COLOR4_PARAM, new Color(Integer.parseInt(codeColor4Param.substring(0, 2), 16), 
											     	  Integer.parseInt(codeColor4Param.substring(2, 4), 16), 
											     	  Integer.parseInt(codeColor4Param.substring(4, 6), 16)));

			String stackContentsColorParam = new String(this.getParameter(STACK_CONTENTS_COLOR_PARAM));
			setParameter(STACK_CONTENTS_COLOR_PARAM, new Color(Integer.parseInt(stackContentsColorParam.substring(0, 2), 16), 
														 Integer.parseInt(stackContentsColorParam.substring(2, 4), 16), 
												         Integer.parseInt(stackContentsColorParam.substring(4, 6), 16)));

			String returnPointerColorParam = new String(this.getParameter(RETURN_POINTER_COLOR_PARAM));
			setParameter(RETURN_POINTER_COLOR_PARAM, new Color(Integer.parseInt(returnPointerColorParam.substring(0, 2), 16), 
														 Integer.parseInt(returnPointerColorParam.substring(2, 4), 16), 
												         Integer.parseInt(returnPointerColorParam.substring(4, 6), 16)));
		} catch (Exception e) {
			// just use the default settings
		}
	}

}

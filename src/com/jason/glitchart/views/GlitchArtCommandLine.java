package com.jason.glitchart.views;

import com.jason.glitchart.*;

/**
 * Created with IntelliJ IDEA.
 * User: jason
 * Date: 11/22/13
 * Time: 1:05 PM
 * To change this template use File | Settings | File Templates.
 */
public class GlitchArtCommandLine {

	public static GlitchArtCommandLine instance = null;

	//zero arg constructor
	protected GlitchArtCommandLine() { }

	public static GlitchArtCommandLine getInstance(){
		if (instance == null)
			instance = new GlitchArtCommandLine();

		return instance;

	}

	public void handleArgs(String file, int effect){

	}


}

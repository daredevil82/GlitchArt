/**
*	Author: Jason
*	Date : Nov 21, 2013
*	Description : 
*/

package com.jason.glitchart;

import org.apache.commons.cli.*;

import com.jason.glitchart.views.*;


public class GlitchArt {

	private static GlitchArt instance = null;

	//implement singleton class
	public static GlitchArt getInstance(){

		if (instance == null)
			instance = new GlitchArt();

		return instance;

	}

	//zero arg constructor with limited visiblity to support
	//singleton class
	protected GlitchArt(){}

	public static void main(String[] args) {

		GlitchArt ga = GlitchArt.getInstance();

		Options glitchOptions = setupOptions();

		CommandLineParser parse = new BasicParser();

		try {
			CommandLine cmd = parse.parse(glitchOptions, args);
			parseOptions(cmd, glitchOptions);
		} catch (ParseException e){
			System.err.println("Parsing exception occurred.  Stacktrace: " + e.getMessage());
		}

	}


	//Parse the command line input using an if-else conditional tree
	private static void parseOptions(CommandLine cmd, Options opts){

		if (cmd.hasOption("h") || cmd.hasOption("help")){
			HelpFormatter fmt = new HelpFormatter();
			fmt.printHelp("GlitchArt", opts);
		}

		else {
			if (cmd.hasOption("g") || cmd.hasOption("gui")) {

				System.out.println("Graphical user interface not implemented yet\nUse the CLI interface for now.\nExiting...");
				System.exit(2);

			} else if (cmd.hasOption("f")){

				int effect = 0;

				if (cmd.hasOption("m")){

					System.out.println("No mode provided, using default Dark effect");

				} else {
					try {

						effect = Integer.parseInt(cmd.getOptionValue("m"));

					} catch (NumberFormatException e){
						System.err.println("Invalid mode provided.  Acceptable entries are [0, 1, 2]");
						System.exit(2);
					}
				}


				GlitchArtCommandLine.getInstance().handleArgs(cmd.getOptionValue("f"), effect);

			}
		}

	}

	private static Options setupOptions(){

		final Options glitchOptions = new Options();
		glitchOptions.addOption("h", "help", false, "Print this help screen")     ;
		glitchOptions.addOption("g", "gui", false, "Use GUI with program");
		glitchOptions.addOption("f", true, "Image file to be filtered");
		glitchOptions.addOption("m", true, "Image Mode\n\n\t0 - Black\n\t1 - Bright\n\tWhite");

		return glitchOptions;

	}

}


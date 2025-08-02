package br.ufba.pos;

import br.ufba.pos.executors.ObjectExecutor;

import java.util.List;

public class Helper {

	/**
	 * Displays usage information and valid arguments for the program.
	 * This method provides an overview of how to execute the program and
	 * the command-line arguments that can be used. It prints a formatted
	 * string listing all valid options and their expected behavior.
	 *
	 * Example output includes:
	 * - General usage description with the command to execute the program.
	 * - A detailed list of valid arguments, including problem-specific
	 *   flags (-p1, -p2, etc.) and optional parameters.
	 */
	public static void help() {
		System.out.println("Usage: java -jar algorithms_pss.jar [args]");
		String validArgs = """
        Valid Args:
            --help | -h			Displays this help message.
            --all      			Executes all solutions within the package "br.ufba.pos.problems".
            --problems -p<num> [--help | -h] | <[param1] [param2] ...> 
            					Executes specific solutions within the package "br.ufba.pos.problems".
            					passing flags like "-p1", "-p2", etc. to specify which solutions to execute.
            					You can pass multiple flags to execute multiple solutions.
            				
        """;
		System.out.println(validArgs);
	}

	/**
	 * Processes command-line arguments to identify and handle specific flags related to problems and help commands.
	 * For each problem flag ("-p" followed by a number) paired with a subsequent help flag ("--help" or "-h"),
	 * this method looks for the corresponding problem class and prints its help information.
	 * If any help command is detected, the system exits after processing.
	 *
	 * @param args an array of command-line arguments provided to the program. It includes problem flags ("-p")
	 *             and optional help flags ("--help" or "-h").
	 * @param allClassesUsingClassLoader a list of classes loaded via the application's class loader. These classes
	 *                                   are searched to find classes named in the format "ProblemX" where X corresponds
	 *                                   to the number extracted from each "-p" flag.
	 */
	public static void checkHelpPFlags(String[] args, List<Class<?>> allClassesUsingClassLoader) {
		Boolean setExit = false;
		for(int i = 1; i < args.length; i++) {
			try {
				String pFlag = args[i];
				String helpFlag = args[i+1];
				if(pFlag.startsWith("-p") && (helpFlag.startsWith("--help") || helpFlag.startsWith("-h"))) {
					if(setExit == false) {
						setExit = true;
					}
					String pNumber = pFlag.replaceAll("[^0-9]", "");
					allClassesUsingClassLoader.stream()
							.filter(arg -> arg.getSimpleName().equals("Problem" + pNumber))
							.findFirst()
							.ifPresent(clazz -> System.out.println(ObjectExecutor.getObjectProblem(clazz).problemHelp()));
				}
			} catch (ArrayIndexOutOfBoundsException e) {

			}
		}
		if(setExit != null && setExit) {
			System.exit(0);
		}
	}
}

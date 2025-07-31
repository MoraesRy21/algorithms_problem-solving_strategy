package br.ufba.pos.readers;

import org.apache.commons.lang3.math.NumberUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class ArgumentReader {

	/**
	 * Extracts and filters command-line arguments that start with the "-p" prefix.
	 * If the `withoutMinus` parameter is true, the returned arguments will have the
	 * leading dash ("-") removed; otherwise, the arguments are returned as-is.
	 *
	 * @param args the array of command-line arguments to filter.
	 * @param withoutMinus a boolean indicating whether the resulting arguments
	 *                     should exclude the leading dash ("-").
	 * @return an array of filtered arguments starting with "-p". If `withoutMinus`
	 *         is true, the arguments will have the leading dash removed. If no
	 *         matching arguments are found, an empty array is returned.
	 */
	public static String[] getPFlags(String[] args, boolean withoutMinus) {
		Stream<String> stream = Arrays.stream(args)
				.filter(arg -> arg.startsWith("-p"));
		return withoutMinus ? stream.map(arg -> arg.substring(1)).toArray(String[]::new) : stream.toArray(String[]::new);
	}

	/**
	 * Extracts a set of parameters from the given command-line arguments, starting
	 * from the specified index. The extraction continues until an argument starting
	 * with the prefix "-p" is encountered. Each extracted argument is evaluated to
	 * determine if it is a numerical value; if so, it is cast to an Integer, otherwise
	 * it remains as a String.
	 *
	 * @param args the array of command-line arguments from which parameters will be extracted.
	 * @param i the index in the array `args` from where the parameter extraction will begin, moving forward.
	 * @return an array of Objects where numerical arguments are represented as Integers and
	 *         other arguments are Strings. The extraction stops when an argument starting with "-p" is encountered.
	 */
	public static Object[] getPFlagParameters(String[] args, int i) {
		return Arrays.stream(args, i+1, args.length)
				.takeWhile(arg -> !arg.startsWith("-p"))
				.map(arg -> NumberUtils.isCreatable(arg) ? NumberUtils.createInteger(arg) : arg)
				.toArray(Object[]::new);
	}

	/**
	 * Constructs a map where each key represents a class name derived from command-line arguments
	 * with the prefix "-p". The associated value is an array of parameters extracted from the subsequent
	 * arguments until another "-p" argument is encountered.
	 *
	 * @param args the array of command-line arguments used to construct the map. Arguments prefixed
	 *             with "-p" indicate a problem identifier, and subsequent arguments are associated
	 *             as parameters for that identifier.
	 * @return a map where the keys are strings representing class names in the format "ProblemX",
	 *         and the values are arrays of extracted parameters. Parameters are typed as either
	 *         Integer for numeric values or String otherwise.
	 */
	public static Map<String, Object[]> getMapParametersFlag(String[] args) {
		Map<String, Object[]> mapParams = new HashMap<>();
		for(int i = 1; i < args.length; i++) {
			String pFlag = args[i];
			if(pFlag.startsWith("-p")) {
				String pNumber = pFlag.replaceAll("[^0-9]", "");
				String className = "Problem" + pNumber;
				Object[] params = getPFlagParameters(args, i);
				mapParams.put(className, params);
			}
		}
		return mapParams;
	}
}

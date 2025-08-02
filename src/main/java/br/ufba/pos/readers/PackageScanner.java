package br.ufba.pos.readers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PackageScanner {

	/**
	 * Searches for classes within subpackages of a given base package that follow a specific naming convention.
	 * Subpackages are expected to be named in the format "p<number>", and only subpackages matching this format
	 * are considered. Optionally, a subset of specific subpackage names can be specified for inclusion.
	 *
	 * @param basePackage the base package to start the search from, in dotted notation (e.g., "com.example").
	 * @param pFlags an optional list of subpackage names (e.g., "p1", "p2") to restrict the search to specific subpackages.
	 *               If omitted or empty, all subpackages matching the "p<number>" format will be scanned.
	 * @return a list of {@code Class<?>} objects representing the classes found within the specified subpackages.
	 *         If no classes are found, an empty list is returned.
	 */
	public static List<Class<?>> findClassesInPNumberedSubpackages(String basePackage, String... pFlags) {
		// Converts package name to path on file system
		String packagePath = basePackage.replace('.', '/');
		List<Class<?>> classes = new ArrayList<>();
		List<String> pParams = !Objects.isNull(pFlags) && pFlags.length > 0 ? Arrays.asList(pFlags) : null;

		try {
			Enumeration<URL> resources = Thread.currentThread().getContextClassLoader().getResources(packagePath);
			while (resources.hasMoreElements()) {
				URL resource = resources.nextElement();
				if (resource.getProtocol().equals("file")) {
					File directory = new File(resource.getFile());
					if (directory.exists()) {
						findClassesFromDirectory(directory, classes, basePackage, pParams);
					}
				} else if (resource.getProtocol().equals("jar")) {
					String jarPath = resource.getPath().substring(5, resource.getPath().indexOf("!"));
					findClassesFromJar(classes, jarPath, packagePath, pParams);
				}
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		return classes;
	}

	/**
	 * Searches for classes located in a specified directory and subdirectories that match
	 * a specific subpackage format and collects them into a list. The subdirectories to
	 * be scanned are filtered based on specific naming conventions and optional input
	 * parameter restrictions.
	 *
	 * @param packageDirectory the root directory to scan for subdirectories and classes.
	 * @param classes the list where the discovered {@code Class<?>} objects will be added.
	 * @param basePackage the base package name in dotted notation (e.g., "com.example").
	 * @param pParams a list of specific subpackage names (e.g., `p1`, `p2`, etc.) to restrict
	 *                the scan only to these subpackages. If {@code null} or empty, all
	 *                subpackages matching the format `p<number>` will be scanned.
	 */
	private static void findClassesFromDirectory(File packageDirectory, List<Class<?>> classes, String basePackage, List<String> pParams) {
		// Gets the classloader directory corresponding to the package
		if (packageDirectory.exists()) {
			for (File subDirectory : packageDirectory.listFiles()) {
				// Checks if it is a subpackage in the format p<number>
				if (subDirectory.isDirectory() && subDirectory.getName().matches("p\\d+")) {
					if(pParams != null && !pParams.isEmpty() && !pParams.contains(subDirectory.getName())) {
						continue;
					}
					// Search for classes within the subdirectory
					String subPackage = basePackage + "." + subDirectory.getName();
					classes.addAll(findClassesInDirectory(subDirectory, subPackage, "Problem\\d+\\.class"));
				}
			}
		}
	}

	/**
	 * Finds and retrieves a list of classes from the specified directory that match the given package
	 * name and class name pattern. This method scans through all files in the directory, identifies
	 * files with `.class` extensions, and filters them based on the provided regular expression
	 * for class names. It then attempts to load these classes dynamically.
	 *
	 * @param directory the directory containing the `.class` files to be scanned
	 * @param packageName the Java package name of the classes in dotted notation (e.g., "com.example")
	 * @param regexClassName a regular expression used to filter the class file names (e.g., "Problem\\d+\\.class")
	 * @return a list of {@code Class<?>} objects representing the classes found in the directory
	 *         that match the package name and file name pattern. If no classes match, an empty list is returned.
	 */
	private static List<Class<?>> findClassesInDirectory(File directory, String packageName, String regexClassName) {
		List<Class<?>> classes = new ArrayList<>();
		for (File file : directory.listFiles()) {
			if (file.isFile() && file.getName().endsWith(".class")) {
				try {
					if(!file.getName().matches(regexClassName)) {
						continue;
					}
					String className = packageName + "." + file.getName().replace(".class", "");
					classes.add(Class.forName(className));
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		}
		return classes;
	}

	/**
	 * Scans a JAR file to find and load classes within a specified package path that match
	 * certain filtering criteria based on provided flags. The discovered class definitions
	 * are added to the given list.
	 *
	 * @param classes the list where the identified classes will be added.
	 * @param jarPath the full path to the JAR file to be scanned.
	 * @param packagePath the package path (in directory notation) used as the base for filtering class entries.
	 * @param pParams a list of specific subpackage names (e.g., `p1`, `p2`, etc.) to restrict
	 * 	 *                the scan only to these subpackages. If {@code null} or empty, all
	 * 	 *                subpackages matching the format `p<number>` will be scanned.
	 */
	public static void findClassesFromJar(List<Class<?>> classes, String jarPath, String packagePath, List<String> pParams) {
		String pDirRegex = packagePath + "/p\\d+/Problem\\d+\\.class";

		try (JarFile jarFile = new JarFile(jarPath)) {
			Enumeration<JarEntry> entries = jarFile.entries();

			while (entries.hasMoreElements()) {
				JarEntry entry = entries.nextElement();
				String entryName = entry.getName();

				if (entryName.matches(pDirRegex)) {
					String flag = extractPattern(entryName, "p\\d+");
					if(flag != null && (pParams == null || pParams.isEmpty() || pParams.contains(flag))) {
						try {
							String className = entryName.replace('/', '.').replace(".class", "");
							classes.add(Class.forName(className));
						} catch (ClassNotFoundException e) {
							throw new RuntimeException(e);
						}
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Extracts the first matching substring from the given input that conforms to the provided regular expression.
	 * If no match is found, the method returns null.
	 *
	 * @param input the string from which the pattern will be extracted
	 * @param regex the regular expression defining the pattern to be extracted
	 * @return the first substring from the input string that matches the given regex, or null if no match is found
	 */
	public static String extractPattern(String input, String regex) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(input);

		if (matcher.find()) {
			String extraction = matcher.group();
			return extraction;
		}
		System.out.println("No match found.");
		return null;
	}

}

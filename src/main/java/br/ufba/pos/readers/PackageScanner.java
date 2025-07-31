package br.ufba.pos.readers;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class PackageScanner {

	/**
	 * Scans the subpackages of a given base package and retrieves a list of classes
	 * found within subpackages named in the format `p<number>` (e.g., `p1`, `p2`).
	 * The method also filters the subpackages by specific names, if provided, and
	 * includes only the classes matching the naming pattern `Problem<number>.class`.
	 *
	 * @param basePackage the root package in dotted notation (e.g., "com.example"),
	 *                    under which the method will look for subpackages.
	 * @param args an optional array of subpackage names (e.g., `p1`, `p2`, etc.)
	 *             to restrict the scan to these specific subpackages. If omitted
	 *             or empty, the method scans all `p<number>` subpackages.
	 * @return a list of {@code Class<?>} objects representing the discovered
	 *         classes in the targeted subpackages. If no classes are found,
	 *         an empty list is returned.
	 */
	public static List<Class<?>> findClassesInPNumberedSubpackages(String basePackage, String... args) {
		// Converte o nome do pacote para o caminho no sistema de arquivos
		String packagePath = basePackage.replace('.', '/');
		List<Class<?>> classes = new ArrayList<>();
		List<String> pParams = !Objects.isNull(args) && args.length > 0 ? Arrays.asList(args) : null;

		// Obtém o diretório do classloader correspondente ao pacote
		File directory = new File(ClassLoader.getSystemClassLoader().getResource(packagePath).getFile());
		if (directory.exists()) {
			for (File subDirectory : directory.listFiles()) {
				// Verifica se é um subpacote no formato p<number>
				if (subDirectory.isDirectory() && subDirectory.getName().matches("p\\d+")) {
					if(pParams != null && !pParams.isEmpty() && !pParams.contains(subDirectory.getName())) {
						continue;
					}
					// Busca as classes dentro do subdiretório
					String subPackage = basePackage + "." + subDirectory.getName();
					classes.addAll(findClassesInDirectory(subDirectory, subPackage, "Problem\\d+\\.class"));
				}
			}
		}
		return classes;
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

}

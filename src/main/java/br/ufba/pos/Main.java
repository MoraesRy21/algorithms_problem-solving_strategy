package br.ufba.pos;

import br.ufba.pos.executors.ObjectExecutor;
import br.ufba.pos.readers.ArgumentReader;
import br.ufba.pos.readers.PackageScanner;

import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        if(args.length == 0 || (args.length == 1 && (args[0].equals("--help") || !args[0].equals("--all")))) {
            Helper.help();
            System.exit(0);
        } else if(args.length == 1 && args[0].equals("--all")) {
            executeAllSolutions();
		} else if(args.length > 1 && args[0].equals("--problems")) {
            executeSpecificSolutions(args);
        } else {
            Helper.help();
        }
    }

    /**
     * Executes all solutions defined in the classes located within the package "br.ufba.pos.problems".
     * Each class in the package is expected to be a subclass of {@code ProblemCase} and should provide a
     * no-argument constructor. The method dynamically discovers all eligible classes, instantiates them,
     * and invokes their {@code executeTestSolutionQuestion} method.
     *
     * Upon encountering an exception during this process (e.g., missing required constructors,
     * invocation errors, or access issues), the method wraps the exception in a {@code RuntimeException}
     * and rethrows it.
     *
     * @throws RuntimeException if any error occurs during class discovery, instantiation, or method invocation
     */
    public static void executeAllSolutions() {
        List<Class<?>> allClassesUsingClassLoader = PackageScanner.findClassesInPNumberedSubpackages("br.ufba.pos.problems");
        for(Class<?> clazz : allClassesUsingClassLoader) {
            ObjectExecutor.invokeDefaultSolution(clazz);
        }
    }

    /**
     * Executes specific solutions defined in the classes located within the package "br.ufba.pos.problems".
     * The solutions to execute are determined by the program arguments provided.
     *
     * Each solution is expected to be represented by a class that is a subclass of {@code ProblemCase}
     * and should provide a no-argument constructor. The method dynamically discovers all eligible classes,
     * instantiates them, and invokes their {@code executeTestSolutionQuestion} method for the specified solutions.
     *
     * Upon encountering an exception during this process (e.g., missing required constructors,
     * invocation errors, or access issues), the method wraps the exception in a {@code RuntimeException} and rethrows it.
     *
     * @param args the program arguments specifying which solutions to execute. Each argument represents
     *             the identifier of a specific problem, such as "q1", "q2".
     * @throws RuntimeException if any error occurs during class discovery, instantiation, or method invocation
     */
    public static void executeSpecificSolutions(String[] args) {
        String[] pFlags = ArgumentReader.getPFlags(args, true);
        List<Class<?>> allClassesUsingClassLoader = PackageScanner.findClassesInPNumberedSubpackages("br.ufba.pos.problems", pFlags);
        Helper.checkHelpPFlags(args, allClassesUsingClassLoader);

        Map<String, Object[]> mapParams = ArgumentReader.getMapParametersFlag(args);

        for(Class<?> clazz : allClassesUsingClassLoader) {
            Object[] params = mapParams.get(clazz.getSimpleName());
            ObjectExecutor.invokeSolution(clazz, params);
        }
    }

}

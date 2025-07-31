package br.ufba.pos.executors;

import br.ufba.pos.problems.ProblemCase;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Objects;

public class ObjectExecutor {

	public static ProblemCase getObjectProblem(String className, Object... args) {
		try {
			Class<?> clazz = Class.forName(className);
			return getObjectProblem(clazz, args);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	public static ProblemCase getObjectProblem(Class<?> clazz, Object... args) {
		try {
			if(!Objects.isNull(args)) {
				Class<?>[] paramClasses = Arrays.stream(args).map(Object::getClass).toArray(Class<?>[]::new);
				Constructor<?> noArgConstructor = clazz.getConstructor(paramClasses);
				return (ProblemCase) noArgConstructor.newInstance(args);
			}
			Constructor<?> noArgConstructor = clazz.getConstructor();
			return (ProblemCase) noArgConstructor.newInstance();
		} catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

	public static void invokeDefaultSolution(Class<?> clazz) {
		ProblemCase problem = getObjectProblem(clazz);
		problem.executeTestSolutionQuestion();
	}

	public static void invokeSolution(Class<?> clazz, Object[] args) {
		ProblemCase problem = getObjectProblem(clazz, args);
		problem.executeTestSolutionQuestion();
	}

}

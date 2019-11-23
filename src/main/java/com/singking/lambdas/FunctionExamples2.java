package com.singking.lambdas;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * https://www.deadcoderising.com/2015-09-07-java-8-functional-composition-using-compose-and-andthen/
 *
 */
public class FunctionExamples2 {
	public void functionals() {
		Function<Integer, Integer> times2 = e -> e * 2;

		Function<Integer, Integer> squared = e -> e * e;

		Integer apply = times2.apply(10);
		System.out.println("apply (Returns 100): " + apply);

		// Next, let's combine them, using compose and andThen.
		Integer apply1 = times2.compose(squared).apply(4);
		System.out.println("apply (Returns 32): " + apply1);
		// Returns 32

		Integer apply2 = times2.andThen(squared).apply(4);
		System.out.println("apply (Returns 64): " + apply2);
		// Returns 64

		BiFunction<String, List<Article>, List<Article>> byAuthor = (name, articles) -> articles.stream()
				.filter(a -> a.getAuthor().equals(name)).collect(Collectors.toList());

		BiFunction<String, List<Article>, List<Article>> byTag = (tag, articles) -> articles.stream()
				.filter(a -> a.getTags().contains(tag)).collect(Collectors.toList());

		Function<Integer, String> intToString = Object::toString;
		Function<String, String> quote = s -> "'" + s + "'";

		Function<Integer, String> quoteIntToString = quote.compose(intToString);

		assertEquals("'5'", quoteIntToString.apply(5));

		// https://www.baeldung.com/java-8-functional-interfaces

		/*
		 * One of the usages of the Function type in the standard library is the
		 * Map.computeIfAbsent method that returns a value from a map by key but
		 * calculates a value if a key is not already present in a map. To
		 * calculate a value, it uses the passed Function implementation:
		 */
		Map<String, Integer> nameMap = new HashMap<>();
		Integer value = nameMap.computeIfAbsent("John", s -> s.length());

		/*
		 * A value, in this case, will be calculated by applying a function to a
		 * key, put inside a map and also returned from a method call. By the
		 * way, we may replace the lambda with a method reference that matches
		 * passed and returned value types.
		 *
		 * Remember that an object on which the method is invoked is, in fact,
		 * the implicit first argument of a method, which allows casting an
		 * instance method length reference to a Function interface:
		 *
		 */
		Integer value1 = nameMap.computeIfAbsent("John", String::length);

		Map<String, Integer> salaries = new HashMap<>();
		salaries.put("John", 40000);
		salaries.put("Freddy", 30000);
		salaries.put("Samuel", 50000);

		salaries.replaceAll((name, oldValue) -> name.equals("Freddy") ? oldValue : oldValue + 10000);

		Supplier<Double> lazyValue = () -> {
			Uninterruptibles.sleepUninterruptibly(1000, TimeUnit.MILLISECONDS);
			return 9d;
		};

		Map<String, Integer> ages = new HashMap<>();
		ages.put("John", 25);
		ages.put("Freddy", 24);
		ages.put("Samuel", 30);

		ages.forEach((name, age) -> System.out.println(name + " is " + age + " years old"));

		// Predicates
		List<String> names = Arrays.asList("Angela", "Aaron", "Bob", "Claire", "David");

		List<String> namesWithA = names.stream().filter(name -> name.startsWith("A")).collect(Collectors.toList());
		List<String> names1 = Arrays.asList("bob", "josh", "megan");

		names1.replaceAll(name -> name.toUpperCase());
		names1.replaceAll(String::toUpperCase);

		/*
		 * reduce One of the most interesting use cases of a BinaryOperator is a
		 * reduction operation. Suppose we want to aggregate a collection of
		 * integers in a sum of all values. With Stream API, we could do this
		 * using a collector, but a more generic way to do it is, would be to
		 * use the reduce method:
		 */
		List<Integer> values = Arrays.asList(3, 5, 8, 9, 12);
		int sum = values.stream().reduce(0, (i1, i2) -> i1 + i2);

		/*
		 * The reduce method receives an initial accumulator value and a
		 * BinaryOperator function. The arguments of this function are a pair of
		 * values of the same type, and a function itself contains a logic for
		 * joining them in a single value of the same type. Passed function must
		 * be associative, which means that the order of value aggregation does
		 * not matter, i.e. the following condition should hold:
		 */

		// op.apply(a, op.apply(b, c)) == op.apply(op.apply(a, b), c);

		/*
		 * Not all functional interfaces appeared in Java 8. Many interfaces
		 * from previous versions of Java conform to the constraints of a
		 * FunctionalInterface and can be used as lambdas. A prominent example
		 * is the Runnable and Callable interfaces that are used in concurrency
		 * APIs. In Java 8 these interfaces are also marked with
		 * a @FunctionalInterface annotation. This allows us to greatly simplify
		 * concurrency code:
		 */
		Thread thread = new Thread(() -> System.out.println("Hello From Another Thread"));
		thread.start();

		/*
		 * Primitive Function Specializations Since a primitive type can’t be a
		 * generic type argument, there are versions of the Function interface
		 * for most used primitive types double, int, long, and their
		 * combinations in argument and return types:
		 *
		 * IntFunction, LongFunction, DoubleFunction: arguments are of specified
		 * type, return type is parameterized ToIntFunction, ToLongFunction,
		 * ToDoubleFunction: return type is of specified type, arguments are
		 * parameterized DoubleToIntFunction, DoubleToLongFunction,
		 * IntToDoubleFunction, IntToLongFunction, LongToIntFunction,
		 * LongToDoubleFunction — having both argument and return type defined
		 * as primitive types, as specified by their names There is no
		 * out-of-the-box functional interface for, say, a function that takes a
		 * short and returns a byte, but nothing stops you from writing your
		 * own:
		 */
		short[] array = { (short) 1, (short) 2, (short) 3 };
		byte[] transformedArray = transformArray(array, s -> (byte) (s * 2));

		byte[] expectedArray = { (byte) 2, (byte) 4, (byte) 6 };
		assertArrayEquals(expectedArray, transformedArray);
	}

	/*
	 * Now we can write a method that transforms an array of short to an array
	 * of byte using a rule defined by a ShortToByteFunction:
	 */
	public byte[] transformArray(short[] array, ShortToByteFunction function) {
		byte[] transformedArray = new byte[array.length];
		for (int i = 0; i < array.length; i++) {
			transformedArray[i] = function.applyAsByte(array[i]);
		}
		return transformedArray;
	}

	/**
	 * returning a lambda expression
	 */
	public static Predicate<String> checkIfStartsWith(final String letter) {
		return name -> name.startsWith(letter);
	}

	public static void main(String[] args) {
		new FunctionExamples2().functionals();
	}

	public double squareLazy(Supplier<Double> lazyValue) {
		return Math.pow(lazyValue.get(), 2);
	}
}

class Article {
	String author;
	String tags;

	public Object getAuthor() {
		return author;
	}

	public String getTags() {
		// TODO Auto-generated method stub
		return tags;
	}

}

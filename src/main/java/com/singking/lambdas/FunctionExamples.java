package com.singking.lambdas;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntSupplier;
import java.util.function.LongFunction;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.ToLongBiFunction;

public class FunctionExamples {
	public void hhh() {

		/**
		 * returning a la,bda expression from a lambda expressioN
		 */
		final Function<String, Predicate<String>> startsWith = letter -> name -> name.startsWith(letter);
		System.out.println(startsWith.apply("l"));

		LongFunction<String> i = (l) -> Long.toString(l);
		System.out.println(i.apply(Long.MAX_VALUE));

		System.out.println();
		/**
		 * using a constructor method
		 */
		Supplier<Heavy> supplier = () -> new Heavy();
		System.out.println("heavy object: " + supplier.get().toString());

		System.out.println();

		Predicate<Sound> isBark = sound -> Sound.valueOf("bark").equals(sound);
		System.out.println("isBark: " + isBark.test(Sound.tweet));
		System.out.println();

		Consumer<String> c = (x) -> System.out.println(x.toLowerCase());
		c.accept("consumer lambda is printing this");

		System.out.println();

		/**
		 * http://www.java2s.com/Tutorials/Java/java.util.function/IntSupplier/index.htm
		 *
		 *
		 */
		BiConsumer<String, String> biConsumer = (x, y) -> {
			System.out.print(x);
			System.out.print(" | ");
			System.out.println(y);
		};
		biConsumer.accept("now two parameters", "can be passed to the bi-irectional connsumer");

		System.out.println();

		BiFunction<Integer, Integer, String> biFunction = (num1, num2) -> "Result:" + (num1 + num2);
		System.out.println(biFunction.apply(20, 25));

		System.out.println();

		BinaryOperator<Integer> adder = (n1, n2) -> n1 + n2;
		System.out.println(adder.apply(3, 4));

		System.out.println();

		IntSupplier index = () -> Integer.MAX_VALUE;
		System.out.println(index.getAsInt());

		ToLongBiFunction<String, String> index2 = (x, y) -> Long.parseLong(x) + Long.parseLong(y);
		System.out.println(index2.applyAsLong("2", "2"));
	}

	/**
	 * returning a lambda expression
	 */
	public static Predicate<String> checkIfStartsWith(final String letter) {
		return name -> name.startsWith(letter);
	}

	public static void main(String[] args) {
		new FunctionExamples().hhh();
	}
}

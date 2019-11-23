package com.singking.lambdas.collect;

import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * https://winterbe.com/posts/2014/07/31/java8-stream-tutorial-examples/
 *
 * Collect# Collect is an extremely useful terminal operation to transform the
 * elements of the stream into a different kind of result, e.g. a List, Set or
 * Map. Collect accepts a Collector which consists of four different operations:
 * a supplier, an accumulator, a combiner and a finisher. This sounds super
 * complicated at first, but the good part is Java 8 supports various built-in
 * collectors via the Collectors class. So for the most common operations you
 * don't have to implement a collector yourself.
 *
 * @author kingt
 *
 */
public class MainPersonsCollect {
	public static void main(String[] args) {

		List<Person> persons = Person.getPersons();

		List<Person> filtered = persons.stream().filter(p -> p.name.startsWith("P")).collect(Collectors.toList());
		System.out.println(filtered); // [Peter, Pamela]

		Map<Integer, List<Person>> personsByAge = persons.stream().collect(Collectors.groupingBy(p -> p.age));
		// age 18: [Max]
		// age 23: [Peter, Pamela]
		// age 12: [David]
		personsByAge.forEach((age, p) -> System.out.format("age %s: %s\n", age, p));

		System.out.println();

		/**
		 * If you're interested in more comprehensive statistics, the
		 * summarizing collectors return a special built-in summary statistics
		 * object. So we can simply determine min, max and arithmetic average
		 * age of the persons as well as the sum and count.
		 */
		Double averageAge = persons.stream().collect(Collectors.averagingInt(p -> p.age));
		System.out.println(averageAge); // 19.0

		System.out.println();

		/**
		 * If you're interested in more comprehensive statistics, the
		 * summarizing collectors return a special built-in summary statistics
		 * object. So we can simply determine min, max and arithmetic average
		 * age of the persons as well as the sum and count.
		 */
		IntSummaryStatistics ageSummary = persons.stream().collect(Collectors.summarizingInt(p -> p.age));

		// IntSummaryStatistics{count=4, sum=76, min=12, average=19.000000,
		// max=23}
		System.out.println(ageSummary);

		System.out.println();

		// The join collector accepts a delimiter as well as an optional prefix
		// and suffix.
		String phrase = persons.stream().filter(p -> p.age >= 18).map(p -> p.name)
				.collect(Collectors.joining(" and ", "In Germany ", " are of legal age."));

		System.out.println(phrase);
		// In Germany Max and Peter and Pamela are of legal age.

		System.out.println();

		/**
		 * In order to transform the stream elements into a map, we have to
		 * specify how both the keys and the values should be mapped. Keep in
		 * mind that the mapped keys must be unique, otherwise an
		 * IllegalStateException is thrown. You can optionally pass a merge
		 * function as an additional parameter to bypass the exception:
		 */
		Map<Integer, String> map = persons.stream()
				.collect(Collectors.toMap(//
						p -> p.age, // key
						p -> p.name, // value
						(name1, name2) -> name1 + ";" + name2));// how to merge
																// two keys

		// {18=Max, 23=Peter;Pamela, 12=David}
		System.out.println(map);

		/*
		 * Now that we know some of the most powerful built-in collectors, let's
		 * try to build our own special collector. We want to transform all
		 * persons of the stream into a single string consisting of all names in
		 * upper letters separated by the | pipe character. In order to achieve
		 * this we create a new collector via Collector.of(). We have to pass
		 * the four ingredients of a collector: a supplier, an accumulator, a
		 * combiner and a finisher.
		 */
		Collector<Person, StringJoiner, String> personNameCollector = Collector.of(() -> new StringJoiner(" | "), // supplier
				(j, p) -> j.add(p.name.toUpperCase()), // accumulator
				(j1, j2) -> j1.merge(j2), // combiner
				StringJoiner::toString); // finisher

		String names = persons.stream().collect(personNameCollector);

		System.out.println(names); // MAX | PETER | PAMELA | DAVID
	}
}

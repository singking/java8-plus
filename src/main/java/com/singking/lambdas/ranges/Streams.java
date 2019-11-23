package com.singking.lambdas.ranges;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Streams {
	public static void main(String[] args) throws IOException {

		Stream<String> song = Stream.of("gently", "down", "the", "stream", "!");
		song.forEach(System.out::println);

		String contents = new String(Files.readAllBytes(Paths.get("alice.txt")),
				StandardCharsets.UTF_8);
		Stream<String> words = Stream.of(contents.split("[\\P{L}]+"));
		words.forEach(System.out::println);

		Stream<String> echos = Stream.generate(() -> "Echo");
		echos.limit(20).forEach(System.out::print);

		System.out.println();

		Stream<Double> randoms = Stream.generate(Math::random).map(x -> x * 100);// .map(Math::abs);
		randoms.limit(20).forEach(l -> System.out.print(l + " "));
	}
}

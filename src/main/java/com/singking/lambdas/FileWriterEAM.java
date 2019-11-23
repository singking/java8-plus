package com.singking.lambdas;

import java.io.FileWriter;
import java.io.IOException;

/**
 * using lambda to cleanup resources
 *
 */
public class FileWriterEAM {

	private final FileWriter writer;

	private FileWriterEAM(final String fileName) throws IOException {
		writer = new FileWriter(fileName);
	}

	private void close() throws IOException {
		System.out.println("close called automatically...");
		writer.close();
	}

	public void writeStuff(final String message) throws IOException {
		writer.write(message);
	}

	/**
	 *
	 * @param fileName
	 * @param block
	 *            lambda expression
	 * @throws IOException
	 */
	public static void use(final String fileName, final UseInstance<FileWriterEAM, IOException> block)
			throws IOException {
		final FileWriterEAM writerEAM = new FileWriterEAM(fileName);
		try {
			block.accept(writerEAM);
		} finally {
			System.out.println("resource closing taken care of on behalf of the client");
			writerEAM.close();
		}
	}

	public static void main(String[] args) throws IOException {

		System.out.println(" lambda on multiple lines");

		FileWriterEAM.use("eam2.txt", writerEAM -> {
			writerEAM.writeStuff("how ");
			writerEAM.writeStuff("sweet ");
			writerEAM.writeStuff("it is to be loved by you");
		});
	}
}

@FunctionalInterface
interface UseInstance<T, X extends Throwable> {
	void accept(T path) throws X;
}
package com.homework27.homework27;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

import static com.homework27.homework27.IntegerList.IntegerListImpl.generateRandomArray;
import static com.homework27.homework27.IntegerList.IntegerListImpl.sortSelection;

@SpringBootApplication
public class Homework27Application {

	public static void main(String[] args) {
		SpringApplication.run(Homework27Application.class, args);

		Integer[] one = generateRandomArray();
//		Arrays.copyOf(one, one.length);
//		long start = System.currentTimeMillis();
//		sortInsertion(one);
//		System.out.println(System.currentTimeMillis() - start);

		Arrays.copyOf(one, one.length);
		long start1 = System.currentTimeMillis();
		sortSelection(one);
		System.out.println(System.currentTimeMillis() - start1);

//		Arrays.copyOf(one, one.length);
//		long start2 = System.currentTimeMillis();
//		sortBubble(one);
//		System.out.println(System.currentTimeMillis() - start2);

	}

}

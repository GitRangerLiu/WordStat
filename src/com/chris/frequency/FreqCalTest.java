package com.chris.frequency;

import java.io.IOException;
import java.util.Map;

public class FreqCalTest {

	public static void main(String[] args) throws IOException {
		Map<String, Integer> map = (new FreqCal()).calFreq();
		System.out.println(map);

	}

}

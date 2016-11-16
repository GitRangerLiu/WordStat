package com.chris.frequency;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


public class FreqCal {
	private String getWordString() throws IOException {
		File file = new File(".\\resource\\txt\\testpdf.txt");
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		String s;
		StringBuilder sb = new StringBuilder();
		while((s = br.readLine()) != null) {
			sb.append(s + "\n");
		}
		br.close();
		//Get the string of paper content
		return sb.toString().toLowerCase();

	}
	
	public Map<String, Integer> calFreq() throws IOException {
		Map<String, Integer> map = new TreeMap<>();
		String wordStr = getWordString();
		//Use non-word to split
		String regex = "[(. )(; )(, )(\n)?\"#:1234567890]";
		String[] strArr = wordStr.split(regex);
		int length = strArr.length;
		for(int i = 0; i < length; i++) {
			String word = strArr[i];
			Integer freq = map.get(word);
			map.put(word, freq == null ? 1 : freq + 1);
		}
		//Sort by map value, but it can't solve the problem of plural.
		map = sortByValue(map);
		return map;
	}
	
	/**
	 * link: http://stackoverflow.com/questions/109383/sort-a-mapkey-value-by-values-java
	 */
	private <K, V extends Comparable<? super V>> Map<K, V> 
		sortByValue(Map<K, V> map) {
		//Convert map to entry list
		List<Map.Entry<K, V>> list = new LinkedList<>(map.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<K, V>>() {
			@Override
			public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
				return (o2.getValue()).compareTo(o1.getValue());
			}
		});
		//Convert entry list to map
		Map<K, V> result = new LinkedHashMap<>();
		for(Map.Entry<K, V> entry : list) {
			result.put(entry.getKey(), entry.getValue());
		}
		return result;
	}
	
	
}

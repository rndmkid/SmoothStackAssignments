package chrisHW;

import java.util.*;
import java.util.stream.Collectors;

//Lambdas and functional interfaces and streams

public class JavaBasicsDay05 {
	
	
	//Question 1
	static class ArrayManipulator{
		
		static List<String> setup(){
			List<String> arrayList = new ArrayList();
			arrayList.add("ABCDE");
			arrayList.add("BCDE");
			arrayList.add("CDE");
			arrayList.add("DE");
			arrayList.add("E");
			return arrayList;
		}
		
		static List<String> sort_by_length(List<String> list){
			Comparator<String> c = 
					(String s1, String s2) -> s1.length() - s2.length();
			
			list = list.stream().sorted(c).collect(Collectors.toList());
			
			return list;
		}
		
		static List<String> sort_by_reverse_length(List<String> list){
			Comparator<String> c = 
					(String s1, String s2) -> -(s1.length() - s2.length());
			
			list = list.stream().sorted(c).collect(Collectors.toList());
			
			return list;
		}
		
		static List<String> e_first(List<String> list){
			Comparator<String> c = 
					(s1, s2) -> s1.charAt(0) == 'e' ? 1 : -1;
			
			list = list.stream().sorted(c).collect(Collectors.toList());
			
			return list;
		}
		
		static void helper(){}
		
		
	}
	
	//Question 2
	static String commaSeparator(List<Integer> list){
		StringBuilder str = new StringBuilder();
		list.stream().forEach(e -> {
			if(e % 2 == 0){
				str.append("e" + e);
			}else{
				str.append("o" + e);
			}
		});
		
		return str.toString();
	}
	
	//Question 3
	static List<String> a_3_letters(List<String> list){
		list = (List<String>) list.stream().filter(
				e -> e.charAt(0) == 'a' && e.length() == 3);
		return list;
	}
	
}

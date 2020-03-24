package com.briup.demo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CmsApplicationTests {

	@Test
	void contextLoads() {
		
		ArrayList<pojo> list = new ArrayList<>();
		list.add(new pojo(1,"1",10));
		list.add(new pojo(2,"2",20));
		
		list.add(new pojo(3,"3",30));
		list.add(new pojo(3,"4",40));
		
		Collections.sort(list,new Comparator<pojo>() {

			@Override
			public int compare(pojo o1, pojo o2) {
				//
				return o1.getAge()-o2.getAge();
			}
		});
		
		for (pojo p : list) {
			System.out.println(p);
		}
	}

}

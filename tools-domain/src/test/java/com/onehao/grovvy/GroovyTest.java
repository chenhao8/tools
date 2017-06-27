package com.onehao.grovvy;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.onehao.domain.InstFileGroovyParser;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:/context/*.xml")
public class GroovyTest {

	@Resource
	private InstFileGroovyParser instFileGroovyParser;
	
	@Test
	public void test() {
		try {
			String aa = instFileGroovyParser.check("liubin");
			System.err.println(aa);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

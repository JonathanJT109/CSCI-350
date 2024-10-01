package hw3;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class hw3dfaTest {

	@Test
	void fn1test_1() {
		assertTrue(hw3dfa.fn1("ab"));
		assertTrue(hw3dfa.fn1("z"));
	}
	
	@Test
	void fn1test_2() {
		assertTrue(hw3dfa.fn1("aba"));
		assertTrue(hw3dfa.fn1("abaaaaaaaaaaaaa"));
		assertTrue(hw3dfa.fn1("zaaaaaa"));
	}
	
	@Test
	void fn1test_3() {
		String s = "ab";
		for(int i = 0; i < 1000; i++) {
			assertTrue(hw3dfa.fn1(s));
			s = s + "a";
		}
		
	}
	
	@Test
	void fn1test_4() {
		assertFalse(hw3dfa.fn1("a"));
		assertFalse(hw3dfa.fn1("zb"));
		assertFalse(hw3dfa.fn1("zaaaaaaab"));
	}
	
	@Test
	void fn2test_1() {
		assertTrue(hw3dfa.fn2(""));
		assertTrue(hw3dfa.fn2("a"));
		assertTrue(hw3dfa.fn2("b"));
	}
	
	@Test
	void fn2test_2() {
		assertFalse(hw3dfa.fn2("ba"));
		assertFalse(hw3dfa.fn2("aba"));
		assertFalse(hw3dfa.fn2("c"));
	}
	
	@Test
	void fn2test_3() {
		assertTrue(hw3dfa.fn2("aaaaaaaa"));
		assertTrue(hw3dfa.fn2("bbbbbbbbbbbb"));
		assertTrue(hw3dfa.fn2("aaaaabbbbb"));
	}
	
	@Test
	void fn2test_4() {
		String s = "";
		for(int i = 0; i < 1000; i++) {
			assertTrue(hw3dfa.fn2(s));
			s = "a" + s + "b";
		}
	}
	
	@Test
	void fn3test_1() {
		assertTrue(hw3dfa.fn3("aaaa"));
		assertTrue(hw3dfa.fn3("bbbb"));
		assertTrue(hw3dfa.fn3("cccc"));
	}
	
	@Test
	void fn3test_2() {
		assertFalse(hw3dfa.fn3("aaa"));
		assertFalse(hw3dfa.fn3("bbbd"));
		assertFalse(hw3dfa.fn3("ccc "));
	}
	
	@Test
	void fn4test_1() {
		assertTrue(hw3dfa.fn4(""));
		assertTrue(hw3dfa.fn4("aa"));
		assertTrue(hw3dfa.fn4("ba"));
		assertTrue(hw3dfa.fn4("aabb"));
		assertTrue(hw3dfa.fn4("abcdbcab"));
	}
	
	@Test
	void fn4test_2() {
		assertFalse(hw3dfa.fn4("a"));
		assertFalse(hw3dfa.fn4("aaa"));
		assertFalse(hw3dfa.fn4("add"));
		assertFalse(hw3dfa.fn4("abababa"));
		assertFalse(hw3dfa.fn4("de"));
	}
	
	@Test
	void fn5test_1() {
		assertTrue(hw3dfa.fn5("aaaaa"));
		assertTrue(hw3dfa.fn5("ababa"));
		assertTrue(hw3dfa.fn5("aabaa"));
		assertTrue(hw3dfa.fn5("bbabb"));
		assertTrue(hw3dfa.fn5("abbba"));
	}
	
	@Test
	void fn5test_2() {
		assertFalse(hw3dfa.fn5("aaa"));
		assertFalse(hw3dfa.fn5(""));
		assertFalse(hw3dfa.fn5("ababb"));
		assertFalse(hw3dfa.fn5("ababab"));
		assertFalse(hw3dfa.fn5("acaca"));
	}

}

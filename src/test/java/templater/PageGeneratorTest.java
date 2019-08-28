package templater;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class PageGeneratorTest {

    private PageGenerator generator;

    @Before
    public void before(){
        generator = PageGenerator.instance("./src/test/java/templater");
    }

    @After
    public void after(){
        generator = PageGenerator.instance("./src");
    }

    @Test
    public void instance() {
        assertThat(generator == PageGenerator.instance(".")).isTrue();
    }

    @Test
    public void getPageByTemplate() {
        String expect = "one white man had two hats.";
        Map<String, Object> map = new HashMap<>();
        map.put("x", "one");
        map.put("y", "two");
        assertThat(generator.getPage("test_templater.html", map)).isEqualTo(expect);
    }
}
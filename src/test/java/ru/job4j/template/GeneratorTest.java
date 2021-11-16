package ru.job4j.template;

import org.junit.Ignore;
import org.junit.Test;

import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@Ignore
public class GeneratorTest {

    @Test
    public void produce() {
        String rsl = new GeneratorImpl().produce("var: ${k1}",
                Map.of("k1", "v"));
        assertThat(rsl, is("var: v"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void produceWhenKeyNotFound() {
        new GeneratorImpl().produce("var: ${k2}",
                Map.of("k1", "v"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void produceWhenExtraKeys() {
        new GeneratorImpl().produce("var: ${k1}",
                Map.of("k1", "v", "k2", "v"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void produceWhenIllegalTemplate() {
        new GeneratorImpl().produce("var: ${k1 k2}",
                Map.of("k1", "v", "k2", "v"));
    }
}
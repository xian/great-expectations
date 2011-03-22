great-expectations
==================

**Jasmine-style expectations for Java**

JUnit 4's assertThat() is kinda awesome and kinda sucks. It's awesome cuz it encourages you to write your own matchers. It sucks cuz it makes it really painful to write your own matchers. And it makes it really hard to find existing matcehrs.

great-expectations makes it dead-simple to use existing matchers using autocomplete in your favorite IDE, and it makes it very easy to declare your own type-safe matchers.

Here's a JUnit assertion:

    import static org.junit.Assert.assertThat;
    import static org.junit.matchers.JUnitMatchers.containsString;

    @Test public void testString() {
      assertThat("Hello", containsString("Hell"));
    }

Here's the equivalent great-expectations assertion:

    import static com.example.Expect.expect;

    @Test public void testString() {
      expect("Hello").toContain("Hell");
    }

Not a huge difference, but note that there's just a single import.

Here's how the matcher looks:

    @MatcherOf(String.class)
    public class StringMatcher<T extends String, M extends StringMatcher<T, M>> extends ObjectMatcher<T, M> {
      public boolean toContain(String expected) {
        return actual.indexOf(expected) != -1;
      }
    }

***Generating Expect.java***
Sorry, we have to generate some java code. Bummer. Use ExpectGenerator to spew out your Expect class. You can add your own matchers there.
great-expectations
==================

## Jasmine-style expectations for Java

    expect("abc").toMatch("b");
    expect(getLoggedInUser()).toBeNull();
    expect(listOfMeats).not.toContain("mortadella");

JUnit 4's assertThat() is kinda awesome and kinda sucks. It's awesome cuz it encourages you to write your own matchers. It sucks cuz it makes it really painful to write your own matchers. And it makes it really hard to find existing matcehrs.

great-expectations makes it dead-simple to find existing relevant matchers using autocomplete in your favorite IDE, and it makes it very easy to declare your own type-safe matchers.

Quick Start
===========

Download [great-expectations](http://mvnrepository.com/artifact/com.github.xian/great-expectations). If you're not using Maven or something like it for dependency management, don't forget to grab asm and asm-commons too, and put them all in your test classpath. (And think about using Maven.)

Create a class named Expect in your project, containing the contents of [this file](https://github.com/xian/great-expectations/raw/master/sample/src/test/java/com/example/Expect.java) (fix the package name to match where you place it).

Now write your first expectation in a test:
    expect(true).toBeFalse();

## Writing asserts:

Here's a JUnit assertion:

    import static org.hamcrest.CoreMatchers.not;
    import static org.junit.Assert.assertThat;
    import static org.junit.matchers.JUnitMatchers.containsString;

    @Test public void testMyAttitude() {
      assertThat("team", not(containsString("me")));
    }

Here's the equivalent great-expectations assertion:

    import static com.example.Expect.expect;

    @Test public void testMyAttitude() {
      expect("team").not.toContain("me");
    }

Not a huge difference, but note that there's just a single import; once you get "expect" imported, everything else is autocompletable.

## Writing matchers:

Here's how the matcher looks:

    @MatcherOf(String.class)
    public class StringMatcher<T extends String, M extends StringMatcher<T, M>> extends ObjectMatcher<T, M> {
      public boolean toContain(String expected) {
        return actual.indexOf(expected) != -1;
      }
    }

Create a class with a generic signature from hell. Then just create a method which returns true or false. Done. Boom.

### Generating Expect.java

Oh yeah, sorry, we have to generate some java glue code too. Bummer. Use ExpectGenerator to spew out your Expect class. You can add your own matchers by extending ExpectGenerator.matcherClasses() and adding to the list.

Download
========

great-expectations is available through maven:

    <dependency>
      <groupId>com.github.xian</groupId>
      <artifactId>great-expectations</artifactId>
      <version>0.9</version>
    </dependency>

or download directly:

    http://repo1.maven.org/maven2/com/github/xian/great-expectations/0.9/great-expectations-0.9.jar

Releases
========

### 0.9
* Added String toMatch(), which matches against regular expressions.
* Iterable's toContain() now doesn't care about order. Added toContainInOrder().
* Fixes compilation errors and warnings.

### 0.8
* Initial packaged release.

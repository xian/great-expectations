package com.pivotallabs.greatexpectations;

import com.pivotallabs.greatexpectations.matchers.BaseMatcher;
import com.pivotallabs.greatexpectations.matchers.BooleanMatcher;
import com.pivotallabs.greatexpectations.matchers.ComparableMatcher;
import com.pivotallabs.greatexpectations.matchers.DateMatcher;
import com.pivotallabs.greatexpectations.matchers.GreatExpectations;
import com.pivotallabs.greatexpectations.matchers.IterableMatcher;
import com.pivotallabs.greatexpectations.matchers.ObjectMatcher;
import com.pivotallabs.greatexpectations.matchers.StringMatcher;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

public class ExpectGenerator {
  private String defaultPackage;
  private String packageName;
  private PrintStream out;

  public ExpectGenerator(String packageName) {
    defaultPackage = BaseMatcher.class.getPackage().getName();
    this.packageName = packageName;
    out = System.out;
  }

  public String generateFor(Class<? extends BaseMatcher> matcherClass) {
    String name = className(matcherClass);
    MatcherOf annotation = matcherClass.getAnnotation(MatcherOf.class);

    StringBuilder buf = new StringBuilder();
    for (Class targetClass : annotation.value()) {
      String type = className(targetClass);
      String genericSig = annotation.directObject() ? "<T, X, M>" : "<T, M>";
      buf.append("    public static <T extends ")
          .append(type)
          .append(annotation.directObject() ? "<X>, X" : "")
          .append(", M extends ")
          .append(name)
          .append(genericSig)
          .append("> ")
          .append(name)
          .append(genericSig)
          .append(" expect(T actual) {\n" + "        return wrapped(")
          .append(name)
          .append(".class, actual);\n" + "    }");
    }
    return buf.toString();
  }

  public void generate() {
    generate(baseClasses());
  }

  public void generate(List<Class<? extends BaseMatcher>> classes) {
    out.println("package " + packageName + ";");
    out.println();
    out.println("import " + defaultPackage + ".*;");
    out.println("import static " + GreatExpectations.class.getName() + ".wrapped;");
    out.println();

    out.println("public class Expect {");
    for (Class<? extends BaseMatcher> aClass : classes) {
      out.println(generateFor(aClass));
    }
    out.println("}");
  }

  public void setOut(PrintStream out) {
    this.out = out;
  }

  public static void main(String[] args) throws IOException {
    ExpectGenerator expectGenerator = new ExpectGenerator(args[0]);
    if (args.length == 3 && args[1].equals("--outFile")) {
      expectGenerator.setOut(new PrintStream(new File(args[2])));
    }
    expectGenerator.generate();
  }

  public List<Class<? extends BaseMatcher>> baseClasses() {
    return Arrays.asList(
        ObjectMatcher.class,
        BooleanMatcher.class,
        ComparableMatcher.class,
        DateMatcher.class,
        IterableMatcher.class,
        StringMatcher.class
    );
  }

  private String className(Class<?> matcherClass) {
    String name;
    String packageName = matcherClass.getPackage().getName();
    if (packageName.equals("java.lang") || packageName.equals(defaultPackage)) {
      name = matcherClass.getSimpleName();
    } else {
      name = matcherClass.getName();
    }
    return name;
  }
}

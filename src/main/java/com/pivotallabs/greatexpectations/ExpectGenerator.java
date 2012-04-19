package com.pivotallabs.greatexpectations;

import com.pivotallabs.greatexpectations.matchers.*;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.*;

public class ExpectGenerator {
  private static Map<Class, String> wrappers = new HashMap<Class, String>();

  private Set<String> importedPackages = new HashSet<String>();
  private String packageName;
  private PrintStream out;

  static {
    wrappers.put(Integer.class, "int");
    wrappers.put(Long.class, "long");
    wrappers.put(Float.class, "float");
    wrappers.put(Double.class, "double");
  }
  
  public ExpectGenerator(String packageName) {
    importedPackages.add("java.lang");
    importedPackages.add(packageName);
    importedPackages.add(BaseMatcher.class.getPackage().getName());
    importedPackages.add(ObjectMatcher.class.getPackage().getName());
    importedPackages.add(packageName);

    this.packageName = packageName;
    out = System.out;
  }

  public String generateFor(Class<? extends BaseMatcher> matcherClass) {
    String name = className(matcherClass);
    MatcherOf annotation = matcherClass.getAnnotation(MatcherOf.class);

    StringBuilder buf = new StringBuilder();
    for (Class targetClass : annotation.value()) {
      if (targetClass.equals(Boolean.class)) {
        buf.append("    public static BooleanMatcher<Boolean, ?> expect(boolean actual) {\n" +
                   "        return wrapped(BooleanMatcher.class, actual);\n" +
                   "    }\n");
      }

      String type = className(targetClass);
      String genericSig = annotation.directObject() ? "<T, X, M>" : "<T, M>";
      String returnType = annotation.directObject() ? "<T, X, " : "<T, ";
      buf.append("    public static <T extends ")
          .append(type)
          .append(annotation.directObject() ? "<X>, X" : "")
          .append(", M extends ")
          .append(name)
          .append(genericSig)
          .append("> ")
          .append(name)
          .append(returnType)
          .append("?> expect(T actual) {\n" + "        return wrapped(")
          .append(name)
          .append(".class, actual);\n" + "    }");

      if (wrappers.containsKey(targetClass)) {
        buf.append("\n    public static ")
            .append(name)
            .append("<")
            .append(className(targetClass))
            .append(", ?> expect(")
            .append(wrappers.get(targetClass))
            .append(" actual) {\n" + "        return wrapped(")
            .append(name)
            .append(".class, actual);\n" + "    }");
      }
    }
    return buf.toString();
  }

  public void generate() {
    generate(matcherClasses());
  }

  public void generate(List<Class<? extends BaseMatcher>> classes) {
    out.println("package " + packageName + ";");
    out.println();
    out.println("import " + ObjectMatcher.class.getPackage().getName() + ".*;");
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

  public List<Class<? extends BaseMatcher>> matcherClasses() {
    return new ArrayList<Class<? extends BaseMatcher>>(
        Arrays.<Class<? extends BaseMatcher>>asList(
            ObjectMatcher.class,
            BooleanMatcher.class,
            ComparableMatcher.class,
            DateMatcher.class,
            IterableMatcher.class,
            StringMatcher.class,
            IntegerMatcher.class,
            LongMatcher.class,
            FloatMatcher.class,
            DoubleMatcher.class
        )
    );
  }

  private String className(Class<?> matcherClass) {
    String name;
    String packageName = matcherClass.getPackage().getName();
    if (importedPackages.contains(packageName)) {
      name = matcherClass.getSimpleName();
    } else {
      name = matcherClass.getName();
    }
    return name;
  }
}

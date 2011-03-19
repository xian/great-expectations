package com.pivotallabs.greatexpectations;

import java.util.Arrays;
import java.util.List;

public class ExpectGenerator {
  private String defaultPackage;
  private String packageName;

  public ExpectGenerator(String packageName) {
    defaultPackage = BaseExpectation.class.getPackage().getName();
    this.packageName = packageName;
  }

  public String generateFor(Class<? extends BaseExpectation> expectationClass) {
    String name;
    if (expectationClass.getPackage().getName().equals(defaultPackage)) {
      name = expectationClass.getSimpleName();
    } else {
      name = expectationClass.getName();
    }
//        expectationClass.get
    String type = expectationClass.getGenericInterfaces()[0].toString();
    return "    public static <T extends " + type + ", M extends " + name + "<T, M>> " + name + "<T, M> expect(T actual) {\n" +
        "        return wrapped(" + name + ".class, actual);\n" +
        "    }";
  }

  private void generate(List<Class<? extends BaseExpectation>> classes) {
    System.out.println("package " + packageName + ";");
    System.out.println();
    System.out.println("import static " + defaultPackage + ".*;");
    System.out.println("import static " + GreatExpectations.class.getName() + ".wrapped;");
    System.out.println();

    System.out.println("public class Expect {");
    for (Class<? extends BaseExpectation> aClass : classes) {
      System.out.println(generateFor(aClass));
    }
    System.out.println("}");
  }

  public static void main(String[] args) {
    List<Class<? extends BaseExpectation>> classes = Arrays.<Class<? extends BaseExpectation>>asList(
        BooleanExpectation.class,
        ComparableExpectation.class,
        DateExpectation.class,
        IterableExpectation.class,
        ObjectExpectation.class,
        StringExpectation.class
    );

    new ExpectGenerator("com.pivotallabs.greatexpectations").generate(classes);
  }
}

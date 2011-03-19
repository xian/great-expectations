package com.pivotallabs.greatexpectations;

import com.pivotallabs.greatexpectations.expectors.BaseExpectation;
import com.pivotallabs.greatexpectations.expectors.BooleanExpectation;
import com.pivotallabs.greatexpectations.expectors.ComparableExpectation;
import com.pivotallabs.greatexpectations.expectors.DateExpectation;
import com.pivotallabs.greatexpectations.expectors.GreatExpectations;
import com.pivotallabs.greatexpectations.expectors.IterableExpectation;
import com.pivotallabs.greatexpectations.expectors.ObjectExpectation;
import com.pivotallabs.greatexpectations.expectors.StringExpectation;

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
    String name = className(expectationClass);
    ExpectationOn annotation = expectationClass.getAnnotation(ExpectationOn.class);

    StringBuilder buf = new StringBuilder();
    for (Class targetClass : annotation.value()) {
      String type = className(targetClass);
      buf.append("    public static <T extends ")
          .append(type)
          .append(annotation.directObject() ? "<X>" : "")
          .append(", M extends ")
          .append(name)
          .append("<T, M>> ")
          .append(name)
          .append("<T, M> expect(T actual) {\n" + "        return wrapped(")
          .append(name)
          .append(".class, actual);\n" + "    }");
    }
    return buf.toString();
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
    List<Class<? extends BaseExpectation>> classes = Arrays.asList(
        BooleanExpectation.class,
        ComparableExpectation.class,
        DateExpectation.class,
        IterableExpectation.class,
        ObjectExpectation.class,
        StringExpectation.class
    );

    new ExpectGenerator(args[0]).generate(classes);
  }

  private String className(Class<?> expectationClass) {
    String name;
    String packageName = expectationClass.getPackage().getName();
    if (packageName.equals("java.lang") || packageName.equals(defaultPackage)) {
      name = expectationClass.getSimpleName();
    } else {
      name = expectationClass.getName();
    }
    return name;
  }
}

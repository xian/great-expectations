package com.pivotallabs.greatexpectations;

import com.pivotallabs.greatexpectations.expectors.BaseExpectation;
import com.pivotallabs.greatexpectations.expectors.BooleanExpectation;
import com.pivotallabs.greatexpectations.expectors.ComparableExpectation;
import com.pivotallabs.greatexpectations.expectors.DateExpectation;
import com.pivotallabs.greatexpectations.expectors.GreatExpectations;
import com.pivotallabs.greatexpectations.expectors.IterableExpectation;
import com.pivotallabs.greatexpectations.expectors.ObjectExpectation;
import com.pivotallabs.greatexpectations.expectors.StringExpectation;

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
    defaultPackage = BaseExpectation.class.getPackage().getName();
    this.packageName = packageName;
    out = System.out;
  }

  public String generateFor(Class<? extends BaseExpectation> expectationClass) {
    String name = className(expectationClass);
    ExpectationOn annotation = expectationClass.getAnnotation(ExpectationOn.class);

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

  private void generate(List<Class<? extends BaseExpectation>> classes) {
    out.println("package " + packageName + ";");
    out.println();
    out.println("import " + defaultPackage + ".*;");
    out.println("import static " + GreatExpectations.class.getName() + ".wrapped;");
    out.println();

    out.println("public class Expect {");
    for (Class<? extends BaseExpectation> aClass : classes) {
      out.println(generateFor(aClass));
    }
    out.println("}");
  }

  public void setOut(PrintStream out) {
    this.out = out;
  }

  public static void main(String[] args) throws IOException {
    List<Class<? extends BaseExpectation>> classes = Arrays.asList(
        BooleanExpectation.class,
        ComparableExpectation.class,
        DateExpectation.class,
        IterableExpectation.class,
        ObjectExpectation.class,
        StringExpectation.class
    );

    ExpectGenerator expectGenerator = new ExpectGenerator(args[0]);
    if (args.length == 3 && args[1].equals("--outFile")) {
      expectGenerator.setOut(new PrintStream(new File(args[2])));
    }
    expectGenerator.generate(classes);
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

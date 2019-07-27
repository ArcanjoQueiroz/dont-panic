import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.math.BigDecimal;
import java.util.Objects;

public class ProxyJDK {

  interface MyInterface {
    public String getName();
    public Integer getAge();
  }

  public static class MyClass implements MyInterface {

    @Override
    public String getName() {
      return "Alexandre";
    }

    @Override
    public Integer getAge() {
      return null;
    }

  }

  public static class NotNullInvocationHandler implements InvocationHandler {

    private Object original;

    public NotNullInvocationHandler(Object original) {
      this.original = original;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
      final Object result = method.invoke(original, args);
      if (Objects.nonNull(result)) {
        return result;
      }
      final Class<?> returnType = method.getReturnType();
      if (returnType.isAssignableFrom(String.class)) {
        return "";
      } else if (returnType.isAssignableFrom(Boolean.class)) {
        return false;
      } else if (returnType.isAssignableFrom(Byte.class)) {
        return 0;
      } else if (returnType.isAssignableFrom(Short.class)) {
        return 0;
      } else if (returnType.isAssignableFrom(Integer.class)) {
        return 0;
      } else if (returnType.isAssignableFrom(Long.class)) {
        return 0L;
      } else if (returnType.isAssignableFrom(Float.class)) {
        return 0F;
      } else if (returnType.isAssignableFrom(Double.class)) {
        return 0D;
      } else if (returnType.isAssignableFrom(BigDecimal.class)) {
        return BigDecimal.ZERO;
      }
      return result;
    }
  }

  @SuppressWarnings("unchecked")
  public static <T> T createProxy(T obj, Class<T> klass) {
    if (Objects.isNull(obj)) {
      throw new IllegalArgumentException("Object is null");
    }
    if (Objects.isNull(klass)) {
      throw new IllegalArgumentException("Target class is null");
    }
    if (!klass.isInterface()) {
      throw new IllegalArgumentException(String.format("Target class is not an interface: %s", klass.getSimpleName()));
    }
    return (T) Proxy.newProxyInstance(klass.getClassLoader(), new Class[] { klass }, new NotNullInvocationHandler(obj));
  }

  public static void main(String[] args) {
    final MyInterface obj = ProxyJDK.createProxy(new MyClass(), MyInterface.class);
    System.out.println(obj.getName());
    System.out.println(obj.getAge());
  }
}

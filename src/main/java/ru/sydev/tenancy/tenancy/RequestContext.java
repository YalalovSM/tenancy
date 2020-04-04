package ru.sydev.tenancy.tenancy;

public class RequestContext {
  private RequestContext() { }

  private static final ThreadLocal<Context> requestContext = new InheritableThreadLocal<Context>() {
    @Override
    protected Context initialValue() {
      return new Context();
    }
  };

  public static void setContext( Context context ) {
    requestContext.set(context);
  }

  public static Context getContext() {
    return requestContext.get();
  }

  public static void clear() {
    requestContext.remove();
  }

}

//MOP与元编程
//实现了GroovyInterceptable接口的对象，不管调用的方法存在还是不存在都会被invokeMethod()拦截
class InterceptTest implements GroovyInterceptable {
    @Override
    Object invokeMethod(String name, Object args) {
        return super.invokeMethod(name, args)
    }
}
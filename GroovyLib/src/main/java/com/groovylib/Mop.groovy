//MOP与元编程
//Groovy拦截器是扩展了GroovyInterceptable的Groovy对象,具有方法拦截功能
//一旦一个类被加载到JVM中,我们就不能修改它的元对象Class了。
// 不过我们可以通过调用setMetaClass()修改它的MetaClass。这给我们一种对象在运行时修改了它的类的感觉。
//

println " "
//程序执行时在 metaClass 上动态定义一个闭包
def val = new Integer(3)
Integer.metaClass.toString = { -> println 'intercepted' }
val.toString()
//继承groovyInterceptable的对象，不管调用的方法存不存在都会被invokeMethod拦截
class InterceptTest implements GroovyInterceptable {
    @Override
    Object invokeMethod(String name, Object args) {
        "invokeMethod intercepted"
    }
}

println new InterceptTest().toString()

class AGroovyObject {
    String str = "123"

    def existingMethod() { 'existingMethod' }

    def existingMethod2() { 'existingMethod2' }
    def closureProp = { 'closure called' }
}
//1.对于一个POJO，Groovy会去应用类（application-wide）的MetaClassRegistry取它的MetaClass，
// 并将方法调用委托给它。因此，我们在它的MetaClass上定义的任何拦截器或方法，都优先于POJO原来的方法。
//2.对于一个POGO，Groovy会采取一些额外的步骤,如果对象实现了GroovyInterceptable，
// 那么所有的调用都会被路由给它的invokeMethod（）
AGroovyObject.metaClass.existingMethod2 = { -> 'intercepted' }
def obj = new AGroovyObject()
println "existingMethod2---" + obj.existingMethod2()
//通过元方法调用方法
AGroovyObject aObj = new AGroovyObject()
//首先查看是否存在该方法
println "aObj 是否有方法 existingMethod  " + (AGroovyObject.getMetaClass().respondsTo(aObj, "existingMethod").size() > 0 ? "有" : "没有")
println "通过元方法meatMethod执行 existingMethod() 结果 " + aObj.metaClass.getMetaMethod("existingMethod").invoke(aObj);
//[]调用属性
String str = "str"
println aObj[str]
//拦截方法调用的两种方式：要么让对象拦截，要么让MetaClass拦截,让对象处理的话，需要实现GroovyInterceptable接口

//class TestMethodInvocation extends GroovyTestCase {
//    void testInterceptedMethodCallonPOJO() {
//        def val = new Integer(3)
//        Integer.metaClass.toString = { -> 'intercepted' }
//        assertEquals "intercepted", val.toString()
//    }
//
//    void testInterceptableCalled() {
//        def obj = new AnInterceptable()
//        assertEquals 'intercepted', obj.existingMethod()
//        assertEquals 'intercepted', obj.nonExistingMethod()
//    }
//
//    void testInterceptedExistingMethodCalled() {
//        AGroovyObject.metaClass.existingMethod2 = { -> 'intercepted' }
//        def obj = new AGroovyObject()
//        assertEquals 'intercepted', obj.existingMethod2()
//    }
//
//    void testUnInterceptedExistingMethodCalled() {
//        def obj = new AGroovyObject()
//        assertEquals 'existingMethod', obj.existingMethod()
//    }
//
//    void testPropertyThatIsClosureCalled() {
//        def obj = new AGroovyObject()
//        assertEquals 'closure called', obj.closureProp()
//    }
//
//    void testMethodMissingCalledOnlyForNonExistent() {
//        def obj = new ClassWithInvokeAndMissingMethod()
//        assertEquals 'existingMethod', obj.existingMethod()
//        assertEquals 'missing called', obj.nonExistingMethod()
//    }
//
//    void testInvokeMethodCalledForOnlyNonExistent() {
//        def obj = new ClassWithInvokeOnly()
//        assertEquals 'existingMethod', obj.existingMethod()
//        assertEquals 'invoke called', obj.nonExistingMethod()
//    }
//
//    void testMethodFailsOnNonExistent() {
//        def obj = new TestMethodInvocation()
//        shouldFail(MissingMethodException) { obj.nonExistingMethod() }
//    }
//}
//
//class AnInterceptable implements GroovyInterceptable {
//    def existingMethod() {}
//
//    def invokeMethod(String name, args) { 'intercepted' }
//}
//
//class AGroovyObject {
//    def existingMethod() { 'existingMethod' }
//
//    def existingMethod2() { 'existingMethod2' }
//    def closureProp = { 'closure called' }
//}
//
//class ClassWithInvokeAndMissingMethod {
//    def existingMethod() { 'existingMethod' }
//
//    def invokeMethod(String name, args) { 'invoke called' }
//
//    def methodMissing(String name, args) { 'missing called' }
//}
//
//class ClassWithInvokeOnly {
//    def existingMethod() { 'existingMethod' }
//
//    def invokeMethod(String name, args) { 'invoke called' }
//}
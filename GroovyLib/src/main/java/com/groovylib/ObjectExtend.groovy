//Object对象扩展
String str = "123456"
//1.dump 方法，打印类内部情况
println str.dump()//返回该对象的类型、哈希、字段
class Example {
    def f1() { println "f1 of Example called ……" }

    def f2() { println "f2 of Example called ……" }

    public Exampe(String s) {

    }
}
//2.with上下文， 调用with()需要传入一个闭包，with()将该闭包的delegate属性设置到调用with()的对象上
new Example().with {
    println "this is ${this}，"
    println "owner is ${owner}，"
    println "delegate is ${delegate}."
}


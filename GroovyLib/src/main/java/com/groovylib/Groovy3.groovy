//--------------------1.闭包--------------------------------
//可以执行的代码块，或者匿名函数,闭包实际上就是一个内部类

def closure0 = {
    //给闭包指定参数，在这里没有填所以it也不能使用
    ->
//    print it
    println "------闭包内置成员变量-----"
    println "this.class = " + this
    println "owner is = " + owner
    println "delegate = " + delegate
    println "------这三个变量都是同一个实例-----"
}
def closure1 = {
    //默认接收一个参数it
    print it
    print "123\n"
}
def closure2 = {
        //给闭包指定参数，a,b
    a, b ->
//    print it
        println "两参数闭包$a,$b"
}
closure0()
closure1("默认参数")
closure2(25, 23)

println "闭包类型 " + closure2.getClass()//groovy中的一个类
println "闭包参数类型 " + closure2.parameterTypes //参数类型
println "闭包参数数量 " + closure2.maximumNumberOfParameters//闭包参数数量
//----------------2.函数---------------------------
//定义一个参数是闭包的函数
void func(closure) {
    closure()
}
//调用该方法
func {
    println("函数参数是闭包")
}
//println "方法类型 "+ func.getClass()
//------------3.类中定义了call方法，可以直接使用对象()调用
class Action {
    void call(ac) {
        println ac + "call方法"
    }

    void call1(ac) {
        println "call1"
    }
}

def action = new Action()
action("调用了")

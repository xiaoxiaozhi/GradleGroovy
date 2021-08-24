package com.groovylib

import java.text.SimpleDateFormat
import java.util.logging.SimpleFormatter;

//
//android studio 怎么运行groovy https://blog.csdn.net/aha_jasper/article/details/104810684
//https://blog.csdn.net/aha_jasper/article/details/104810684
//public class closure {
//}
//--------------------闭包--------------------------------------
//1.定义一个闭包
def closure = {
    a, b ->
        println "定义一个带两个参数的闭包$a,$b"
}
//2.使用一个闭包
def product = "偶数"

def func(int n, p) {//参数p是一个函数
    for (int i = 0; i < n; i++) {
        p(i)
    }
}
//调用的时候 因为p是最后一个参数也可以写成
func(10) {
    if (it % 2 == 0 && it != 0) {
        print product  //闭包还可以使用函数调用者的作用域内的变量
        print it + "\t"
    }
}
println " "
//3.向闭包传递参数
def func1(a, closure) {
    for (i in 1..a) {
        closure new Date(), i //传递参数
    }
}

func1(10) {
    date, i ->
        print "${new SimpleDateFormat("HH:mm:ss").format(date)} 打印$i\t"
}
println " "
//3.1根据闭包的参数，选择使用
def fun2(a, closure) {
    if (closure.maximumNumberOfParameters == 1) {
        println "一个参数的闭包"
    } else if (closure.maximumNumberOfParameters == 2) {
        println "两个参数的闭包"
    }
}

fun2(10) {

}
println " "
//4.清理资源
new FileWriter("out.txt").withWriter {
    it.write("使用闭包自动close关闭流")
}//使用withWriter才能关闭流
println " "
//5.闭包委托,从日志可以看出来，闭包被创建成一个内部类，delegate被设置为owner。
// 在闭包内引用的变量和方法都会绑定到this，它负责处理任何方法调用，以及对任何属性或变量的访问。
// 如果this无法处理，则转向owner，最后是delegate
def closure1 = {
    println "first closure1"
    println getClass().getName() //
    println "this is " + this + "; parent isc" + getClass().superclass.getName()
    println "owner is " + owner
    println "delegate is " + delegate
    def closure2 = {
        println "second closure2"
        println getClass().getName()
        println "this is " + this + "; parent isc" + getClass().superclass.getName()
        println "owner is " + owner
        println "delegate is " + delegate
    }
    closure2()
}
closure1()
println " "

class Handler {
    def f1() { println "f1 of Handler called ……" }

    def f2() { println "f2 of Handler called ……" }
}

class Example {
    def f1() { println "f1 of Example called ……" }

    def f2() { println "f2 of Example called ……" }

    def foo(closure) {
        closure.delegate = new Handler()
//        new Handler().with closure  //相当于 def clone = closure.clone() clone.delegate = handler  clone()
        closure()
    }
}

def f1() { println "f1 of Script called……" }
//在闭包内引用的变量和方法都会绑定到this，它负责处理任何方法调用，以及对任何属性或变量的访问。
// 如果this无法处理，则转向owner，最后是delegate
new Example().foo {
    println "this is " + this//this 是闭包，闭包本质上是个内部类，它没有 f1 和 f2 函数
    f1()
    f2()
}

println(" ")
//一个i=0到i=3的循环，3的扩展类型，groovy已经做好
3.times {
    print it
}
println("groovy")
println "groovy" //没有歧义的话括号可以省略
println "----闭包-----"
0.step(10, 2) {//闭包
    print it + " "
}
println "等价于"
//等价于
0.step(10, 2, { print it + " " }) //函数在最后一个参数，可以写在外面

println "\n----安全导航操作符----"

def nav(String str) {
    "没有参数的时候就不执行返回" + str?.reverse()
}

println(nav())
println(nav("有值"))
println "----动态类型----"
//def 动态类型，根据最后一行的返回确定函数类型
def test() {
    "123\n"
}

print(test())
println "----异常处理----"

def sleep() {
    Thread.sleep(1000)
}

sleep()

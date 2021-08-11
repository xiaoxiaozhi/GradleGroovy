package com.groovylib;
//
//android studio 怎么运行groovy https://blog.csdn.net/aha_jasper/article/details/104810684
//https://blog.csdn.net/aha_jasper/article/details/104810684
//public class Groovy1 {
//}

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

import org.apache.groovy.docgenerator.DocGenerator

class Dog {
    int price = 110
    def name = "dog"
    private String appearance
    def voice //默认是 object类型

    void setPrice(int p) {
        print("实际上调用的是 setPrice方法")
        this.price = p
    }

    int getPrice() {
        println("实际上调用的是 getPrice方法\n")
        return price
    }
}

Dog dog = new Dog()
println(dog.price)//实际上调用的是 getMils 元编程,实际上是通过反射调用，private没用
println "通过字符串的形式访问变量 "+dog.'price'
def pri = "price"
println "通过变量的形式访问变量 "+dog."${pri}"
dog.price = 1000
dog.@price //加@直接访问变量就不会调用get和set方法
class Car {
    def mils
    final year = 27 // 等价于 final def year = 27 //动态标识符可以省略
    private int day
    char c = "1"
    def excute(x,y,z){
        print "${x},${y},${z}"
        print x.class
    }

}

def car = new Car(mils: 100, day: 10)
println("\n" + car.mils)
println car.@day //private 属性还是可以调用，Groovy对private修饰符没有强制
car.mils = 1000
println("动态类型自动装箱 int -->" + car.mils.class)
println("动态类型自动装箱 char -->" + car.c.class)
println("等等...")
def name = ''//单引号是纯粹的字符串，双引号可以插值
def realName = "$name"
def secondName = ''' 
first
second 
third
'''//三个点换行
println '为什么是java类 ' + name.getClass()
println realName.getClass()
println "\n三个点换行 " + secondName

def array = [1, 2, 3]
println "数组根据动态类型推导出来的是" + array.getClass()
int[] array1 = [1, 2, 3]
println "声明数组类型" + array1.getClass()
int[] array2 = [1, "m", 3];//m转成ascii, 当超过ascii就会报错
println array2.getClass()
println array2[1]
//[]闭区间
(0..5).forEach {
    print(it + "\t")
}
//前闭后开
println ""
(0..<5).forEach() {
    print(it + "\t")
}
println ""
('a'..'x').forEach() {//根据ascii
    print(it + "\t")
}
def range = (0..10)
println "\n" + range.getClass()//直接(0..10).getClass() 则会报错，原因未知；groovy.lang.IntRange是什么类型？？？

def map = [a: 11, b: 12]// 映射的 key是string 类型
map.forEach() { k, v ->
    println "k:$k v:$v k.class = ${k.class}"
}
println map['a']//打印map值，注意 健要加引号否则就是变量
car.excute(x:10,y:2,z:3,4,5)//必须符合参数个数才不会报错，前面





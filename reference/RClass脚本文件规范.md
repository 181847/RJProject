# RClass脚本文件规范

## RClass脚本文件所包含的信息类型
类型  |   解释
-----------------|--------
RClass类型    | 相当于Java中的**接口类、抽象类和普通类**之间的区别
RClass名字     | 用于唯一定义一个RClass的字符串，类似Java中管理包的概念，形如“com.github.liuyang.RClassDemo1”，
非接口父类       |  相当于Java中extends之后的类，这个类不能是接口类RClass
接口父类          | 相当于Java中的Implements之后的接口类
成员变量        | 成员变量，包括静态和非静态，对基本数据类型可以有初始化值
构造Function      | 构造Function，新建对象的时候会调用这个Function来执行对一个RClass实例的初始化操作
静态Function      | 静态Function，类似Java中的静态方法，这种Function不能访问实例中的非静态成员变量
非静态Function    | 普通的Function，可以访问实例中的任何静态成员变量
抽象Function      | 没有具体内部实现的Function，这种Function类似Java中的抽象方法，只声明了Function对外部的各个组件类型和名字（*比如需要的参数，返回值……*），属于不完全的非静态Function



## RClass各个信息板块之间的区分
* 每个信息板块之间用*Table*键来区分
* 比如在下面这个代码中我们可以看到三个信息声明板块**RClass类型**、**RClass名字** 和 **成员变量**。
  ```
  Type:
    AbstractClass
  Name:
    com.github.liuyang.RClassDemo1
  Member:
    basic.Integer member3 -12
    default.myPackage.RClass4 member4
  ```
  在上面这段代码中，每个声明板块下面再用一个*Table*键来将板块声明和板块之下的**子信息**进行区分，
  各个子信息也可以是另一块信息块，像这样层层包含，我们以**成员变量（Member:）**为例子，来看看在成员变量块中声明
  几个**静态成员**
  ```
  Member:
    Static:
      myPackage.RClass2 staticMember1
		  myPackage.RClass3 staticMember2
    basic.Integer member3 -12
    default.myPackage.RClass4 member4
  ```
  在上面这个例子当中Member信息块的下面声明了一个Static信息块，这个Static信息块的下面声明了两个静态成员。




## 具体信息类型的规范


### 1. RClass类型
* RClass类型块声明，以**Type:** 为标志
* 具体类型声明，有且只能是以下三种
    三种类型声明|
    -----------
    **Interface**|
    **AbstractClass**|
    **Class**|


###2.RClass的名字
  * RClass的名字采用和**Java**类似的包层次管理方法，任何一个类都应该在一个包当中
  * 脚本命名规范 *注：此命名规范适用于所有脚本中的可命名部分*
      字符      |   字符描述
      ----------|-----------
      空格      |   空格不能出现在RClass的名字中
      **.**     |   **.** 或者说**点**只能出现在包名和最底层的类名之间作为分隔符
      **(**、**)**、**{**、**}**|  半角的圆括号、花括号不能出现在名字中
      **@**     | **@**将作为特殊组件名称的标志，不能出现在RClass的名字中




###3.非接口父类
  *
###4.接口父类
###5.成员变量
###5.Function


-------------------
## 声明块声明字符串查找表
信息块       |   信息块声明字符串     |    描述
------------|-----------------------|-------------
RClass类型   |Type:                  |

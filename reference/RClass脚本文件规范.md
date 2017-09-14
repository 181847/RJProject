# RClass脚本文件规范

## RClass脚本文件所包含的信息类型
信息类型     |   解释
--------------------|--------
RClass类型           | 相当于Java中的**接口类、抽象类和普通类**之间的区别
RClass名字        | 用于唯一定义一个RClass的字符串，类似Java中管理包的概念，形如“com.github.liuyang.RClassDemo1”，
非接口父类       |  相当于Java中extends之后的类，这个类不能是接口类RClass
接口父类          | 相当于Java中的Implements之后的接口类
成员变量        | 成员变量，包括静态和非静态，对基本数据类型可以有初始化值
构造Function      | 构造Function，新建对象的时候会调用这个Function来执行对一个RClass实例的初始化操作
静态Function      | 静态Function，类似Java中的静态方法，这种Function不能访问实例中的非静态成员变量
非静态Function    | 普通的Function，可以访问实例中的任何静态成员变量
抽象Function      | 没有具体内部实现的Function，这种Function类似Java中的抽象方法，只声明了Function对外部的各个组件类型和名字（*比如需要的参数，返回值……*），属于不完全的非静态Function

**注意：RClass没有Function重载的概念，也就是说，一个RClass只能有一个名字的Function实现，不会存在【同名Function，但是参数不同，内部实现不同】**

## RClass各个信息板块之间的区分
* 每个信息板块之间用*Table*键来区分
* 比如在下面这个代码中我们可以看到三个信息声明板块 **RClass类型**、**RClass名字** 和 **成员变量**。
```
Type:
    AbstractClass
Name:
    com.github.liuyang.RClassDemo1
Members:
    default.myPackage.RClass4 member4
```
在上面这段代码中，每个声明板块下面再用一个*Table*键来将板块声明和板块之下的**子信息**进行区分，各个子信息也可以是另一块信息块，像这样层层包含，我们以 **成员变量（Member:）** 为例子，来看看在成员变量块中声明几个 **静态成员**
```
Members:
    Static:
        staticMember1
            myPackage.RClass2
        staticMember2
            myPackage.RClass3
    member3
        basic.Integer
        Init:
            -12
    member4
        default.myPackage.RClass4
```

在上面这个例子当中，Member信息块的下面声明了一个Static信息块，这个Static信息块的下面声明了两个静态成员。

-----
## 具体信息类型的规范

### 1.RClass类型
* RClass类型块声明，以**Type:** 为标志
* 具体类型声明，有且只能是以下三种
三种类型声明   	|
-----------------|
**Interface** |
**AbstractClass** |
**Class** |

* 举例
```
Type:
    AbstractRClass
```
---
### 2.RClass的名字
* RClass的名字采用和**Java**类似的包层次管理方法，任何一个类都应该在一个包当中
* 脚本命名规范 *注：此命名规范适用于所有脚本中的可命名部分*
    字符      |   字符描述
    ----------|-----------
    空格      |   空格不能出现在RClass的名字中
    **(**、**)**、**{**、**}**、**<**、**>**  |  半角的圆括号、花括号不能出现在名字中
    **@**     | **@** 将作为特殊组件名称的标志，不能出现在RClass的名字中

* 举例
```
Name:
    com.github.liuyang.RClassDemo2
```
---
### 3.非接口父类
* 非接口父类的子类信息中只能存储一行RClass的名字
* 举例
```
Extends:
    com.github.liuyang.RClassDemo
```
---
### 4.接口父类
* 接口父类中可以存储多行RClass的名字，注意保证这些RClass的类型是接口
* 举例
```
Implements:
    myPackage.Interface1
    myPackage.Interface2
```
---
### 5.成员变量
* 单个变量声明结构
```
变量名
    变量类型
    Init:
        变量初始化信息
```
变量名下面第一个子信息必须是类型信息，最后一个是初始化信息，*类型信息必须有，初始化信息可以没有*。  
举例：
```
member3
    basic.Integer
    Init:
        -12
```
主信息声明了变量的名字 **member3** 。  
第一个子信息表明类型是 **basic.Integer**。  
第三个子信息用 **Init:** 来表明一个初始化信息，初始化值为 **-12**。


* 成员变量声明块用**Member:** 开始
* 非静态变量普通陈列就可以了，而静态变量部分需要声明一个新的**Static**信息块，比如下面这个单独的Static块
```
Static:
    staticMember1
        myPackage.RClass2
    staticMember2
        myPackage.RClass3
```
* 变量名称必须遵守组件命名规范
* 初始化值只对应基本数据类型，比如整型、布尔型、字符型……，非基本数据类型的变量声明可以没有初始化值，如果有的话也会被忽略
* 成员变量声明举例，下面声明了两个静态成员变量和两个普通成员变量
```
Member:
    Static:
        staticMember1
            myPackage.RClass2
        staticMember2
            myPackage.RClass3
    member3
        basic.Integer
        Init:
            -12
    member4
        default.myPackage.RClass4
```
----
### 6.Function
* Function内部具有多个子信息块

Function子信息类型	|	 解释| 构造Function自定义  |  静态Function自定义  |非静态Function自定义 | 抽象Function自定义
-------------------|-------|------------------|----------------|--------------|------------
执行入口（Excutee）         | 用于发动Function功能的组件 |  不能 | 不能 | 不能 | 不能
参数组件（Parameter）       | 执行Function功能需要的参数数据 |  可以 | 可以 | 可以 | 可以
执行出口（Excuter）         | Function功能执行结束后，下一个被发动的Function | 不能 | 不能 | 不能 | 不能
返回值（Returnval）         | Function执行之后的数据 | 可以 | 可以 | 可以 | 可以
本地变量（LocalVar）        | 存储临时变量的引用 | 可以 | 可以 | 可以 | 不能
子Function（SubFunction）  | 内部调用的其他Function | 可以 | 可以 | 可以 | 不能
连接弧线（Arc）             | 子Function之间相互连接的弧线，包括执行弧线（定义Function之间的执行顺序）、参数弧线（定义Function之间的弧线传输顺序）| 必须 | 必须 | 必须 | 必须
注释（Comment）  | 与Function的执行功能无关，属于编辑过程中留下的的注释信息，以方块的和文字的形式对某个长方形区域进行注释 | 可以 | 可以 | 可以 | 可以

* 各个子信息块开始声明的标志

Function子信息类型 | 声明标志
------------------|-------------
执行入口（Excutee）| **Excutees:**
参数组件（Parameter）| **Parameters:**
执行出口（Excuter） | **Excuters:**
返回值（Returnval） | **Returnvals:**
本地变量（LocalVar） | **LocalVars:**
子Function（SubFunction）| **SubFunctions:**
连接弧线（Arc）  | **Arcs:**
注释（Comment） | **Comments:**

* Function的类型分类

Function类型  |  解释  | 声明字段
--------------|--------|----------
ConstructFunction| 相当于构造函数 | **ConstructFunction:**
StaticFunction | 静态Function，执行功能不依赖于RClass的实例 | **StaticFunction:** + 空格 + Function的名字
Function | 普通Function ，依靠RClass实例才能执行功能 | **Function:** + 空格 + Function的名字
AbstractFunction | 抽象Function，声明Function的外部插槽接口，内部不提供具体的功能 | **AbstractFunction：** + 空格 + Function的名字

* 举例：
```
Function: functionName
    Excutee:
        fire
        doWork
	Parameter:
        parameter1
            myPackage.RClass2
		parameter2
            basic.Integer
    Excuter:
        ExcepE:
            NULL_POINTER
            IO_EXCPETION
        NormalE:
            end
	LocalVar:
		Static:
            staticLocalVar1
                myPackage.RClass2
			staticLocalVar2
                myPackage.RClass2
		localVar3
            basic.Integer
            Init:
                -12
	SubFunction:
        fun
            myPackage.RClass1
            (-4,-7)
            GPAssign:
                T: basic.Integer
        addInteger
             basic.Integer
            (-5.1,-2.55)
            Modify:
                3
        divide
            basic.Integer
            (15.0,458)
        RClass1
            myPackage.RClass1
            (22,33)
	Arcs:
		EtoE:
             ->
                1.funEnd
                4.construct
             ->
                3.end
                1.fire
		RtoP:
             ->
                2.result
                3.by
	Comments:
		(55.1, 22.1) -> (21.0, 50.1)
			注释信息二第一行
			注释信息二第二行
		(-1, -2) -> (12, 11)
			另一个注释信息第一行
			另一个注释信息第二行
```

----

### 7. Function内部的详细字块声明
* 在介绍以下内容之前，首先明确一个组件命名规范
  * 什么是组件命名规范？不同于RClass的命名规范，组件命名一般会用于变量的名字、Function的名字、Function外部的插槽接口的名字（比如说Excutee、Parameter……），组件命名规范用于约束这些组件的名字。
  * 组件命名规范的内容：一个字符串，不包含空格、**@**、**.**（半角英文点号）、**:**、**(**、**)**、**{**、**}**、**<**、**>**。
#### 1. Excutee（执行入口）
* Excutee组件的子项不区分类型，全部为线性执行入口，每一行声明一个Excutee组件的名字，名字要遵守组件命名规范
* 每个Function至少要拥有一个执行入口。
* 举例：
```
Excutees:
    fire
    startLoop
```
 上面这个例子中声明了两个Excutee组件分别名为 **fire** 和 **startLoop** *（注：例子中只是为了展示可声明多个Excutee，目前而言，没有考虑让Function能够实现多个Excutee组件，日后可能会添加这个功能）*
#### 2. Parameter（参数）
* Parameter组件子项的声明类似变量声明，但是没有初始化值，主信息声明组件名字，第一个子信息声明组件类型，例如：
```
Parameters:
    parameter1
        myPackage.RClass2
    parameter2
        basic.Integer
```

#### 3. Excuter（执行出口）
* Excuter主要分为两种类型，一种是正常状态下的执行出口（称为*普通执行出口*），一种是发生异常现象时的执行出口（称为*异常执行出口*）。
* 每个Function都至少有一个普通执行出口。
* 至少要有一个普通执行出口

Excuter类型| 声明字段
----------|-----------
普通执行出口 | **NormalE:**
异常执行出口 | **ExcepE:**
* 同一个类型的Excuter声明放在一起，例如：
```
Excuters:
    ExcepE:
        NULL_POINTER
        IO_EXCPETION
    NormalE:
        end
```
* 用户可以为Function声明任意数目的异常执行出口，但普通执行出口一般只有一个（多个普通执行出口的实现可能放在日后）
* 一个 **Excuters:** 信息块下面最多只有两个不同类型的子信息块，不允许重复声明信息块

#### 4. Returnval（返回值）
* Returnval的声明与Parameter的声明要求一样
* 举例：
```
Returnvals:
    parameter1
        myPackage.RClass2
    parameter2
        basic.Integer
```

#### 5. LocalVar（本地变量）
* LocalVar的声明与RClass中的Members声明要求一样
* 举例：
```
LocalVars:
    Static:
        staticMember1
            myPackage.RClass2
        staticMember2
            myPackage.RClass3
    member3
        basic.Integer
        Init:
            -12
    member4
        default.myPackage.RClass4
```

#### 6. SubFun（子Function）
* SubFun的声明顺序和后面的弧线连接有关，所以声明顺序必须与弧线匹配，不能随便改动
* 一个完整的SubFun声明包含三个部分：

SubFun在工作区域的二维坐标 | RClass的名字 | Function的名字 | 修改信息
----------|--------|----------------|------------
由于本软件是用来在图形化界面下进行编程，所以这些SubFun在图形界面下都应该具有一个二维的坐标，用于在图形化的工作区域中显示这个SubFun的位置 | RClass的名字用来表明SubFun属于哪个RClass，这个部分要求符合RClass命名规范  | 指明SubFun是前面声明的RClass内部的哪个Function，这个部分要符合组件命名规范  | 单行的字符串信息，SubFun在被发动功能之前会先读取这个 *修改信息*，来对SubFun进行自定义，如何自定义？这事由SubFun来决定（或者说由写出这个Function的程序员来决定），程序只是提供一个修改信息的存储和读写方法供给Function使用，目前针对这个修改信息的一个应用，就是多针脚的加法Function，这种Function默认只有两个参数，但是通过 *修改信息*，可以为它增加更多的针脚，来适应不同数量的参数加法。
* 举例：
单个SubFun的结构：
```
Function名称
    Function所属RClass
    坐标信息
    GPAssign:
        泛参指定信息
    Modify:
        修改信息
```
```
SubFunctions:
    fun
        myPackage.RClass1
        (-4,-7)
        GPAssign:
            T: basic.Integer
    addInteger
        basic.Integer
        (-5.1,-2.55)
        Modify:
            3
    divide
        basic.Integer
        (15.0,458)
    RClass1
        myPackage.RClass1
        (22,33)
```
* 注意第二个Function就是一个加法Function，其中 *修改信息* 是 **3**，表示增加三个参数针脚。
* 二维坐标中的数字是Double类型。


#### 7. Arc（弧线）
* 每个Function都至少有一个执行弧线。
* 弧线可分为两种类型

弧线类型 | 声明符号 | 解释
-------|---------|--------
执行弧线 | **EtoE:** | 执行弧线用于连接Excutee和Excuter，这些是执行功能的顺序弧线
参数弧线 | **RtoP:** | 参数弧线用于连接Returnval和参数，是传递数据的关系弧线
* 举例
```
Arcs:
    EtoE:
         ->
            1.funEnd
            4.construct
         ->
            3.end
            1.fire
    RtoP:
         ->
            2.result
            3.by
```
* 每个弧线用一个箭头符号 **->** (注意箭头的两端都有空格)，来表示一个弧线，
* 针对上面的例子，Arc声明的具体结构：

弧线类型 | 对应SubFun序号 | 连接的SubFun外部组件名字 | 被连接SubFun序号 | 被连接的外部组件名字
--------|----------------|------------------------|-------------|------------
EtoE:  |1             | funEnd                  | 4             | construct
* **EtoE:** 和 **RtoP:** 的后面都有一个空格
* SubFun序号和外部组件名字之间用 **.**（半角英文点号）分隔，例如 **1.funEnd**
* 如果将序号和外部组件名字的 字符串组合 称作弧线的一个端点，那么弧线的两个端点之间要用一个箭头符号分隔开来 **->**，注意这个箭头符号的前后也要有空格。

#### 8. Comment（注释）
* 单个Comment表现为一个方形区域的字符串（可以包括多行），对字符串的内容没有限制
* 方形区域由两个二维坐标（左上角到右下角的位置）来表示在图形化界面下的注释范围，坐标之间用一个 *箭头符号* 来分隔
* 坐标声明下面的子信息就是详细的注释字符串，可以换行
* 规定注释不能为空，至少要有一个空格。
* 举例：
```
Comments:
    (55.1, 22.1) -> (21.0, 50.1)
        注释信息一第一行
        注释信息一第二行
        注释信息一第三行
    (-1, -2) -> (12, 11)
        另一个注释信息第一行
        另一个注释信息第二行
```

----
### 8.泛型
#### 定义
    在定义RClass的时候定义泛型，即用指定的字符串来代替未知的类型
1. 定义泛型时，可能需要增加两类信息，一个是在继承其他泛型的时候，将本类定义的泛参通过包装映射到父类的泛型当中
2. 二是对本类定义的泛参，可能需要对其进行约束，限制它至少应该继承的类型，使得在编程的时候可以通过这个最低限度的类型来调用Function
3. 允许对Functtion单独定义泛参
4. 成员变量或者Function的本地变量 的类型 定义为指定的泛参（**注：任何静态变量的类型都不能使用泛参**）
####使用
1. 调用RClass（泛型）的ConFun生成实例的时候，需要填充泛参
2. 某些Function自己声明了泛参，用户调用这些Function的时候需要手动填充这些泛型
3. 创建泛型的引用时，需要制定泛参的具体类型

#### 在脚本文件下的定义形式
######1. 首先介绍一下泛型的表示格式
第一行为泛型的全名称（或者称作实类型），此行下面的子信息块（缩进一行），然后用一个泛参名 + **:** 的形式进行泛参的指定，例如：
```
default.GenericsClassA
    M: basic.RObject
    N: basic.Integer
```
其中泛型为 **default.GenericsClassA < M, N >**，指定泛实参的时候不需要按顺序声明，而是为指定的泛参名指定类型，所以上面这段代码与下面这段代码的含义是一样的
```
default.GenericsClassA
    N: basic.Integer
    M: basic.RObject
```
泛实参也可以为另一个泛型，例如：
```
default.GenericsClassA
    N: basic.Integer
    M: default.GenericsClassB
        T: basic.String
```
上面用到的另一个泛型为 **default.GenericsClassB < T >**
######2. 在下面这一段代码中，定义了本类当中要用作泛参的泛参名，以及相应的泛型约束，这种部分也可以声明在Function中（但是每个Function不能声明和Class中相同名称的泛参，不同Function之间的泛参名允许重复）
```
GenericsParam:
    T
        default.GenericsClassA
            M: T
            N: default.GenericsClassB
                T: basic.Integer
    K
        default.GenericsClassB
            T: V
    V
        basic.RObject
    N
        V
```
在上面这段代码中，我们定义了三个泛参，他们的名字分别为<T, K, V>。  
四个信息块分别声明了四个泛参，**T**、**K**、**V**、**N**，这四个信息块下方各有一个信息块来声明一个泛型约束。
例如其中：
```
V
    basic.RObject
```
表示泛参 **V** 被约束为RObject类型，即 **V** 继承了RObject类型，可以使用它的所有方法。  
一个泛参可以被约束到任何一种具体或者不具体的类型（包括泛型）
例如：
```
N
    V
```
表示泛参 **N** 被约束到另一个泛参 **V**，所以 **N** 只有在已经被具体实例化的时候才能够检查是否符合约束 **V**。  
```
K
    default.GenericsClassB
    T: V
```
这里泛参 **K** 被约束到另一个泛型 **default.GenericsClassB < T >**，这个 **default.GenericsClassB < T >** 声明了一个泛参 **T** ，这段代码中的 **T: V** 的意思是：泛型中的泛参 **T** 用本类中的泛参 **V** 替代，假如在运行过程中，用户指明 **V** 是 **basic.Integer** 类型， 那么 **K** 就应该被约束到 **default.GenericsClassB < basic.Integer >** 。

######3. 继承泛型
只需要把相应的类型转变为泛型的声明结构就可以了，例如
```
Extends:
    default.GenericsClassB
        T: V
Implements:
    default.GenericsInterfaceA
        T: basic.Integer
    default.GenericsInterfaceB
        K: basic.String
```
上面这段代码表明，当前的RClass继承 **default.GenericsclassB < V >**，实现两个接口  
**default.GenericsInterfaceA < basic.Integer >** 和  
**default.GenericsInterfaceB < basic.String >**。

######4. 变量的类型使用泛型时的声明
只需要把相应的类型改为泛型的声明格式就可以了
```
member1
    default.GenericsClassA
        M: basic.Integer
        N: default.GenericsClassB
            T: basic.String
    Init:
        -12
```
在一行变量声明的子信息块中声明泛型信息就可以了，具体的格式参照泛型声明。  
这里声明了两个成员变量，**member2** 的类型是普通的 **basic.Integer**，  
而 **member1** 的类型是泛型 **GenericsClassA < Integer, GenericsClassB < basic.String > >**。
另外这些变量同样也可以使用RClass定义的泛参（本地变量还可以使用Function自己定义的泛参）,例如：
```
Member:
     member1
        default.GenericsClassA
            M: basic.Integer
            N: T
        Init:
            -12
```
上面的这个变量类型使用了RClass中定义的一个泛参 **T**。

######5. 调用子Function时，填充需要的泛参
* 有两种子Function需要用户手动指明泛参：
1. 定义了泛参的RClass的ConFun需要用户手动填充泛参，来指明生成的实例中的泛实参
2. 调用的子Function自己声明了泛参，这个Function声明的泛参是独立于RClass声明之外的泛参，需要用户手动指定泛实参
* 填充的方法与变量使用泛型的方法一致，只需要在子Function声明的下面的子信息块中，指定具体的泛参名以及泛实参就可以了，例如
```
SubFunctions:
     fun
        myPackage.RClass1
            T: basic.Integer
            N: default.GenericsClassB
                T: basic.String
        (-4,-7)
```
这个名为 **fun** 的Function声明了两个独立于RClass的泛参 **T** 和 **N**，我们在下面的子信息块中将 **T** 指定为 **basic.Integer**， **N** 指定为 **default.GenericsClassB < basic.String >**。





-------------------
## 声明块声明字符串查找表
信息块       |   信息块声明字符串     |    描述
------------|-----------------------|-------------
RClass类型   |**Type:**                  |
RClass名字   |**Name:**                  |
非接口父类    |**Extends:**              |
接口父类      |**Implements:**           |
成员变量      |**Members:**               |
变量声明的静态部分 | **Static:**           |   专门用于成员变量或者Function中的本地变量声明静态变量部分
执行入口（Excutee） | **Excutees:**
参数组件（Parameter）| **Parameters:**
执行出口（Excuter） | **Excuters:**
返回值（Returnval） | **Returnvals:**
本地变量（LocalVar） | **LocalVars:**
子Function（SubFunction）| **SubFunctions:**
连接弧线（Arc）  | **Arcs:**
注释（Comment） | **Comments:**
异常执行出口  | **ExcepE:** + 空格
普通执行出口 | **NormalE:** + 空格
构造Function（ConstructFunction） | **ConstructFunction:**
静态Function（StaticFunction） | **StaticFunction:** + 空格
普通Function（Function） | **Function:** + 空格
抽象Function（AbstractFunction） | **AbstractFunction:** + 空格
泛型参数（GenericsParam） | **GenericsParam:**
变量初始化信息声明（Init） | **Init:**
Function泛参指定信息（GPAssign）| **GPAssign:**
Function修改信息（Modify） | **Modify:**

## RClass名称规范
* 一行字符串
* 不包含以下字符
  不包含的字符|
  -----------|
  空格 |
  **:** |
  **@** |
  **<** |
  **>** |
  **(** |
  **)** |
  **{** |
  **}** |
* 至少包含一个 **.**（英文半角点号），因为要求所有的RClass都至少要属于某个包，每个包之间、以及包和最底层的类名之间都用 **.**（英文半角点号）来分隔，所以要求至少有一个 **.**（英文半角点号）来将唯一的一个包名和最底层的类名进行分隔。

## 组件命名规范
* 一行字符串

  不包含的字符|
  -----------|
  空格 |
  **.**  *（半角英文点号）* |
  **@** |
  **:** |
  **<** |
  **>** |
  **(** |
  **)** |
  **{** |
  **}** |
* 可以看到组件命名和RClass命名很类似，但是组件命名中不允许包含 **.**（英文半角点号）。

## Function修改信息规范

  不包含的字符|
  -----------|
  空格 |
  换行符 |
  **{** |
  **}** |

## 特别的符号

符号、或者符号模式 | 代号 | 解释
----|------|------
**:** | 声明冒号 | 此符号经常用于声明信息块，或者表明类型，请一定注意，声明冒号后面如果有其他字符，一定要空一格，比如声明弧线类型时，用 **EtoE: 1.funEnd -> 4.construct** 声明一个执行弧线，声明冒号的后面是有一个空格的。
**->** | 箭头符号 | 此符号用于弧线声明中，用于在两个SubFun的外部组件中表示连接弧线的方向，请一定注意这个符号的前后都有一个小空格，例如 **EtoE: 1.funEnd -> 4.construct** 。
**(**  数字 **,** 数字 **)** | 二维坐标  |  用于指定一个工作区域的位置，可用于注释的方形区域，或者SubFun的位置，中间不能出现任何空格

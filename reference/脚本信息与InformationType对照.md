#脚本信息对应InformationType
## 声明字段
声明字段都是完全不变的脚本信息，用于表明接下来将要声明的信息块属于什么类型的信息。  
顶层对照表：
原始脚本信息  | 脚本含义  |   InformationType
-------------|----------|---------------------
**Type:**               |   RClass的类型声明     |     DECLAR_TYPE
**Name:**               |   RClass的名称声明     |     DECLAR_NAME
**GenericsParams:**     |   RClass的泛参声明     |     DECLAR_GEN_PARAM
**Extends:**            |   继承非接口父类声明   |      DECLAR_EXTENDS
**Implements:**         |   继承接口父类声明    |    DECLAR_IMPLEMENTS
**Members:**            |   成员变量声明        |   DECLAR_MEMBERS
**ConstructFunction:**  |   构造Function声明    |   DECLAR_FUN_CONFUN
**StaticFunction:**     |   静态Function声明    |   DECLAR_FUN_STATCI
**Function:**           |   非静态Function声明  |   DECLAR_FUN
**AbstractFunction:**   |   抽象Function声明    |   DECLAR_FUN_ABSTRACT


变量区域声明块 *（RClass或者Function中声明变量区域的信息块）* 中，对照表：
原始脚本信息  | 脚本含义  |   InformationType
-------------|----------|---------------------
**Static:**  | 所有声明变量中，属于静态变量的部分    | DECLAR_VAR_STATIC

Function信息块 *（各种类型的Function声明）* 中，对照表：
原始脚本信息  | 脚本含义  |   InformationType
-------------|----------|---------------------
**Excutees:** | 执行入口声明  | DECLAR_EXCUTEES
**Parameters:** | Function对外参数声明    | DECLAR_PARAMETERS
**Excuters:**   | 执行出口声明    |   DECLAR_EXCUTERS
**Returnval**   | Function对外返回值声明   |   DECLAR_RETURNVALS
**LocalVars"**  | Function使用的本地变量声明 | DECLAR_LOCALVARS
**SubFunctions:**   | Function调用的子Fun声明 |   DECLAR_SUBFUNS
**Arcs:**       | 弧线声明      | DECLAR_ARCS
**Comments:**   |   注释声明    | DECLAR_COMMENTS

执行出口声明块 *（Excuters声明）* ，对照表：
原始脚本信息  | 脚本含义  |   InformationType
-------------|----------|---------------------
**ExcepE:**  |  异常执行出口声明 | DECLAR_EXCUTERS_EXCEPTION
**NormalE:**  |  普通执行出口声明 | DECLAR_EXCUTERS_NORMAL

弧线声明块 *（Arcs声明）* ，对照表：
原始脚本信息  | 脚本含义  |   InformationType
-------------|----------|---------------------
**EtoE:**   | 执行弧线声明    | DECLAR_ARCS_E_E
**RtoP:**   | 参数弧线声明    | DECLAR_ARCS_R_P

##可变声明字段，用于声明一个内容变化的信息
###类型声明块
表明一个类型，或者引用当前RClass *（或者Function）* 中定义的泛参，子信息中可能会包含多个泛参指定信息块。  
当声明的类型是已知的时候（没有直接使用泛参），用 **CLASS_REF_CL** 进行标记。
当声明的类型未知的时候（使用泛参），用 **CLASS_REF_GP** 进行标记。
```
default.GenericsClassA              ------ CLASS_REF_CL
    M: basic.Integer
    N: default.GenericsClassB
        T: T

//下面单独声明一个类型表示引用泛参T所指代的类型
T                                   ------ CLASS_REF_GP
```

###泛参指定块
表明一个类型的时候，如果该类型是一个泛型，我们需要为这个泛型的泛参指明使用的类型。这样一个信息块被叫做泛参指定块，根据被指定的类型，泛参指定块分为以下两种。
1. 当把一个具体的类型（泛型也可以）赋予给某个泛参时，用 **GP_ASSIGN_CL** 来标记。
2. 当把一个未知的类型赋予某个泛参时，用 **GP_ASSIGN_GP** 来标记。  

并且有如下规则， **GP_ASSIGN_CL** 标记的信息块下方还可以声明泛参指定块，但是 **GP_ASSIGN_GP** 不能有任何子信息块。
下面的代码使用了两种泛型GenericsClassA<T, K>，和GenericsClassB<T>
```
//假设当前环境中有泛参T和K
//下面声明一个类型表示
GenericsClassA
    T: basic.Integer                ------ GP_ASSIGN_CL
    K: basic.String                 ------ GP_ASSIGN_CL

GenericsClassA
    T: T                            ------ GP_ASSIGN_GP
    K: basic.String                 ------ GP_ASSIGN_CL

GenericsClassA
    T: GenericsClassB               ------ GP_ASSIGN_CL
        T: basic.Integer            ------ GP_ASSIGN_CL
    K: T                            ------ GP_ASSIGN_GP
```

###泛参声明块
泛参声明块的下方只有一个子信息块（类型声明块），表示泛型约束
```
M                                   ------ GEN_PARAM
    default.MyClass
```

###单一执行入口声明块
```
Excutee:
    fire                        ------ EXCUTEE
    doWork                      ------ EXCUTEE
```

###单一参数声明块
```
Parameter:
    parameter1                  ------VAR
        myPackage.RClass2
	parameter2                  ------VAR
        basic.Integer
```

###单一执行出口声明块
```
ExcepE:
    NULL_POINTER                ------ EXCUTER
    IO_EXCPETION                ------ EXCUTER
```

###单一返回值声明块
```
Returnvals:
    returnval1                  ------VAR
        myPackage.RClass2
	returnval2                  ------VAR
        basic.Integer
```

###SubFun声明块
SubFun声明的时候可以带有泛参指定块，用来填充Function自身声明的泛参类型
```
addInteger                          ------ SUBFUN
    basic.Integer                   ------ CLASS_REF_CL/ CLASS_REF_GP
    (-5.1,-2.55)                    ------ LOCATION
    GPAssign:                       ------ DECLAR_SUBFUN_GP_ASSIGN
        T: basic.String             ------ GP_ASSIGN_CL
    Modify:                         ------ DECLAR_FUNCTION_MODIFY
        3                           ------ MODIFY_INFO
```

###单一变量声明块
声明一个变量，指定其类型、名称和初始化值
```
member3                             ------ VAR
    basic.Integer                   ------ CLASS_REF_CL/ CLASS_REF_GP
    Init:                           ------ DECLAR_VAR_INIT
        -12                         ------ INIT_INFO
```

###单一弧线声明块
一个弧线包含两个端点
```
 ->                                 ------ ARC
    1.funEnd                        ------ ARC_OUTPORT
    4.construct                     ------ ARC_INPORT
```

###单一注释声明方块
一个注释包括两个二维坐标和不限行数的注释信息
```
(55.1, 22.1) -> (21.0, 50.1)        ------ RECT
    注释信息一第一行                 ------ COMMENT_INFO
    注释信息一第二行                 ------ COMMENT_INFO
    注释信息一第三行                 ------ COMMENT_INFO
```

#RStruct
##RStruct是用于存储RClass内部定义的所有结构体的总称，其所有的子类都必须继承一个IRStruct接口，IRStruct本身没有任何方法，只是用来标记子类属于RStruct的一种。

###所有用到的RStruct类型
RStruct类型 |   含义    
-----------|---------------
RClassStruct    |   包含一个RClass所有定义的结构
RClassRefStruct |   一个类型引用结构，类型引用可以是实类型（即一个具体的类名），或者可以是一个泛参（一个类型未知的类引用），而且，一个类型引用可能会包含泛参指定，泛参指定下面也可能还会包含泛参指定。
GPAssignStruct  |   一个泛参指定，包括被指定的泛参名，以及一个类引用（RClassRefStruct）
VarFieldStruct  |   存储变量定义的区域结构，内部分为静态变量和非静态变量。
GenParamStruct  |   一个泛参定义，内部包括泛参的名字，以及对泛参的约束，这个泛参约束一般为一个RClassRefStruct
VarStruct   |   一个变量定义，包括变量的名字、类型（RClassRefStruct）、初始化信息。
FunStruct   |   一个Function定义，内部包括Function的名字、执行入口定义……注释方块。
ExcuteeStruct   |   一个执行入口定义，Function内部用于的定义执行入口，一般只包含一个执行入口的名字。
ExcuterStruct |   一个执行出口定义，Function内部用于定义执行出口，一般只包含一个执行出口的名字。
SubFunStruct    |   一个子Function的定义，子Function被定义在FunStruct中，用于被执行功能，包含一个Fucntoin的名字、类型（RClassRefStruct）、二维坐标（LocationStruct）、泛参指定（GPAssignStruct）、修改信息（TextStruct）。
LocationStruct  |   一个二维坐标定义，包括两个浮点数
ArcFieldStruct  |   一个弧线定义，可用于定义执行弧线和参数弧线，内部包括两个端点结构。
ArcPointStruct  |   一个弧线端点定义，包括一个序号、一个组件的名字（执行端口或者参数返回值的名字）。
CommentStruct   |   一个注释区域的定义，内部包括一个方形区域的定义，以及一个个纯粹的文本区域（TextStruct）。
TextStruct  |   一个纯粹的文本结构，简单用StringBuffer进行存储。

#相关RStruct可以共用的方法
RStruct |   defineName()    |   defineType()    |    defineClassRef()   |   defineGenParams_by_RSet |   defineGPAssign_by_RSet()
--------|-------------------|-------------------|-----------------------|---------------------------|----------------------------
RClassStruct    |   V       |   V               |                       |   V
RClassRefStruct |   V   |   V   |   |   |   V
GPAssignStruct  |   V   |   V   |   V   |       |   V
FunStruct       |   V   |       |       |   V
SubFunStruct    |   V   |   |   V   |   |   V
VarStruct       |   V   |   |   V
ExcuteeStruct   |   V   |
ExcuterStruct   |   V   |
GenParamStruct  |   V   |

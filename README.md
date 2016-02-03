# 分析 Verilog 代码

# 功能

Verilog 允许在没有定义 net 就使用时直接隐式定义成 1 bit 宽的 net. 如果是 typo 造成的错误, 则很难发现.
这里利用 ANTLR 4 生成的 Verilog Parser, 检查出未定义就使用的情况.

Verilog 的 ANLTR 4 语法文件来自[官方样例](https://github.com/antlr/grammars-v4/tree/master/verilog).

# Build

按照[ANTLR 4 的文档](https://github.com/antlr/antlr4/blob/master/doc/getting-started.md)安装 ANTLR 4 并配置 Java 环境.

下面是在 shell 下编译并使用的例子

```
antlr4 Verilog2001.g4
javac *.java
java Check [SRC_FILE]
```

不支持预处理, 所以如果有宏和 include, 请先用`iverilog -E [SRC_FILE] -I [INCLUDE_DIR] -o [OUT_FILE]`进行预处理.

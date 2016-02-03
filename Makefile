GRAMMAR := Verilog2001

.PHONY: check clean

check:
	antlr4 $(GRAMMAR).g4
	javac *.java

clean:
	rm -f `find -name "$(GRAMMAR)*.java"`  # ANTLR4 生成的代码
	rm -f `find -name "*.tokens"`          # ANTLR4 产物
	rm -f `find -name "*.class"`           # javac 编译产物
	rm -f `find -name "*.out"`             # iverilog 预处理产物

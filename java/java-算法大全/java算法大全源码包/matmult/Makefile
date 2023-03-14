
all: AlgThread.class Subscript.class \
	IntMatrix.class OptMatMult.class \
	FloatingBox.class Parenthesis.class DimIndicator.class

AlgThread.class: AlgThread.java
	javac AlgThread.java

DimIndicator.class: DimIndicator.java
	javac DimIndicator.java

Parenthesis.class: Parenthesis.java
	javac Parenthesis.java

FloatingBox.class: FloatingBox.java
	javac FloatingBox.java

OptMatMult.class: OptMatMult.java
	javac OptMatMult.java

Subscript.class: Subscript.java
	javac Subscript.java

IntMatrix.class: IntMatrix.java
	javac IntMatrix.java

run:
	appletviewer test.html

clean:
	\rm -f AlgThread.class
	\rm -f DimIndicator.class
	\rm -f FloatingBox.class
	\rm -f IntMatrix.class
	\rm -f OptMatMult.class
	\rm -f Parenthesis.class
	\rm -f Subscript.class

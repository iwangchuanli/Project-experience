
all: AlgThread.class AlgAnimApp.class TextFrame.class \
	DrawingPanel.class AlgAnimFrame.class ComPanel.class \
	ComBox.class TextPanel.class LFrame.class \
	Node.class OBSearch.class IntMatrix.class \
	BinTree.class ControlPanel.class DrawingObj.class \
	OBSAnim.class

OBSAnim.class: OBSAnim.java
	javac OBSAnim.java

DrawingObj.class: DrawingObj.java
	javac DrawingObj.java

ControlPanel.class: ControlPanel.java
	javac ControlPanel.java

BinTree.class: BinTree.java
	javac BinTree.java

IntMatrix.class: IntMatrix.java
	javac IntMatrix.java

OBSearch.class: OBSearch.java
	javac OBSearch.java

Node.class: Node.java
	javac Node.java

LFrame.class: LFrame.java
	javac LFrame.java

ComBox.class: ComBox.java
	javac ComBox.java

ComPanel.class: ComPanel.java
	javac ComPanel.java

DrawingPanel.class: DrawingPanel.java
	javac DrawingPanel.java

TextFrame.class: TextFrame.java
	javac TextFrame.java

TextPanel.class: TextPanel.java
	javac TextPanel.java

AlgThread.class: AlgThread.java
	javac AlgThread.java

AlgAnimApp.class: AlgAnimApp.java
	javac AlgAnimApp.java

AlgAnimFrame.class: AlgAnimFrame.java
	javac AlgAnimFrame.java

run:
	appletviewer tts.html

clean:
	\rm -f *.class

doc: *.java
	javadoc -d ./doc *.java

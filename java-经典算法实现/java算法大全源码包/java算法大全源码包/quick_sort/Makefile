
all: AlgThread.class AlgAnimApp.class TextFrame.class \
	DrawingPanel.class AlgAnimFrame.class ComPanel.class \
	ComBox.class TextPanel.class Legend.class \
	ControlPanel.class DrawingObj.class \
	ImageButton.class ShadowLabel.class Stick.class

Legend.class: Legend.java
	javac Legend.java

Stick.class: Stick.java
	javac Stick.java

ShadowLabel.class: ShadowLabel.java
	javac ShadowLabel.java

ImageButton.class: ImageButton.java
	javac ImageButton.java

DrawingObj.class: DrawingObj.java
	javac DrawingObj.java

ControlPanel.class: ControlPanel.java
	javac ControlPanel.java

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
	appletviewer test.html

clean:
	\rm -f *.class

doc: *.java
	javadoc -d ./doc *.java

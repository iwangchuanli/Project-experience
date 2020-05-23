
all: AlgAnimApp.class TextFrame.class \
	DrawingPanel.class AlgAnimFrame.class ComPanel.class \
	ComBox.class TextPanel.class Legend.class \
	ControlPanel.class DrawingObj.class \
	ImageButton.class ShadowLabel.class \
	Histogram.class IntMatrix.class LFrame.class \
	TablePanel.class TableCanvas.class Node.class

JAVAC=javac

Node.class: Node.java
	$(JAVAC) Node.java

Histogram.class: Histogram.java
	$(JAVAC) Histogram.java

IntMatrix.class: IntMatrix.java
	$(JAVAC) IntMatrix.java

LFrame.class: LFrame.java
	$(JAVAC) LFrame.java

TablePanel.class: TablePanel.java
	$(JAVAC) TablePanel.java

TableCanvas.class: TableCanvas.java
	$(JAVAC) TableCanvas.java

Legend.class: Legend.java
	$(JAVAC) Legend.java

ShadowLabel.class: ShadowLabel.java
	$(JAVAC) ShadowLabel.java

ImageButton.class: ImageButton.java
	$(JAVAC) ImageButton.java

DrawingObj.class: DrawingObj.java
	$(JAVAC) DrawingObj.java

ControlPanel.class: ControlPanel.java
	$(JAVAC) ControlPanel.java

ComBox.class: ComBox.java
	$(JAVAC) ComBox.java

ComPanel.class: ComPanel.java
	$(JAVAC) ComPanel.java

DrawingPanel.class: DrawingPanel.java
	$(JAVAC) DrawingPanel.java

TextFrame.class: TextFrame.java
	$(JAVAC) TextFrame.java

TextPanel.class: TextPanel.java
	$(JAVAC) TextPanel.java

AlgAnimApp.class: AlgAnimApp.java
	$(JAVAC) AlgAnimApp.java

AlgAnimFrame.class: AlgAnimFrame.java
	$(JAVAC) AlgAnimFrame.java

clean:
	\rm -f *.class

doc: *.java
	javadoc -d ./doc *.java

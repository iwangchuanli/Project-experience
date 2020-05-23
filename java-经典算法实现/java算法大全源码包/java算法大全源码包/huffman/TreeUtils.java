
import java.awt.*;

class TreeUtils extends Node {

    Font bigFont, smallFont, tinyFont, hugeFont;
    DrawingPanel drawingPanel;

    public TreeUtils(DrawingPanel drawingPanel) {
	this.bigFont = drawingPanel.bigFont;
	this.smallFont = drawingPanel.smallFont;
	this.tinyFont = drawingPanel.tinyFont;
	this.hugeFont = drawingPanel.hugeFont;
	this.drawingPanel = drawingPanel;
    }

    public int treeWidth(Node node) {
        return (rightMostPosn(node) - leftMostPosn(node));
    }
 
    public int leftMostPosn(Node node) {
        if (node.isLeaf())
            return node.x;
        else
            return leftMostPosn(node.getLeftNode());
    }
 
    public int rightMostPosn(Node node) {
        if (node.isLeaf())
            return (node.x + 20);
        else
            return rightMostPosn(node.getRightNode());
    }
 
    public int treeHeight(Node node) {
        return (bottomMostPosn(node) - node.y);
    }
 
    public int bottomMostPosn(Node node) {
        if (node.isLeaf())
            return (node.y + 30);
        else {
            int rightBottom = bottomMostPosn(node.getRightNode());
            int leftBottom = bottomMostPosn(node.getLeftNode());
            return (rightBottom > leftBottom ? rightBottom : leftBottom);
        }
    }

    public void drawLeafNode(Graphics g, int x, int y,
                String label, int weight) {
        g.setColor( Color.blue );
        if (label.length() > 0) {
            g.fillRect(x, y, label.length() * 20, 30);
            g.setColor( Color.white );
            g.setFont(hugeFont);
            g.drawString(label, x+3, y+12);
            g.setFont(tinyFont);
            g.setColor( Color.yellow );
            g.drawString(""+weight, x+2, y+25);
        }
    }

    public void drawLeafNode(Graphics g, Node node) {
        int x = node.x; int y = node.y;
        String label = node.getLabel(); int weight = node.getWeight();
        if (node.highlight)
            g.setColor(Color.black);
        else
            g.setColor( Color.blue );
        if (label.length() > 0) {
            g.fillRect(x, y, label.length() * 20, 30);
            g.setColor( Color.white );
            g.setFont(hugeFont);
            g.drawString(label, x+3, y+12);
            g.setFont(tinyFont);
            g.setColor( Color.yellow );
            g.drawString(""+weight, x+2, y+25);
        }
    }
 
    public void drawNode(Graphics g, Node node) {
        if (node.highlight)
            g.setColor(Color.black);
        else
            g.setColor( Color.red );
        g.fillRect(node.x-3, node.y, 27, 30);
        g.setColor( Color.white );
        g.drawString(""+node.getWeight(), node.x-1, node.y+25);
 
        // draw links to children
        g.setColor( Color.black );
        g.drawLine(node.x + 6, node.y + 30,
                node.getLeftNode().x + 10, node.getLeftNode().y);
	if (node.highlightLeft) {
            g.drawLine(node.x + 5, node.y + 30,
                node.getLeftNode().x + 9, node.getLeftNode().y);
            g.drawLine(node.x + 4, node.y + 30,
                node.getLeftNode().x + 8, node.getLeftNode().y);
	}

        g.drawLine(node.x + 14, node.y + 30,
                node.getRightNode().x + 10, node.getRightNode().y);
	if (node.highlightRight) {
            g.drawLine(node.x + 15, node.y + 30,
                node.getRightNode().x + 11, node.getRightNode().y);
            g.drawLine(node.x + 16, node.y + 30,
                node.getRightNode().x + 12, node.getRightNode().y);
	}

        if (drawingPanel.encoding) {
            // draw 0/1 on links
            g.setColor( Color.black );
            g.setFont( smallFont );
            g.drawString("0",
                node.getLeftNode().x + 6,
                node.getLeftNode().y);
            g.drawString("1",
                node.getRightNode().x + 10,
                node.getRightNode().y);
        }
    }

    public void moveNode(Node node, int dx, int dy) {
        node.x += dx;
        node.y += dy;
        if (!node.isLeaf()) {
            moveNode(node.getLeftNode(), dx, dy);
            moveNode(node.getRightNode(), dx, dy);
        }
    }
}

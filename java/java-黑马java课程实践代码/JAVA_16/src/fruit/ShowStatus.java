package fruit;

import java.applet.Applet;
import java.awt.event.MouseEvent;

public class ShowStatus extends Applet {
    int count =  0;

    @Override
    public void init() {

    }
    public boolean mouseClicked(MouseEvent e){
        count ++;
        getAppletContext().showStatus("��ã����Ѿ������"+count+"����");
        return true;
    }
}

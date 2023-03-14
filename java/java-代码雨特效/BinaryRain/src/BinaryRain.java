

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Properties;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

@SuppressWarnings("serial")
//JDialog类时创建对话框的主要类
public final class BinaryRain extends JDialog {

    public static void main(String[] args) {
        BinaryRain r = new BinaryRain();
        r.setVisible(true);
        r.start();
    }

    private BinaryRain() {
        try {
            initProperties();
            init();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Failed to init.\n" + ex, "BinaryRain", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }

    private Color getColor(String color) {
        if (color == null || color.isEmpty())
            return null;
        if (color.startsWith("#")) {
            int i = Integer.valueOf(color.substring(1), 16);
            return new Color(i);
        }
        if (color.matches("[\\d]+[\\p{Blank}]*,[\\p{Blank}]*[\\d]+[\\p{Blank}]*,[\\p{Blank}]*[\\d]+")) {
            String[] cs = color.split("[\\p{Blank}]*,[\\p{Blank}]*");
            if (cs.length != 3)
                return null;
            int r = Integer.valueOf(cs[0]);
            int g = Integer.valueOf(cs[1]);
            int b = Integer.valueOf(cs[2]);
            return new Color(r, g, b);
        }
        return null;
    }

    private void initProperties() throws Exception {
        Properties p = new Properties();
        File f = new File(System.getProperty("user.dir") + "/BinaryRainProperties.properties");
        boolean dw = true, dh = true, df = true, db = true, dc = true, dcf = true;
        if (f.exists() && f.isFile()) {
            p.load(new FileInputStream(f));
            String strFore = p.getProperty("foreground", "default").toLowerCase();
            if (!strFore.equals("default")) {
                df = false;
                foreground = getColor(strFore);
                if (foreground == null)
                    foreground = Color.getColor(strFore, Color.GREEN);
            }
            String strBack = p.getProperty("background", "default").toLowerCase();
            if (!strBack.equals("default")) {
                db = false;
                background = getColor(strBack);
                if (background == null)
                    background = Color.getColor(strBack, Color.BLACK);
            }
            size = new Dimension();
            String strW = p.getProperty("width", "default").toLowerCase();
            if (!strW.equals("default")) {
                dw = false;
                size.width = Integer.valueOf(strW);
            }
            String strH = p.getProperty("height", "default").toLowerCase();
            if (!strH.equals("default")) {
                dh = false;
                size.height = Integer.valueOf(strH);
            }
            String strC = p.getProperty("characters", "default");
            if (!strC.equalsIgnoreCase("default")) {
                dc = false;
                String[] cs = strC.split(",");
                RAIN_CHARACTERS = new char[cs.length];
                for (int i = 0, s = RAIN_CHARACTERS.length; i < s; i++) {
                    RAIN_CHARACTERS[i] = cs[i].charAt(0);
                }
            }
            String strCF = p.getProperty("colorful", "default");
            if (!strCF.equalsIgnoreCase("default")) {
                dcf = false;
                isColorful = Boolean.parseBoolean(strCF);
            }

            String strM = p.getProperty("music", "default");
            if (!strM.equalsIgnoreCase("default")) {
                File musicFile = new File(strM);
                if (musicFile.exists() && musicFile.isFile())
                    if ((music = Applet.newAudioClip(musicFile.toURI().toURL())) != null)
                        hasMusic = true;
            }
        }
        if (dw & dh)
            size = Toolkit.getDefaultToolkit().getScreenSize();
        else if (dw)
            size.width = Toolkit.getDefaultToolkit().getScreenSize().width;
        else if (dh)
            size.height = Toolkit.getDefaultToolkit().getScreenSize().height;
        if (df)
            foreground = Color.GREEN;
        if (db)
            background = Color.BLACK;
        if (dc) {
            RAIN_CHARACTERS = new char[126 - 33 + 1];
            for (int c = 0, i = 33, l = RAIN_CHARACTERS.length; c < l; c++, i++)
                RAIN_CHARACTERS[c] = (char) i;
        }
        if (dcf)
            isColorful = false;
    }
    private Dimension size;
    private Color foreground, background;
    public char[] RAIN_CHARACTERS;
    private boolean isColorful;
    private boolean hasMusic = false;
    private AudioClip music;

    private void init() {
        setAlwaysOnTop(true);
        setResizable(false);
        setUndecorated(true);
        setTitle("Binary Rain");
        BufferedImage cursor = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB_PRE);
        setCursor(Toolkit.getDefaultToolkit().createCustomCursor(cursor, new Point(8, 8), "Disable Cursor"));
        setSize(size);
        setLocationRelativeTo(null);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent event) {
                if ((event.isAltDown() && event.getKeyCode() == KeyEvent.VK_F4) || (event.getKeyCode() == KeyEvent.VK_ESCAPE)) {
                    setVisible(false);
                    System.exit(0);
                }
            }
        });
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent event) {
                if (!isRaining())
                    stop();
                System.exit(0);
            }
        });
        add(panel, BorderLayout.CENTER);
    }

    @Override
    public void setVisible(boolean flag) {
        super.setVisible(flag);
        if (!flag)
            stop();
    }
    private Font rainFont;

    {
       
            try {
				rainFont = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("RainFont.ttf")).deriveFont(Font.BOLD, 15.0f);
			} catch (FontFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				rainFont = new Font("arial", Font.BOLD, 15);
				e.printStackTrace();
			}
        
    }

    private synchronized void newRain() {
        Rain r = new Rain(getRandomLength(), (int) (Math.random() * size.width), (int) (Math.random() * -60 * 15), (int) (Math.random() * 8 + 2), (float) (Math.random() * 10 + 10));
        rains.add(r);
        new Thread(r).start();
    }
    @SuppressWarnings({ "rawtypes", "unchecked" })
	private ArrayList<Rain> rains = new ArrayList();

    public void start() {
        if (hasMusic)
            music.loop();
        for (int c = 0, s = 108; c < s; c++)
            newRain();
        isStart = true;
        for (Rain r : rains)
            new Thread(r).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (isStart)
                    panel.repaint();
            }
        }).start();
    }

    public Dimension getFrameSize() {
        return size;
    }

    public boolean isRaining() {
        return isStart;
    }

    public int getRandomLength() {
        return (int) (Math.random() * 40 + 10);
    }

    public String getRandomChar() {
        return String.valueOf(RAIN_CHARACTERS[(int) (Math.random() * RAIN_CHARACTERS.length)]);
    }

    private void stop() {
        isStart = false;
        if (hasMusic)
            music.stop();
    }
    private RainPanel panel = new RainPanel();
    private boolean isStart = false;

    private final class RainPanel extends JPanel {

        public RainPanel() {
        }

        @Override
        public void paint(Graphics g) {
            if (isStart) {
                BufferedImage img = new BufferedImage(size.width, size.height, BufferedImage.TYPE_INT_RGB);
                Graphics2D g2 = (Graphics2D) img.getGraphics();
                g2.setColor(background);
                g2.fillRect(0, 0, size.width, size.height);
                g2.setColor(foreground);
                @SuppressWarnings("unchecked")
				Collection<Rain> collection = (Collection<Rain>) rains.clone();
                for (Iterator<Rain> it = collection.iterator(); it.hasNext();) {
                    Rain r = it.next();
                    if (r.isEnd()) {
                        rains.remove(r);
                        newRain();
                        continue;
                    }
                    if (isColorful) {
                        g2.setFont(rainFont.deriveFont(r.getSize()));
                        String[] ss = r.getRainChars();
                        int x = r.getX();
                        int y = r.getY() - ss.length * 15;
                        for (int i = 0, sss = ss.length; i < sss; i++) {
                            if (i < 7)
                                g2.setColor(COLORS[i]);
                            else
                                g2.setColor(COLORS[i % 7]);
                            g2.drawString(ss[i], x, y);
                            y += 15;
                        }
                    } else {
                        g2.setFont(rainFont.deriveFont(r.getSize()));
                        String[] ss = r.getRainChars();
                        int x = r.getX();
                        int y = r.getY() - ss.length * 15;
                        for (String s : ss) {
                            g2.drawString(s, x, y);
                            y += 15;
                        }
                    }
                }
                g.drawImage(img, 0, 0, this);
            }
        }
        private final Color[] COLORS = new Color[]{
            new Color(255, 0, 0),
            new Color(255, 165, 0),
            new Color(255, 255, 0),
            new Color(0, 255, 0),
            new Color(0, 127, 0),
            new Color(0, 127, 255),
            new Color(139, 0, 255),};
    }

    private final class Rain implements Runnable {

        public Rain(int length, int x, int y, int speed, float size) {
            if (speed < 1)
                throw new RuntimeException("The speed must be greater than or equal to 1.");
            if (length < 5)
                length = getRandomLength();
            if (size < 1.0f)
                size = 15.0f;
            rainChars = new String[length + 1];
            for (int i = 0; i < length; i++)
                rainChars[i] = getRandomChar();
            rainChars[length] = " ";
            this.rainX = x;
            this.rainY = y;
            this.rainSpeed = speed;
            this.fontSize = size;
        }

        @Override
        public void run() {
            while (isRaining() && rainY < getFrameSize().height + (rainChars.length + 1) * 15) {
                if (rainSpeed <= 0)
                    break;
                try {
                    Thread.sleep(rainSpeed);
                } catch (InterruptedException ex) {
                }
                rainY += 2;
            }
            rainSpeed = -1;
        }

        public String[] getRainChars() {
            return rainChars;
        }

        public int getX() {
            return rainX;
        }

        public int getY() {
            return rainY;
        }

        public float getSize() {
            return fontSize;
        }

        public boolean isEnd() {
            return rainSpeed <= 0;
        }
        private final String[] rainChars;
        private int rainSpeed;
        private int rainX, rainY;
        private float fontSize;
    }
}
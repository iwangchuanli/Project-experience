public class Pillar {
    Geometry  bottom;        //bottom�ǳ�����Geometry�����ı���
    double height;
    Pillar (Geometry bottom,double height) {
        this.bottom=bottom; this.height=height;
    }
    public double getVolume() {
        return bottom.getArea()*height; //bottom���Ե���������д��getArea����
    }
}







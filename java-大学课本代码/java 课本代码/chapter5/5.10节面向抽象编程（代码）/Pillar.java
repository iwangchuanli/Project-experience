public class Pillar {
    Geometry  bottom;        //bottom是抽象类Geometry声明的变量
    double height;
    Pillar (Geometry bottom,double height) {
        this.bottom=bottom; this.height=height;
    }
    public double getVolume() {
        return bottom.getArea()*height; //bottom可以调用子类重写的getArea方法
    }
}







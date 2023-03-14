public class Circle { 
    double radius,area;
    Circle(double r) {
       radius = r;
    }
    void setRadius(double r) {
        radius=r;
    } 
    double getRadius(){
        return radius;
    }
    double getArea(){
        area=3.14*radius*radius;
        return area;
    }
}

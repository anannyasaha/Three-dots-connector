import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CircleDrag extends Application {
    private Circle[] circle={new Circle(40, 40, 10),
            new Circle(140, 40, 10), new Circle(60, 140, 10)};
    private Line[] line= {new Line(),new Line(),new Line()};
    private Text[] text={new Text(),new Text(),new Text()};
    @Override
    public void start(Stage stage) throws Exception {
        Pane pane = new Pane();

        for(int i=0;i<circle.length;i++) {
            pane.getChildren().addAll(circle[i], line[i], text[i]);
        }
        Scene scene=new Scene(pane,200,200);
        stage.setScene(scene);
        stage.show();

        for (int j=0;j<circle.length;j++){
            int finalJ = j;
            circle[j].setOnMouseDragged(e->{
                if (circle[finalJ].contains(e.getX(), e.getY())) {

                   circle[finalJ].setCenterX(e.getX());
                   circle[finalJ].setCenterY(e.getY());
                   computeLines();
           /* Point2D redCenter = new Point2D(circle[finalJ].getCenterX(), circle[finalJ].getCenterY());
            Point2D mouse = new Point2D(e.getX(), e.getY());
            Point2D centerToMouse = mouse.subtract(redCenter);
            Point2D centerToNewPoint = centerToMouse.normalize().multiply(circle[finalJ].getRadius());
            Point2D newPoint = centerToNewPoint.add(redCenter);
            circle[finalJ].setCenterX(newPoint.getX());
            circle[finalJ].setCenterY(newPoint.getY());
            computeLines();*/
                }});
            }
        }


    public void computeLines(){
        line[0].setStartX(circle[0].getCenterX());
        line[0].setStartY(circle[0].getCenterY());
        line[0].setEndX(circle[1].getCenterX());
        line[0].setEndY(circle[1].getCenterY());

        line[1].setStartX(circle[0].getCenterX());
        line[1].setStartY(circle[0].getCenterY());
        line[1].setEndX(circle[2].getCenterX());
        line[1].setEndY(circle[2].getCenterY());

        line[2].setStartX(circle[1].getCenterX());
        line[2].setStartY(circle[1].getCenterY());
        line[2].setEndX(circle[2].getCenterX());
        line[2].setEndY(circle[2].getCenterY());

        double a=(new Point2D(circle[2].getCenterX(),circle[2].getCenterY())).distance(circle[1].getCenterX(),circle[1].getCenterY());
        double b=(new Point2D(circle[0].getCenterX(),circle[0].getCenterY())).distance(circle[2].getCenterX(),circle[2].getCenterY());
        double c=(new Point2D(circle[0].getCenterX(),circle[0].getCenterY())).distance(circle[1].getCenterX(),circle[1].getCenterY());

        double angle[]=new double[3];
        angle[0]=Math.acos((a*a-b*b-c*c)/(-2*b*c));
        angle[1]=Math.acos((b*b-c*c-a*a)/(-2*c*a));
        angle[2]=Math.acos((c*c-b*b-a*a)/(-2*b*a));
        for(int k=0;k<angle.length;k++){
            text[k].setX(circle[k].getCenterX());
            text[k].setY(circle[k].getCenterY()-circle[k].getRadius());
            text[k].setText(String.format("%.2f", Math.toDegrees(angle[k])));
        }


    }

}

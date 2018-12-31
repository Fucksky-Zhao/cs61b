public class NBody{
    public static double readRadius(String filename){
        In in = new In(filename);
        int N = in.readInt();
        double radius = in.readDouble();
        return radius;
    }

    public static Planet[] readPlanets(String filename){
        In in = new In(filename);
        int N = in.readInt();
        double radius = in.readDouble();
        Planet[] ps = new Planet[N];
        int i = 0;
        double xP, yP, xV, yV, m;
        String img;
        while (!in.isEmpty()){
            if (i>=N){
                break;
            }
            xP = in.readDouble();
            yP = in.readDouble();
            xV = in.readDouble();
            yV = in.readDouble();
            m = in.readDouble();
            img = in.readString();
            ps[i] = new Planet(xP, yP, xV, yV, m, img);
            i++;
        }
        return ps;
    }

    public static void main(String[] args){
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = NBody.readRadius(filename);
        Planet[] ps = NBody.readPlanets(filename);

        StdDraw.setXscale(-10*radius, 10*radius);
        StdDraw.setYscale(-10*radius, 10*radius);
        //StdDraw.setPenRadius(radius);
        StdDraw.clear();
        StdDraw.picture(0, 0, "images/starfield.jpg");
        for (Planet p: ps){
            p.draw();
        }
        StdDraw.enableDoubleBuffering();
        double curTime = 0;
        double[] xForces = new double[ps.length], yForces = new double[ps.length];
        while (curTime < T){
            for (int i = 0; i<ps.length; i++){
                xForces[i] = ps[i].calcNetForceExertedByX(ps);
                yForces[i] = ps[i].calcNetForceExertedByY(ps);
            }
            for (int i = 0; i<ps.length; i++){
                ps[i].update(curTime, xForces[i], yForces[i]);
            }
            StdDraw.picture(0, 0, "images/starfield.jpg");
            for (Planet p: ps){
                p.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
            curTime += dt;
        }
        StdOut.printf("%d\n", ps.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < ps.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                           ps[i].xxPos, ps[i].yyPos, ps[i].xxVel,
                           ps[i].yyVel, ps[i].mass, ps[i].imgFileName);
                       }
    }
}

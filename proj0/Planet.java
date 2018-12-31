public class Planet{
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    public Planet(double xP, double yP, double xV, double yV, double m, String img){
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Planet(Planet p){
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet p){
        double distance_square = (xxPos - p.xxPos)*(xxPos - p.xxPos) + (yyPos - p.yyPos)*(yyPos - p.yyPos);
        return Math.sqrt(distance_square);
    }


    public double calcForceExertedBy(Planet p){
        double g = 6.67e-11;
        double force = g*mass*p.mass / ((this.calcDistance(p))*(this.calcDistance(p)));
        return force;
    }

    public double calcForceExertedByX(Planet p){
        double dx = p.xxPos - xxPos;
        return this.calcForceExertedBy(p)*dx/this.calcDistance(p);
    }

    public double calcForceExertedByY(Planet p){
        double dy = p.yyPos - yyPos;
        return this.calcForceExertedBy(p)*dy/this.calcDistance(p);
    }

    public double calcNetForceExertedByX(Planet[] ps){
        double netForceX = 0;
        for (Planet p: ps){
            if (this.equals(p)){
                continue;
            }
            netForceX += this.calcForceExertedByX(p);
        }
        return netForceX;
    }

    public double calcNetForceExertedByY(Planet[] ps){
        double netForceY = 0;
        for (Planet p: ps){
            if (this.equals(p)){
                continue;
            }
            netForceY += this.calcForceExertedByY(p);
        }
        return netForceY;
    }

    public void update(double dt, double fX, double fY){
        double aX = fX / mass, aY = fY / mass;
        xxVel = xxVel + aX*dt;
        yyVel = yyVel + aY*dt;
        xxPos += xxVel*dt;
        yyPos += yyVel*dt;
    }

    public void draw(){
        StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
    }
}

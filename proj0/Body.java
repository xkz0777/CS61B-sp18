public class Body {
    public static final double G = 6.67e-11;

    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    public Body(double xP, double yP, double xV, double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Body(Body b) {
        xxPos = b.xxPos;
        yyPos = b.yyPos;
        xxVel = b.xxVel;
        yyVel = b.yyVel;
        mass = b.mass;
        imgFileName = b.imgFileName;
    }

    public double calcDistance(Body b) {
        return Math.sqrt((xxPos - b.xxPos) * (xxPos - b.xxPos) + (yyPos - b.yyPos) * (yyPos - b.yyPos));
    }
    
    public double calcForceExertedBy(Body b) {
        double distance = calcDistance(b);
        return G * mass * b.mass / (distance * distance);
    }

    public double calcForceExertedByX(Body b) {
        return calcForceExertedBy(b) * (b.xxPos - xxPos) / calcDistance(b);
    }

    public double calcForceExertedByY(Body b) {
        return calcForceExertedBy(b) * (b.yyPos - yyPos) / calcDistance(b);
    }

    public double calcNetForceExertedByX(Body[] bodies) {
        double netForce = 0;
        for (Body body : bodies) {
            if (!this.equals(body)) {
                netForce += calcForceExertedByX(body);
            }
        }
        return netForce;
    }

    public double calcNetForceExertedByY(Body[] bodies) {
        double netForce = 0;
        for (Body body : bodies) {
            if (!this.equals(body)) {
                netForce += calcForceExertedByY(body);
            }
        }
        return netForce;
    }

    public void update(double dt, double fX, double fY) {
        double aX = fX / mass;
        double aY = fY / mass;
        xxVel += dt * aX;
        yyVel += dt * aY;
        xxPos += dt * xxVel;
        yyPos += dt * yyVel;
    }

    public void draw() {
        StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
    }
}

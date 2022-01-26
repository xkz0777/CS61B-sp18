public class Planet {
    private static final double G = 6.67e-11;

    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    public Planet(double xP, double yP, double xV, double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Planet(Planet p) {
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet b) {
        return Math.sqrt((xxPos - b.xxPos) * (xxPos - b.xxPos) + (yyPos - b.yyPos) * (yyPos - b.yyPos));
    }
    
    public double calcForceExertedBy(Planet b) {
        double distance = calcDistance(b);
        return G * mass * b.mass / (distance * distance);
    }

    public double calcForceExertedByX(Planet b) {
        return calcForceExertedBy(b) * (b.xxPos - xxPos) / calcDistance(b);
    }

    public double calcForceExertedByY(Planet b) {
        return calcForceExertedBy(b) * (b.yyPos - yyPos) / calcDistance(b);
    }

    public double calcNetForceExertedByX(Planet[] bodies) {
        double netForce = 0;
        for (Planet body : bodies) {
            if (!this.equals(body)) {
                netForce += calcForceExertedByX(body);
            }
        }
        return netForce;
    }

    public double calcNetForceExertedByY(Planet[] bodies) {
        double netForce = 0;
        for (Planet body : bodies) {
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

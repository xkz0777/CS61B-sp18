public class NBody {
    public static double readRadius(String filename) {
        In in = new In(filename);
        in.readInt();
        double radius = in.readDouble();
        in.close();
        return radius;
    }

    public static Body[] readBodies(String filename) {
        In in = new In(filename);
        int n = in.readInt();
        Body[] bodies = new Body[n];
        in.readDouble();
        for (int i = 0; i < n; ++i) {
            bodies[i] = new Body(in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(),
                    in.readString());
        }
        in.close();
        return bodies;
    }

    public static void main(String[] args) {
        /** Read from Command line */
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = readRadius(filename);
        Body[] bodies = readBodies(filename);
        int n = bodies.length;

        String backgroundImg = "images/starfield.jpg";
        StdDraw.enableDoubleBuffering();
        StdDraw.setScale(-radius, radius);

        double t = 0; /** Discretize time */
        while (t <= T) {
            double[] xForces = new double[n];
            double[] yForces = new double[n];
            for (int i = 0; i < n; ++i) {
                xForces[i] = bodies[i].calcNetForceExertedByX(bodies);
                yForces[i] = bodies[i].calcNetForceExertedByY(bodies);
            }
            for (int i = 0; i < n; ++i) {
                bodies[i].update(dt, xForces[i], yForces[i]);
            }

            /** Draw the image starfield.jpg as the background. */
            StdDraw.clear();
            StdDraw.picture(0, 0, backgroundImg, 2 * radius, 2 * radius);
            /** Draw each one of the bodies in the `bodies` array */
            for (Body body : bodies) {
                body.draw();
            }
            
            /** Display in the standard drawing window */
            StdDraw.show();
            StdDraw.pause(10);

            t += dt;
        }

        System.out.println(n);
        System.out.printf("%.2e\n", radius);
        for (int i = 0; i < n; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n", bodies[i].xxPos, bodies[i].yyPos, bodies[i].xxVel,
                bodies[i].yyVel, bodies[i].mass, bodies[i].imgFileName);
        }
    }
}

public class NBody {
    public static double readRadius(String filename) {
        In in = new In(filename);
        in.readInt();
        double radius = in.readDouble();
        in.close();
        return radius;
    }

    public static Planet[] readPlanets(String filename) {
        In in = new In(filename);
        int n = in.readInt();
        Planet[] planets = new Planet[n];
        in.readDouble();
        for (int i = 0; i < n; ++i) {
            planets[i] = new Planet(in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(),
                    in.readString());
        }
        in.close();
        return planets;
    }

    public static void main(String[] args) {
        /** Read from Command line */
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = readRadius(filename);
        Planet[] planets = readPlanets(filename);
        int n = planets.length;

        String backgroundImg = "images/starfield.jpg";
        StdDraw.enableDoubleBuffering();
        StdDraw.setScale(-radius, radius);

        double t = 0; /** Discretize time */
        while (t <= T) {
            double[] xForces = new double[n];
            double[] yForces = new double[n];
            for (int i = 0; i < n; ++i) {
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);
            }
            for (int i = 0; i < n; ++i) {
                planets[i].update(dt, xForces[i], yForces[i]);
            }

            /** Draw the image starfield.jpg as the background. */
            StdDraw.clear();
            StdDraw.picture(0, 0, backgroundImg, 2 * radius, 2 * radius);
            /** Draw each one of the planets in the `planets` array */
            for (Planet planet : planets) {
                planet.draw();
            }
            
            /** Display in the standard drawing window */
            StdDraw.show();
            StdDraw.pause(10);

            t += dt;
        }

        System.out.println(n);
        System.out.printf("%.2e\n", radius);
        for (int i = 0; i < n; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n", planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
        }
    }
}

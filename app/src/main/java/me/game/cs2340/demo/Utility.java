package me.game.cs2340.demo;

public class Utility {
    public static double getEuclideanDistance(double point1_X, double point1_Y, double point2_X, double point2_Y) {
        return Math.sqrt(Math.pow(point1_X - point2_X, 2) + Math.pow(point1_Y - point2_Y, 2));
    }
}

package brickbreaker;

public class BallMovementPhysics {
    // Helper method for horizontal reflection.
   public static int reflectHorizontal(int pos, int size, int velocity, int leftBound, int rightBound) {
       return (pos < leftBound) || (pos + size > rightBound) ? -velocity : velocity;
   }

    // Helper method for vertical reflection.
    public static int reflectVertical(int pos, int velocity, int topBound) {
        return pos < topBound?-velocity:velocity;
    }
}

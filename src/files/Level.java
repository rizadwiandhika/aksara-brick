package files;

public class Level {

    public static final int BRICKS_ROW = 3;
    public static final int BRICKS_COL = 7;
    private static final int NUMBER_OF_LEVELS = 8;

    private static int [][][] levels = new int[NUMBER_OF_LEVELS][BRICKS_ROW][BRICKS_COL];

    public static int[][] getLevelBricks (int level) {
        fillLevel();
        return copyArray(levels[level]);
    }

    private static void fillLevel() {
        levels[0] = level0;
        levels[1] = level1;
        levels[2] = level2;
        levels[3] = level3;
        levels[4] = level4;
        levels[5] = level5;
        levels[6] = level6;
        levels[7] = level7;
    }

    private static int[][] copyArray(int arr[][]) {
        int temp[][] = new int[BRICKS_ROW][BRICKS_COL];

        for (int i = 0; i < BRICKS_ROW; i++) {
            for (int j = 0; j < BRICKS_COL; j++) {
                temp[i][j] = arr[i][j];
            }
        }
        return temp;
    }
    
    private static int[][] level0 = {
        {5, -1, -1, -1, -1, -1, -1},
		{-1, -1, -1, -1, -1, -1, -1},
        {-1, -1, -1, -1, -1, -1, -1}
    };

    private static int[][] level1 = {
        {5, -1, -1, -1, -1, -1, -1},
		{-1, -1, -1, -1, -1, -1, -1},
        {-1, -1, -1, -1, -1, 5, -1}
    };

    private static int[][] level2 = {
        {5, -1, -1, -1, -1, -1, -1},
		{-1, 5, -1, -1, -1, -1, -1},
        {-1, -1, -1, -1, -1, 5, -1}
    };

    private static int[][] level3 = {
        {5, -1, -1, -1, 5, -1, -1},
		{-1, -1, -1, 5, -1, -1, -1},
        {-1, -1, -1, -1, -1, 5, -1}
    };

    private static int[][] level4 = {
        {5, -1, -1, 5, -1, -1, -1},
		{-1, -1, 5, -1, -1, -1, -1},
        {-1, 5, -1, -1, -1, 5, -1}
    };

    private static int[][] level5 = {
        {5, -1, 5, -1, -1, -1, -1},
		{-1, -1, -1, 5, -1, 5, -1},
        {5, -1, -1, -1, -1, 5, -1}
    };

    private static int[][] level6 = {
        {5, -1, 5, -1, 5, -1, 5},
		{-1, 5, -1, -1, -1, -1, -1},
        {-1, -1, 5, -1, -1, 5, -1}
    };

    private static int[][] level7 = {
        {5, -1, -1, 5, -1, -1, 5},
		{-1, -1, 5, -1, 5, -1, -1},
        {-1, 5, -1, 5, -1, 5, -1}
    };


}

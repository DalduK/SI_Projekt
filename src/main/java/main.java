public class main {
    public static void main(String[] args) {

        int[][][]BLOCKS = {{{2, 3, 1},
                {5, 2},
                {1, 3, 1, 1},
                {2, 2, 1, 1},
                {2,1},
        }, {
                {4},
                {2, 1},
                {2, 1},
                {4},
                {3},
                {1},
                {1},
                {1, 2},
                {1, 1},
                {4},
        }};

        int[][][]maly = {{{1, 1},
                {1},
        }, {
                {1},
                {1},
                {1},
        }};


        int[][][]wielki = {{{3, 5},
                {1, 5},
                {1, 6},
                {5},
                {2, 4, 1},
                {2, 1},
                {3},
                {5, 1},
                {1},
                {2, 1, 1},
        }, {
                {1, 4, 1},
                {3, 4, 1},
                {1, 3},
                {1, 1},
                {3, 1},
                {5},
                {5, 1},
                {4, 1, 1},
                {5, 1},
                {3},
        }};
        int[][][]cross = {{
            {2},
            {3},
            {6},
            {7},
            {2, 6},
            {10},
            {11},
            {12},
            {2, 17},
            {17},
            {13, 4},
            {13, 3},
            {13},
            {14},
            {14},
            {5, 4},
            {4, 3},
            {3, 3},
            {3, 3, 2, 3},
            {7, 7},
            {19},
            {13},
        }, {
            {3},
            {5,2},
            {7,2},
            {4,3,3},
            {8,2},
            {8,2},
            {20},
            {20},
            {16,2},
            {13,2},
            {10,2},
            {8,2},
            {7,2},
            {7,2},
            {7,2},
            {7,2},
            {8,2},
            {14},
            {14},
            {2,5,2},
            {3,2},
            {4,3},
                {3,2},
                {2,2},
        }};

        int[][][] wiki = {{
                        {8, 7, 5, 7},
                        {5, 4, 3, 3},
                        {3, 3, 2, 3},
                        {4, 3, 2, 2},
                        {3, 3, 2, 2},
                        {3, 4, 2, 2},
                        {4, 5, 2},
                        {3, 5, 1},
                        {4, 3, 2},
                        {3, 4, 2},
                        {4, 4, 2},
                        {3, 6, 2},
                        {3, 2, 3, 1},
                        {4, 3, 4, 2},
                        {3, 2, 3, 2},
                        {6, 5},
                        {4, 5},
                        {3, 3},
                        {3, 3},
                        {1, 1},
                }, {
                        {1},
                        {1},
                        {2},
                        {4},
                        {7},
                        {9},
                        {2, 8},
                        {1, 8},
                        {8},
                        {1, 9},
                        {2, 7},
                        {3, 4},
                        {6, 4},
                        {8, 5},
                        {1, 11},
                        {1, 7},
                        {8},
                        {1, 4, 8},
                        {6, 8},
                        {4, 7},
                        {2, 4},
                        {1, 4},
                        {5},
                        {1, 4},
                        {1, 5},
                        {7},
                        {5},
                        {3},
                        {1},
                        {1},
                }};
        Nonogram n = new Nonogram( cross[1].length,cross[0].length, cross);
        n.solver();
        System.out.println();
        System.out.println("------------------------------------------");
        Nonogram n2 = new Nonogram( BLOCKS[1].length,BLOCKS[0].length, BLOCKS);
        n2.solver();
        System.out.println();
        System.out.println("------------------------------------------");
        Nonogram n3 = new Nonogram( wiki[1].length,wiki[0].length, wiki);
        n3.solver();
        System.out.println();
        System.out.println("------------------------------------------");
        Nonogram n4 = new Nonogram( maly[1].length,maly[0].length, maly);
        n4.solver();
        System.out.println();
        System.out.println("------------------------------------------");
        Nonogram n5 = new Nonogram( wielki[1].length,wielki[0].length, wielki);
        n5.solver();
    }
}

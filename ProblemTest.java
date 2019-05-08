//
//import org.junit.Test;
//
//import java.util.Set;
//
//import static org.junit.Assert.assertEquals;
//
//public class ProblemTest {
//
//    @Test
//    public void test1() {
//        int[][] graph = new int[][]
//                {       {1,3,12},
//                        {1,4,6},
//                        {1,2,3},
//                        {2,5,9},
//                        {2,3,1},
//                        {3,5,5},
//                        {3,6,9},
//                        {5,6,2},
//                        {4,3,6}
//                };
//
//        int[][] result = new int[][]{
//                {2,3},
//                {2,5},
//                {5,6}
//        };
//        test(result,new Problem(7,graph));
//    }
//
//    @Test
//    public void test2() {
//        int[][] graph = new int[][]
//                {       {1,3,13},
//                        {1,4,6},
//                        {1,2,3},
//                        {2,5,9},
//                        {2,3,1},
//                        {3,5,5},
//                        {3,6,9},
//                        {5,6,2},
//                        {4,3,6}
//                };
//
//        int[][] result = new int[][]{
//                {2,3},
//                {2,5},
//                {4,3},
//                {5,6}
//        };
//        test(result,new Problem(7,graph));
//
//    }
//    @Test
//    public void test3() {
//        int[][] graph = new int[][]
//                {       {1,3,12},
//                        {1,4,7},
//                        {1,2,3},
//                        {2,5,9},
//                        {2,3,1},
//                        {3,5,5},
//                        {3,6,9},
//                        {5,6,2},
//                        {4,3,6}
//                };
//
//        int[][] result = new int[][]{
//                {1,3},
//                {2,3},
//                {2,5},
//                {5,6}
//        };
//        test(result,new Problem(7,graph));
//
//    }
//
//    @Test
//    public void test4() {
//        int[][] graph = new int[][]
//                {       {1,2,1},
//                        {1,3,1},
//                        {2,4,1},
//                        {3,4,1},
//                };
//
//        int[][] result = new int[][]{
//        };
//
//        test(result,new Problem(5,graph));
//
//    }
//    @Test
//    public void test5() {
//        int[][] graph = new int[][]
//                {       {1,2,1},
//                        {1,3,1},
//                        {2,4,1},
//                        {3,4,2},
//                };
//
//        int[][] result = new int[][]{
//                {2,4}
//        };
//
//        test(result,new Problem(5,graph));
//
//    }
//
//    @Test
//    public void test6() {
//        int[][] graph = new int[][]
//                {       {1,2,1},
//                };
//
//        int[][] result = new int[][]{
//
//        };
//
//        test(result,new Problem(3,graph));
//
//    }
//
//    @Test
//    public void test7() {
//        int[][] graph = new int[][]
//                {       {1,7,1},
//                        {1,8,1},
//                        {7,9,1},
//                        {8,9,1},
//                        {1,10,2},
//                        {10,9,1},
//                        {1,4,6},
//                        {1,2,3},
//                        {1,3,12},
//                        {2,5,9},
//                        {2,3,1},
//                        {3,5,5},
//                        {3,6,9},
//                        {5,6,2},
//                        {4,3,6}
//                };
//
//        int[][] result = new int[][]{
//                {2,3},
//                {2,5},
//                {5,6},
//                {7,9},
//                {8,9}
//        };
//        test(result,new UnderControlSolver(11,graph));
//    }
//
//    public void testTime1() {
//
//    }
//
//    public void test(int[][] expected,UnderControlSolver p){
//        Set<TaskInfo> i = p.solve();
//        assertEquals(expected.length,i.size());
//        int n = 0;
//        for(TaskInfo task : i){
//            assertEquals(expected[n][0],task.inicialValue.intValue());
//            assertEquals(expected[n][1],task.finalValue.intValue());
//            n++;
//        }
//
//    }
//
//}
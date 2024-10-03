import java.util.List;
import java.util.Random;
import java.util.ArrayList;
/**---------------------------------------------------

*Akdeniz University CSE201 Data Structures Homeworks

*Yahya Efe Kurucay

*29.09.2024

*Description: HW01 

*Grade: ? 

*Website: https://efekurucay.com

*---------------------------------------------------*/
/***
 *    ███████ ███████ ███████   |    ███████ ███████ ███████ 
 *    ██      ██      ██        |    ██      ██      ██      
 *    █████   █████   █████     |    █████   █████   █████   
 *    ██      ██      ██        |    ██      ██      ██      
 *    ███████ ██      ███████   |    ███████ ██      ███████ 
 *                            
 *                            
 */
 

public class HW01_20220808005{
    public static void main(String[] args) {
        
        TrafficControl a = new TrafficControl(4, 8);
        System.out.println(a.toString());
        System.out.println(a.countMalfunctioningLights());
        System.out.println(a.mostMalfunction());
        System.out.println(a.countMalfunctioningNeighbors(2, 1));
        System.out.println(a.crucialIntersections().toString());
    
    }
}

interface ITrafficControl{
    String toString();
    List <int[]> crucialIntersections();
    int[][] getCityGrid();
    int countMalfunctioningLights();
    int countMalfunctioningNeighbors(int row, int col);
    String mostMalfunction();
}
class TrafficControl implements ITrafficControl{
    private int[][] cityGrid;


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < cityGrid.length; i++) {
            for (int j = 0; j < cityGrid[i].length; j++) {
                sb.append(cityGrid[i][j]);
                if (j < cityGrid[i].length - 1) {
                    sb.append(" "); 
                }
            }
            if (i < cityGrid.length - 1) {
                sb.append("\n"); 
            }
        }
        return sb.toString();
    }

    TrafficControl(int m, int n){
        cityGrid = new int[m][n];
        Random r = new Random();
        for (int i = 0; i<m ; i++){
            for(int j=0 ; j<n ; j++){
                cityGrid[i][j] = r.nextInt(3);
            }
        }
    
    }
    TrafficControl(int[][] cityGrid){
        this.cityGrid = cityGrid;
    }
    @Override
    public int[][] getCityGrid() {
        return cityGrid;
    }
    @Override
    public int countMalfunctioningLights() {
        int count = 0;
        for (int i = 0; i < cityGrid.length; i++) {
            for (int j = 0; j < cityGrid[i].length; j++) {
                if (cityGrid[i][j] == 1) {
                    count++;
                }
            }
        }
        return count;
    }
    @Override
    public String mostMalfunction() {
        int maxRowCount = -1;
        int maxRowIndex = -1;
        int maxColCount = -1;
        int maxColIndex = -1;

        int[] rowCounts = new int[cityGrid.length];
        int[] colCounts = new int[cityGrid[0].length];

        for (int i = 0; i < cityGrid.length; i++) {
            for (int j = 0; j < cityGrid[i].length; j++) {
                if (cityGrid[i][j] == 1) {
                    rowCounts[i]++;  
                    colCounts[j]++;  
                }
            }
        }

        for (int i = 0; i < rowCounts.length; i++) {
            if (rowCounts[i] > maxRowCount) {
                maxRowCount = rowCounts[i];
                maxRowIndex = i;
            }
        }

        for (int j = 0; j < colCounts.length; j++) {
            if (colCounts[j] > maxColCount) {
                maxColCount = colCounts[j];
                maxColIndex = j;
            }
        }

        if (maxRowCount >= maxColCount) {
            return "Row: " + maxRowIndex;
        } else {
            return "Column: " + maxColIndex;
        }
    }
    @Override
    public int countMalfunctioningNeighbors(int row, int col) {
        int count = 0;
        int[] dRow = {-1, 1, 0, 0}; 
        int[] dCol = {0, 0, -1, 1};

        for (int i = 0; i < 4; i++) {
            int newRow = row + dRow[i];
            int newCol = col + dCol[i];
            if (newRow >= 0 && newRow < cityGrid.length && newCol >= 0 && newCol < cityGrid[0].length) {
                if (cityGrid[newRow][newCol] == 1) {
                    count++;
                }
            }
        }
        return count;
    }
    @Override
    public List<int[]> crucialIntersections() {
            ArrayList<int[]> intersections = new ArrayList<>();
            for (int i = 0; i < cityGrid.length; i++) {
                for (int j = 0; j < cityGrid[i].length; j++) {
                    if (countMalfunctioningNeighbors(i, j) >= 2) {
                        intersections.add(new int[] {i, j});
                    }
                }
            }
            return intersections;
        }

    
    
    
    



}
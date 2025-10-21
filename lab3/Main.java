import java.util.*;

interface ISearchArrays {  
    int countIslands(int[][] grid);
    int searchWord(char[][] grid, String word);
}

class SearchArrays implements ISearchArrays{
    void adjasentCheck(int[][] grid,int i,int j){
        grid[i][j]=0;

        if(j<grid[0].length-1 && grid[i][j+1]==1){
            adjasentCheck(grid,i,j+1);
        }
        if(i<grid.length-1 && grid[i+1][j]==1){
            adjasentCheck(grid,i+1,j);
        }
        if(j>0 && grid[i][j-1]==1){
            adjasentCheck(grid,i,j-1);
        }
        if(i>0 && grid[i-1][j]==1){
            adjasentCheck(grid,i-1,j);
        }
        return;
    }



    boolean adjasentCheck(char[][] grid, String word,int i,int j){
        if (word.length() == 0) return true;
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length) return false;
        if (grid[i][j] != word.charAt(0)) return false;
        String next = word.substring(1);
        char temp = grid[i][j];
        grid[i][j] = '#';

        boolean found =
            adjasentCheck(grid, next, i + 1, j) ||
            adjasentCheck(grid, next, i - 1, j) ||
            adjasentCheck(grid, next, i, j + 1) ||
            adjasentCheck(grid, next, i, j - 1);
        grid[i][j] = temp;
        return found;

    }





    @Override
    public int countIslands(int[][] grid){
        if(grid == null || grid.length==0 || grid[0].length==0) return 0;
        int count = 0;
        for (int i=0;i<grid.length;i++){
            for (int j=0;j<grid[0].length;j++){
                if (grid[i][j] == 1){
                    count++;
                    adjasentCheck(grid,i,j);

                }
            }
        }
        return count;
    };

    @Override
    public int searchWord(char[][] grid, String word){
        // true is 1 and false is 0
        if(grid == null || grid.length==0 || grid[0].length==0 || word==null) return 0;

        char currentChar = word.charAt(0);

        for (int i=0;i<grid.length;i++){
            for (int j=0;j<grid[0].length;j++){
                if (grid[i][j] == currentChar) {
                    if (adjasentCheck(grid, word, i, j)) {
                        return 1;
                    }
                }
            }
        }
        return 0;
    };
}
public class Main{
    public static void main(String[] args ){
        int[][] grid = {
    {0, 0, 0},
    {1, 1, 0, 0},
    {1, 1, 0, 0},
    {0, 0, 0, 1},
    {0, 0, 1, 1}
        };
        char[][] grid2 = {
            {'a','b','x','y'},
            {'0','a','g','a'},
            {'1','t','1','1'}
        };
        ISearchArrays searchArrays = new SearchArrays();
        //System.out.println(searchArrays.countIslands(grid));
        System.out.println(searchArrays.searchWord(grid2, "baq"));// zero is false and one is true
       }
}

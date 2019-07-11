import java.io.*;
import java.util.*;

public class sat2sud{
	public static int[] parseSAT(Scanner s){
		int k;
		int xy=0;
		int[] a=new int[81];
		for (int i=1;i<=729;i++){
			k=s.nextInt();
			xy=(i-1)/9;
			if (k==i) {
				a[xy]=i%9;
				if (i%9==0)
					a[xy]=9;
			} else if (k!=-i) {
				System.out.println("Input error");
			}
		}
		return a;
	}

	public static void printSudoku(int[] a, PrintStream p){
		int k=0;
		for (int i=0;i<9;i++){
			if (i==3 || i==6){
				p.println("---------------------");
			}
			for (int j=0;j<9;j++){
				if (j==3 || j==6){
					p.print("| ");
				}
				p.print(a[k]+" ");
				k++;
			}
			p.println();			
		}
	}

	public static void main(String args[]) throws FileNotFoundException{
		Scanner s;
		PrintStream p=new PrintStream(new File("sudokusolved.txt"));
		try{
			s=new Scanner(new File("sudoku.out"));
		} catch (FileNotFoundException e){
			System.out.println("File not found.");
			return;
		}
		String a=s.next();
		if (a.equals("SAT")){
			int[] results=parseSAT(s);
			//System.out.println("Results:");
			printSudoku(results, p);
		} else {
			System.out.println("No solution");
		}
	}
}
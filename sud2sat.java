import java.io.*;
import java.util.Scanner;

public class sud2sat{
	public static void printSudoku(int[] a){
		int k=0;
		for (int i=0;i<9;i++){
			if (i==3 || i==6){
				System.out.println("---------------------");
			}
			for (int j=0;j<9;j++){
				if (j==3 || j==6){
					System.out.print("| ");
				}
				System.out.print(a[k]+" ");
				k++;
			}
			System.out.println();			
		}
	}
	public static void main(String args[]) throws FileNotFoundException{
		PrintStream p=new PrintStream(new File("sudoku.in"));
		Scanner s=null;
		try{
			s=new Scanner(new File("sudoku.txt"));
		} catch (FileNotFoundException e){
			System.out.println("File not found.");
		}

		String ss;
		
		//count inputs
		int clause=8829;
		int xy=0;
		int[] input=new int[81];
		while (s.hasNext()){
			ss=s.next();
			for (int i=0;i<ss.length();i++){
				if (ss.charAt(i)=='0' || ss.charAt(i)=='.'){
					input[xy]=0;
					xy++;
				} else {
					input[xy]=(int)ss.charAt(i)-48;
					xy++;
					clause++;
				}
			}
		}

		System.out.println("Inputed this Sudoku:");
		printSudoku(input);

		//declaration
		p.println("p cnf 729 "+clause);

		//print inputed variables
		for (int i=0;i<81;i++){
			if (input[i]!=0)
				p.println(i*9+input[i]+" 0");
		}

		//at least one number in each entry
		int a=1;
		for (int x=0;x<9;x++){
			for (int y=0;y<9;y++){
				for (int z=0;z<9;z++){
					p.print(a+" ");
					a++;
				}
				p.println(0);
			}
		}
		
		//each number appears at most once in each row
		for (int y=0;y<9;y++){
			for (int z=1;z<10;z++){
				for (int x=0;x<8;x++){
					for (int i=x+1;i<9;i++){
						p.println((-x*81-y*9-z)+" "+(-i*81-y*9-z)+" 0");
					}
				}
			}
		}

		//each number appears at most once in each column
		for (int x=0;x<9;x++){
			for (int z=1;z<10;z++){
				for (int y=0;y<8;y++){
					for (int i=y+1;i<9;i++){
						p.println((-x*81-y*9-z)+" "+(-x*81-i*9-z)+" 0");
					}
				}
			}
		}
		
		//each number appears at most once in each 3x3 sub-grid
		for (int z=1;z<10;z++){
			for (int i=0;i<3;i++){
				for (int j=0;j<3;j++){
					for (int x=0;x<3;x++){
						for (int y=0;y<3;y++){
							for (int k=y+1;k<3;k++){
								p.println(-((3*i+x)*81+(3*j+y)*9+z)+" "+(-((3*i+x)*81+(3*j+k)*9+z))+" 0");
							}
						}
					}
				}
			}
		}
		for (int z=1;z<10;z++){
			for (int i=0;i<3;i++){
				for (int j=0;j<3;j++){
					for (int x=0;x<3;x++){
						for (int y=0;y<3;y++){
							for (int k=x+1;k<3;k++){
								for (int l=0;l<3;l++){
									p.println(-((3*i+x)*81+(3*j+y)*9+z)+" "+(-((3*i+k)*81+(3*j+l)*9+z))+" 0");
								}
							}
						}
					}
				}
			}
		}
	}
}
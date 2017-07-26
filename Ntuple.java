// This alg. solve the Knapsack problem with brute force(n-tuple enumeration)
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class Ntuple {
	public static void main(String[] args) throws FileNotFoundException
	{
		int size = Integer.parseInt(args[0]);
		int[] copies = new int[size];
		int[] values = new int[size];
		int[] weights = new int[size];
		Scanner s1 = new Scanner(new File(args[1]));
		Scanner	s2 = new Scanner(new File(args[2]));
		Scanner	s3 = new Scanner(new File(args[3]));
		int j = 0;
		while(s1.hasNext()){
			copies[j] = s1.nextInt();
			values[j] = s2.nextInt();
			weights[j++] = s3.nextInt();
		}
		s1.close();
		s2.close();
		s3.close();


		int totalV = 0;
		double maxW = 0;
		int maxV = 0;
		int temp = 0;
		int[] chosen = new int[size];
		int[] output = new int[size];
		for(int i = 0; i < size; i++)
		{
			totalV += copies[i]*values[i];
			//System.out.println(copies[i] + " " + values[i] + " " + weights[i]);
		}
		maxW = totalV/2.0;
		// System.out.println("maxW~~~~" + maxW);
		while(true){
			temp = 0;
			if((chosen = addOne(copies, chosen)) == null)
				break;
			/*
			for(int i = 0; i < size; i++)
				System.out.print(chosen[i]);
			System.out.println();
			*/
			for(int i = 0; i < size; i++)
				temp += weights[i] * chosen[i];
			if(temp > maxW) // see if it is overweight
				continue;
			else{
				for(int i = 0; i < size; i++)
					temp += values[i] * chosen[i];
				// see if value is max
				if(temp > maxV){
					maxV = temp;
					for(int i = 0; i < size; i++)
						output[i] = chosen[i];
				}
			}
		}
		for(int i = 0; i < size; i++)
			System.out.print(output[i] + " ");
		System.out.println();

	}

	private static int[] addOne(int[] A, int[] P){
		for(int i = 0; i < A.length; i++){
			if(P[i] < A[i]){
				P[i]++;
				return P;
			}
			else
				P[i] = 0;
		}
		return null;
	}



}

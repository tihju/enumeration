import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Subset
{
	public static void main(String[] args) throws FileNotFoundException
	{
		int size = Integer.parseInt(args[0]);
		int totalV = 0;
		double maxW = 0;
		int copyNum = 0;

		int[] copies = new int[size];
		int[] values = new int[size];
		int[] weights = new int[size];

		Scanner scan1 = new Scanner(new File(args[1]));
		Scanner scan2 = new Scanner(new File(args[2]));
		Scanner scan3 = new Scanner(new File(args[3]));

		int t = 0;
		while(scan1.hasNext())
		{
			copies[t] = scan1.nextInt();
			values[t] = scan2.nextInt();
			weights[t++] = scan3.nextInt();
		}
		scan1.close(); scan2.close(); scan3.close();



		for(int i = 0; i < size; i++)
		{
			totalV += copies[i] * values[i];
			copyNum += copies[i];

		}

		maxW = totalV/2.0;

		// variables for records the result;
		int[] itemV = new int[copyNum];
		int[] itemW = new int[copyNum];
		int[] chosen = new int[copyNum];
		int[] output = new int[copyNum];

		// transform the array
		for(int i = 0, k = 0; i < size; i++)
		{
			for(int j = 0; j < copies[i]; j++)
			{
				itemV[k] = values[i];
				itemW[k++] = weights[i];
			}
		}


		// start now
		int tempW = 0, maxV = 0;
		totalV = 0;

		while(true)
		{
			tempW = 0;
			totalV = 0;
			if((chosen = addOne(chosen)) == null)
				break;

			for(int i = 0; i < copyNum; i++)
				tempW += itemW[i] * chosen[i];

			if(tempW > maxW)
				continue;
			else
			{
				for(int i = 0; i < copyNum; i++)
					totalV += itemV[i] * chosen[i];

				if(totalV > maxV)
				{
					maxV = totalV;
					for(int i = 0; i < copyNum; i++)
						output[i] = chosen[i];
				}
			}
		}


		// separate the chosen and group them together
		int[] result = new int[size];
		int tmp = 0;

		for(int i = 0, index = 0; i < size; i++)
		{
			tmp = 0;

			for(int j = 0; j < copies[i]; j++)
				tmp += output[index++];

			result[i] = tmp;

			// output the result
	//		System.out.println(result[i]);
		}


		// test output **********************************************************
		//System.out.println("max weight: " + maxW);
		//System.out.println("max value: " + maxV);
		for(int i=0; i<size; i++)
		{
			System.out.print( result[i] + " ");
		}
		System.out.println();
		//***********************************************************************

	}

	private static int[] addOne(int[] P)
	{
		for(int i = 0; i < P.length; i++)
		{
			if(P[i] < 1)
			{
				P[i]++;
				return P;
			}
			else
				P[i] = 0;
		}
		return null;
	}
}

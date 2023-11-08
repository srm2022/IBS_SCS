//    <SCS software includes two solvers for the Shortest Common Supersequence (SCS) problem>
//    Copyright (C) <2010>  <Sayyed Rasoul Mousavi, Fateme Bahri, Farzaneh Sadat Tabataba>
//
//    This program is free software: you can redistribute it and/or modify
//    it under the terms of the GNU General Public License as published by
//    the Free Software Foundation, either version 3 of the License, or
//    (at your option) any later version.
//
//    This program is distributed in the hope that it will be useful,
//    but WITHOUT ANY WARRANTY; without even the implied warranty of
//    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//    GNU General Public License for more details.
//
//    You should have received a copy of the GNU General Public License
//    along with this program.  If not, see <http://www.gnu.org/licenses/>.
//
//    <contact : srm@cc.iut.ac.ir, f.bahri@ec.iut.ac.ir, f.tabataba@ec.iut.ac.ir>

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.PrintWriter;

/**
 * @author a
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

public class Run 
{
	public static void main(String[] args) throws IOException 
	{
		//args for IBS_SCS = InputFile string_type IBS_SCS/DR beam_size num_best OutputFile(optional)
		//args for DR =      InputFile string_type IBS_SCS/DR OutputFile(optional)
		if(!(args.length==3 || args.length==4||args.length==5 ||args.length==6 ))
		{
			System.out.println("ERROR : arguments are  wrong !!! please read \"readme.txt\" file");
			return;
		}
		
		String InputFile = args[0];		
		int strings_num = 0;
		try
		{
		LineNumberReader reader  = new LineNumberReader(new FileReader(InputFile));				
		while (( reader.readLine()) != null)
		{
			strings_num++;
		}		
		reader.close();

		}
		catch(FileNotFoundException E)
		{
			System.out.println("ERROR : input file not found ! please type the input file path and name as the first argument");
			return;
		}
		


		if(!(args[1].equalsIgnoreCase("D")||args[1].equalsIgnoreCase("P")||args[1].equalsIgnoreCase("O")))
		{
			System.out.println("ERROR : argument 2 should be \"D\" or \"P\" or \"O\", \"D\" if strings are real DNA, \"P\" if strings are real Protein and \"O\" if strings are not any of them.");
			return;
		}
		if(!(args[2].equalsIgnoreCase("DR") || args[2].equalsIgnoreCase("IBS_SCS")))
		{
			System.out.println("ERROR : argument 3 should be DR or IBS_SCS !!!");
			return;
		}
		if(args[2].equalsIgnoreCase("DR")&& !(args.length==3 || args.length==4))
		{
			System.out.println("ERROR : for run DR arguments should be : InputFile string_type IBS_SCS/DR OutputFile(optional) ");
			return;
		}
		if(args[2].equalsIgnoreCase("IBS_SCS")&& !(args.length==5 || args.length==6))
		{
			System.out.println("ERROR : for run IBS_SCS arguments should be : InputFile string_type IBS_SCS/DR beam_size num_best OutputFile(optional) ");
			return;
		}


	
		String string_type = args[1];
		String DR_IBS_SCS = args[2];
		String OutputFile;	
		long tStart,tEnd;
		double Run_time;

		if(DR_IBS_SCS.equalsIgnoreCase("IBS_SCS"))// IBS_SCS
		{
			String beam_size_s = args[3];
			int beam_size;			
			try
			{
				beam_size= Integer.parseInt(beam_size_s);	
			}
			catch(NumberFormatException E)
			{
				System.out.println("ERROR : argument 4 is beam size , so an integer should be typed. ");
				return;
			}
			String num_best_s = args[4];
			int num_best;
			try
			{
				num_best= Integer.parseInt(num_best_s);
			}
			catch(NumberFormatException E)
			{
				System.out.println("ERROR : argument 5 is number of best children for dominance pruning , so an integer should be typed. ");
				return;
			}

			if(args.length==6)
			{
				OutputFile = args[5];
			}
			else
			{
				String temp;
				if(InputFile.lastIndexOf('.')!= -1)
					temp = InputFile.substring( 0 , InputFile.lastIndexOf('.') );
				else
				{
					temp = InputFile;
				}
				OutputFile = temp + "_IBS_SCS_Result.txt";
			}
			IBS_SCS objSCS= null;
			tStart= System.currentTimeMillis();	   	    
			objSCS=new IBS_SCS(strings_num,InputFile,string_type);
			objSCS.mainMethod(beam_size,num_best);
			tEnd= System.currentTimeMillis();	
			Run_time=(tEnd-tStart);	
			String output = "";
			System.out.println();
			output="SCS length is " + objSCS.retLen +"; Run time is : "+ Run_time + " milliseconds" ;
			System.out.println(output);
			
			output=output+ "; obtained SCS is printed here :  ";
			for(int i = 0 ; i < objSCS.retLen ; i++ )
			{
				output = output + (objSCS.Alphabet.charAt( objSCS.Global_finalSol.X[i] ) ) ;
			}		
			int ret=Myprint(output,OutputFile);
			if(ret==1)
			{
				System.out.println();
				System.out.println("obtained SCS is printed in : "+OutputFile);
				System.out.println();
			}			
			
		}
		if(DR_IBS_SCS.equalsIgnoreCase("DR"))//DR
		{
			if(args.length==4)
			{
				OutputFile = args[3];
			}
			else
			{
				String temp;
				if(InputFile.lastIndexOf('.')!= -1)
					temp = InputFile.substring( 0 , InputFile.lastIndexOf('.') );
				else
				{
					temp = InputFile;
				}
				OutputFile = temp + "_DR_Result.txt";
			}
			DR objSCS= null;
			tStart= System.currentTimeMillis();	   	    
			objSCS=new DR(strings_num,InputFile,string_type);
			objSCS.mainMethod();
			tEnd= System.currentTimeMillis();	
			Run_time=(tEnd-tStart);	
			String output = "";
			System.out.println();
			output="SCS length is " + objSCS.retLen +"; Run time is : "+ Run_time + " milliseconds" ;
			System.out.println(output);			
			output=output+ "; obtained SCS is printed here :  ";
			for(int i = 0 ; i < objSCS.retLen ; i++ )
			{
				output = output + (objSCS.Alphabet.charAt( objSCS.Global_finalSol.X[i] ) ) ;
			}		
			int ret=Myprint(output,OutputFile);
			if(ret==1)
			{
				System.out.println();
				System.out.println("obtained SCS is printed in : "+OutputFile);
				System.out.println();
			}
		}


	}//main

	
	public static int Myprint(String st, String Outputfile)
	{
		PrintWriter pr;	
		try
		{
			pr = new PrintWriter(new FileWriter(Outputfile , false ));			
			pr.println(st );
			pr.close();

		}catch(FileNotFoundException e)
		{
			System.out.println("ERROR : output file not found ! please type the output file path and name as the last argument");
			return -1;
		}	 
		catch(IOException e)
		{
			System.out.println("ERROR in printing output");
			return -1;
		}	 
		return 1;
	}

}
# IBS_SCS
    <SCS software includes two solvers for the Shortest Common Supersequence (SCS) problem>
    Copyright (C) <2010>  <Sayyed Rasoul Mousavi, Fateme Bahri, Farzaneh Sadat Tabataba>

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    
This repository contains the original Java source files used in the paper introducing the IBS_SCS algorithm for the Shortest Common Super-sequence problem:
Mousavi, S. R., Bahri, F., & Tabataba, F. S. (2012). An enhanced beam search algorithm for the Shortest Common Supersequence Problem. Engineering Applications of Artificial Intelligence, 25(3), 457-467.

Information about the DR algorithm may be found in:
Ning, K., & Leong, H. W. (2006). Towards a better solution to the shortest common supersequence problem: the deposition and reduction algorithm. BMC bioinformatics, 7, 1-11.

Comments and questions about IBS_SCS or these java files may be emailed to seyed_r_mousavi@yahoo.com (as long as alive).

This software includes two solvers called DR and IBS-SCS for the Shortest Common Supersequence (SCS) problem. The executable file name is 'Run', and it is located in the bin folder.

To run the software simply type: 
Java Run arg1 arg2 arg3 …

The arguments are separated by space and defined as follows:

arg1 is the input data filename. If not in the same directory, include the full path. If there is space in the path, put the whole path and filename with quotation marks "" (because space is the delimiter of the arguments). The accepted format of data files is a text file which simply consists of lines of strings without any additional information about their number and lengths; these will be determined automatically.

arg2 is the data type. Use “D” for real DNAs, “P” for real Proteins, and “O” otherwise. The reason for discriminating between real and random DNA/Proteins is that real DNAs/Proteins may include unknown characters- such as N as an unknown nucleotide- and are therefore treated differently.

arg3 is either "DR" for the DR algorithm or "IBS_SCS" for the "IBS-SCS" algorithm. The latter is recommended because of higher (average) quality and speed. The definitions for the subsequent arguments depend on the selected value for this argument. 

arg4: if arg3 is "DR", then arg4 is optional and intended for the output filename. If not specified, the output filename by default will be the same as the input filename with "_DR_Result" at its end. For example, if input filename is infile1.txt, then the default output filename will be infile1_DR_Result.txt. If there is space in the path, put the whole path and filename with quotation marks "" (because space is the delimiter of the arguments).

If arg3 is "IBS-SCS", then arg4 specifies the beam size parameter.

arg5: this is used only if arg3 is "IBS-SCS" and specifies the k parameter. The value of k is the number of (best) partial solutions which are used as potential dominators for the existing partial solutions.

arg6: this is also used only if arg3 is "IBS-SCS" and is intended for the output filename. If not specified, the output filename by default will be the same as the input filename with "_IBS_SCS_Result" at its end. For example, if input filename is infile1.txt, then the default output filename will be infile1_IBS_SCS_Result.txt. If there is space in the path, put the whole path and filename with quotation marks "" (because space is the delimiter of the arguments).

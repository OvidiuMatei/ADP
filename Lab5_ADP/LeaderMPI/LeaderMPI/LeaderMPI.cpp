// LeaderMPI.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"
#include "mpi.h"
#include <stdio.h>
#include <stdlib.h>
#include <time.h>

#define LEN 100
#define MASTER 0


int main(int argc, char** argv)
{
	float val[LEN];			/*vector local de valori*/
	int count = 100;				/*numar local de valori*/
	int myrank, maxrank, maxindex;
	float maxval;
	srand(unsigned int(time(NULL)));

	struct {
		float value;
		int	  index;
	}in, out;
	/*maxim local*/

	for (int i = 0; i < LEN; i++){
		val[i] = float(rand() % LEN);
	}
	in.value = val[0];
	in.index = 0;

	for (int i = 1; i < count; i++)
	if (in.value <= val[i]) {
		in.value = val[i];
		in.index = i;
	}
	/*maxim global*/
	MPI_Init(&argc, &argv);
	MPI_Comm_rank(MPI_COMM_WORLD, &myrank);
	in.index = myrank*LEN + in.index;
	MPI_Reduce(&in, &out, 1, MPI_FLOAT_INT, MPI_MAXLOC, MASTER, MPI_COMM_WORLD);

	if (myrank == MASTER){
		maxval = out.value;
		maxrank = out.index / LEN;
		maxindex = out.index % LEN;

		printf("The leader process is %d, value = %d, index = %d", maxrank, maxval, maxindex);
	}


	MPI_Finalize();
}


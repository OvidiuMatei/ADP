#include "stdafx.h"
#include "mpi.h"
#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <random>

#define N 20
#define MASTER 0

int main(int argc, char *argv[]) {

	int rank, nrProcs, i, totalSum = 0, slice;
	int array[N];

	MPI_Init(&argc, &argv);
	MPI_Comm_rank(MPI_COMM_WORLD, &rank);
	MPI_Comm_size(MPI_COMM_WORLD, &nrProcs);

	slice = N / nrProcs;

	for (i = 0; i < N; i++)
	{
		array[i] = rand() % 10 + 1;
	}

	if (rank != MASTER){
		int tempSum = 0;

		for (i = 0; i < slice; i++) {
			tempSum += array[rank * slice + i];
		}

		printf("Rank #%d sending value %d\n", rank, tempSum);
		MPI_Send(&tempSum, 1, MPI_INT, MASTER, 0, MPI_COMM_WORLD);

	}
	else {
		printf("MASTER receiving values:\n");

		int auxSum;

		for (i = 1; i < nrProcs; i++) {
			MPI_Recv(&auxSum, 1, MPI_INT, i, 0, MPI_COMM_WORLD, MPI_STATUS_IGNORE);

			printf("Received %d from rank #%d\n", auxSum, i);

			totalSum += auxSum;
		}

		printf("Total Sum = %d\n", totalSum);
	}
	MPI_Finalize();
}
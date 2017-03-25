/*
 * =====================================================================================
 *
 *       Filename:  run.c
 *
 *    Description:  
 *
 *        Version:  1.0
 *        Created:  03/18/2017 20:10:37
 *       Revision:  none
 *       Compiler:  gcc
 *
 *         Author:  Cassie, 
 *   Organization:  none
 *
 * =====================================================================================
 */
#include <stdlib.h>
#include <stdio.h>
#include <math.h>

void run(int l, int r) {
	float remain, angel;
	remain = fmod((float)l, 2*M_PI*r);

	angel = remain/(2*M_PI*r)*360;

	printf("angel %f\n", angel);

	if (0 == angel || 360 == angel) {
		printf("x: %f y: %f\n", (float)0, (float)r);
	} else if (angel < 90) {
		printf("x: %f y: %f\n", -sin(angel)*r, cos(angel)*r);
		printf("x: %f y: %f\n", sin(angel)*r, cos(angel)*r);
	} else if (90 == angel) {
		printf("x: %f y: %f\n", -(float)r, (float)0);
		printf("x: %f y: %f\n", (float)r, (float)0);
	} else if (angel < 180) {
		printf("x: %f y: %f\n", -sin(angel)*r, -cos(angel)*r);
		printf("x: %f y: %f\n", sin(angel)*r, -cos(angel)*r);
	} else if (180 == angel) {
		printf("x: %f y: %f\n", (float)0, -(float)r);
	} else if (angel < 270) {
		printf("x: %f y: %f\n", sin(angel)*r, -cos(angel)*r);
		printf("x: %f y: %f\n", -sin(angel)*r, -cos(angel)*r);
	} else if (270 == angel) {
		printf("x: %f y: %f\n", (float)r, (float)0);
	} else if (angel < 360) {
		printf("x: %f y: %f\n", sin(angel)*r, cos(angel)*r);
		printf("x: %f y: %f\n", -sin(angel)*r, cos(angel)*r);
	}
}

int main(void)
{
	int l, r;
	scanf("%d%d", &l, &r);
	run(l, r);
}


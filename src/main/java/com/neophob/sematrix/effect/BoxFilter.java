/**
 * Copyright (C) 2011 Michael Vogt <michu@neophob.com>
 *
 * This file is part of PixelController.
 *
 * PixelController is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * PixelController is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with PixelController.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.neophob.sematrix.effect;



/**
 * The Class BoxFilter.
 */
public final class BoxFilter  {

	/** The boxfiltr. */
	private static float[] boxfiltr = new float[]{
		0.0f,  0.0f,  0.0f,  0.0f,  0.0f,		//0
		0.0f,  0.0f,  0.125f,  0.0f,  0.0f,     //smooth h+v
		0.0f,  0.125f,  0.5f,  0.125f,  0.0f,
		0.0f,  0.0f,  0.125f,  0.0f,  0.0f,
		0.0f,  0.0f,  0.0f,  0.0f,  0.0f,

		0.0f,  0.0f,  0.0f,  0.0f,  0.0f,		//1
		0.0f,  0.0f,  0.0f,  0.0f,  0.0f,      	//horizontal smooth
		0.0f,  0.25f,  0.5f,  0.25f,  0.0f,
		0.0f,  0.0f,  0.0f,  0.0f,  0.0f,
		0.0f,  0.0f,  0.0f,  0.0f,  0.0f,

		0.0f,  0.0f,  0.0f,  0.0f,  0.0f,		//2
		0.0f,  0.0f,  0.25f,  0.0f,  0.0f,    	//vertical smooth
		0.0f,  0.0f,  0.5f,  0.0f,  0.0f,
		0.0f,  0.0f,  0.25f,  0.0f,  0.0f,
		0.0f,  0.0f,  0.0f,  0.0f,  0.0f,

		0.04f,  0.04f,  0.04f,  0.04f,  0.04f,	//3
		0.04f,  0.04f,  0.04f,  0.04f,  0.04f,  //gaussian
		0.04f,  0.04f,  0.04f,  0.04f,  0.04f,
		0.04f,  0.04f,  0.04f,  0.04f,  0.04f,
		0.04f,  0.04f,  0.04f,  0.04f,  0.04f,

		0.0f,  0.0f,  0.0f,  0.0f,  0.0f,		//4
		0.0f,  0.0f,  0.0f,  0.0f,  0.0f,    	//horizontal blur
		0.02f,  0.2f,  0.2f,  0.2f,  0.2f,
		0.0f,  0.0f,  0.0f,  0.0f,  0.0f,
		0.0f,  0.0f,  0.0f,  0.0f,  0.0f,

		0.0f,  0.0f,  0.2f,  0.0f,  0.0f,		//5
		0.0f,  0.0f,  0.2f,  0.0f,  0.0f,  		//vertical blur
		0.0f,  0.0f,  0.2f,  0.0f,  0.0f,
		0.0f,  0.0f,  0.2f,  0.0f,  0.0f,
		0.0f,  0.0f,  0.2f,  0.0f,  0.0f,

		0.0f,  0.0f,  0.0f,  0.0f,  0.0f,		//6
		0.0f,  -1.0f, -1.0f,  1.0f,  0.0f,     	//embossfilter
		0.0f,  -1.0f,  0.0f,  1.0f,  0.0f,
		0.0f,  -1.0f,  1.0f,  1.0f,  0.0f,
		0.0f,  0.0f,  0.0f,  0.0f,  0.0f,

		0.0f,  0.0f,  0.0f,  0.0f,  0.0f,		//7
		0.0f,  -0.125f,-0.125f,-0.125f,  0.0f,  //Edge Detection 1
		0.0f,  -0.125f,1.0f,  -0.125f,  0.0f,
		0.0f,  -0.125f,-0.125f,-0.125f,  0.0f,
		0.0f,  0.0f,  0.0f,  0.0f,  0.0f,

		0.0f,  -1.0f, -1.0f,  -1.0f,  0.0f,		//8
		-1.0f,  1.5f, 1.5f,   1.5f,  -1.0f,    	//Edge Detection 2
		-1.0f,  1.5f, 3.0f,   1.5f,  -1.0f,
		-1.0f,  1.5f, 1.5f,   1.5f,  -1.0f,
		0.0f,  -1.0f, -1.0f,  -1.0f,  0.0f,

/*		0.0f,  0.0f,  0.0f,  0.0f,  0.0f,		//8
		0.0f,  0.0f,  -1.0f,  0.0f,  0.0f,    	//Edge Detection 2
		0.0f,  -1.0f,  4.0f,  -1.0f,  0.0f,
		0.0f,  0.0f,  -1.0f,  0.0f,  0.0f,
		0.0f,  0.0f,  0.0f,  0.0f,  0.0f,
*/	
		0.0f,  0.0f,  0.0f,  0.0f,  0.0f,		//9
		0.0f,  0.0f,  0.0f,  0.0f,  0.0f,    	//Move Left
		0.0f,  1.0f,  0.0f,  0.0f,  0.0f,
		0.0f,  0.0f,  0.0f,  0.0f,  0.0f,
		0.0f,  0.0f,  0.0f,  0.0f,  0.0f,

		0.0f,  0.0f,  0.0f,  0.0f,  0.0f,		//10
		0.0f,  0.0f,  1.0f,  0.0f,  0.0f,    	//Move Up
		0.0f,  0.0f,  0.0f,  0.0f,  0.0f,
		0.0f,  0.0f,  0.0f,  0.0f,  0.0f,
		0.0f,  0.0f,  0.0f,  0.0f,  0.0f,

		2.0f,  0.0f,  -1.0f,  0.0f,  2.0f,		//11
		0.0f,  2.0f,  -1.0f,  2.0f,  0.0f,   	//textil
		-1.0f,  -1.0f,  2.0f,  -1.0f,  -1.0f,
		0.0f,  2.0f,  -1.0f,  2.0f,  0.0f,
		2.0f,  0.0f,  -1.0f,  0.0f,  2.0f,

		0.0f,  0.0f,  0.0f,  0.0f,  0.0f,		//12
		0.0f,  -1.0f,  -1.0f,  -1.0f,  0.0f,    //sharpen
		0.0f,  -1.0f,  16.0f,  -1.0f,  0.0f,
		0.0f,  -1.0f,  -1.0f,  -1.0f,  0.0f,
		0.0f,  0.0f,  0.0f,  0.0f,  0.0f,

		0.0f,  0.0f,  0.0f,  0.0f,  0.0f,		//13
		0.0f,  1.0f,  1.0f,  1.0f,  0.0f,    	//Subtle Edges
		0.0f,  1.0f,  -2.0f,  1.0f,  0.0f,
		0.0f,  1.0f,  1.0f,  1.0f,  0.0f,
		0.0f,  0.0f,  0.0f,  0.0f,  0.0f,

		0.0f,  0.0f,  0.0f,  0.0f,  0.0f,		//14
		0.0f,  0.0f,  0.0f,  0.0f,  0.0f,    	//intens
		0.0f,  0.0f,  3.0f,  0.0f,  0.0f,
		0.0f,  0.0f,  0.0f,  0.0f,  0.0f,
		0.0f,  0.0f,  0.0f,  0.0f,  0.0f,  
	};


	/** add this value to the result. */
	private static short[] bfOffset = new short[]{  
		0,
		0,
		0,
		0,
		0,
		0,
		128,
		0,
		0,
		0,
		0,
		0,
		0,
		0,
		0
	};

	/** divide the result with this value. */
	private static short[] bfDivide = new short[] {  
		1,
		1,
		1,
		1,
		1,
		1,
		1,
		1,
		1,
		1,
		1,
		8,
		16,
		6,
		2
	};
	
	/**
	 * Instantiates a new box filter.
	 */
	private BoxFilter() {
		//util class - hide constructor
	}

	/**
	 * Apply box filter.
	 *
	 * @param art the art
	 * @param anz the anz
	 * @param src the src
	 * @param lineSize the line size
	 * @return the int[]
	 */
	public static int[] applyBoxFilter(int art, int anz, int src[], int lineSize) {	
		int buffertmp[] = new int[src.length];

		int IMG_SIZE = src.length;
		int LINE = lineSize;
		int COLOR_DEPTH = 1;

		int[] boxoffset = new int[] { 
				IMG_SIZE-2*LINE-COLOR_DEPTH*2,  IMG_SIZE-2*LINE-COLOR_DEPTH,  IMG_SIZE-2*LINE,  IMG_SIZE-2*LINE+COLOR_DEPTH,  IMG_SIZE-2*LINE+COLOR_DEPTH*2,
				IMG_SIZE-LINE-COLOR_DEPTH*2,    IMG_SIZE-LINE-COLOR_DEPTH,    IMG_SIZE-LINE,    IMG_SIZE-LINE+COLOR_DEPTH,    IMG_SIZE-LINE+COLOR_DEPTH*2,
				IMG_SIZE-COLOR_DEPTH*2,         IMG_SIZE-COLOR_DEPTH,         0,                COLOR_DEPTH,          COLOR_DEPTH*2,
				LINE-COLOR_DEPTH*2,             LINE-COLOR_DEPTH,             LINE,             LINE+COLOR_DEPTH,        LINE+COLOR_DEPTH*2,
				2*LINE-COLOR_DEPTH*2,           2*LINE-COLOR_DEPTH,           2*LINE,           2*LINE+COLOR_DEPTH,        2*LINE+COLOR_DEPTH*2 
		};
		
		float f;
		int index, val;
		short valr, valg, valb;

		for (int n=0; n<anz; n++) {			
			index=0;
			for (int y=0; y<lineSize; y++) {
				for (int x=0; x<lineSize; x++) {
					valr = bfOffset[art];
					valg = bfOffset[art];
					valb = bfOffset[art];
					
					for (int ofsn=0; ofsn< 25; ofsn++){
						f = boxfiltr[ofsn+art*25];
						val = src[(index + boxoffset[ofsn])%(IMG_SIZE)];
						valr += (short)(f * ((val>>16) & 255));
						valg += (short)(f * ((val>> 8) & 255));
						valb += (short)(f * ((val    ) & 255));
					}      
					
					valr /= bfDivide[art];
					valg /= bfDivide[art];
					valb /= bfDivide[art];
					
					if (valr>255) valr = 255;
					if (valg>255) valg = 255;
					if (valb>255) valb = 255;

					if (valr<0) valr = 0;
					if (valg<0) valg = 0;
					if (valb<0) valb = 0;

					//val/= bf_divide[art];
					//if (val<0) val=0;
					//if (val>255) val=255;
					buffertmp[index] = (int)(valr << 16) | (valg << 8) | valb;
					index++;
				}
			}
		}
		return buffertmp;
	}

	
}
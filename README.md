# Using UI
Click on MenuBar which include file, generator and operators. 
Load image through open first then select the operator.
In Red Eye Removel, drag on the image. There will be a red rectangle drawed, inside which is the selected aera to apply red eye removel.
Click on clear button to clear filter.
Generators do not require load image.


# Using file input
If using file input, then please edit the input.txt located in res folder.
There is example command lines already inside input.txt and input-2.txt.

## Use of commands:
load [filename] 
	will load the image of [filename] to the program. Please include the path to the file in the filename as well.

dither
	will perform dithering on the loaded image.

edgeDetection
	will perform edgeDetection on the loaded image.

greyscaleContrastEnhancement
	will perform greyscale contrast enhancement on the loaded image.

mosaic
	will perform mosaicing on the loaded image.

redEye
	will perform red eye removal on the loaded image.

greyscale
	will perform greyscaling on the loaded image.

sepia
	will perform sepia toning on the loaded image.

blur
	will perform blurring on the loaded image.

blur [numberOfTimes]
	will perform [numberOfTimes] times of blurring on the loaded image.

sharpen 
	will perform sharpening on the loaded image.

sharpen [numberOfTimes]
	will perform [numberOfTimes] times of sharpening on the loaded image.

checkBoard [heightBlocks] [widthBlocks] [blockSize]
	will generate a checkBoard image with [heightBlocks] blocks in height, [widthBlocks] blocks in width, where each square block has [blockSize] pixels in width.

rainbow [height] [width] [isHorizontal]
	will generate an rainbow image with [height] pixels in height, [width] pixels in width. The stripe is horizontal if [isHorizontal] is true, or vertical otherwise.

flagFrance [height] [width]
	will generate a flag of France with [height] pixels in height, [width] pixels in width.

flagSwiterland [width]
	will generate a square flag of Switerland with [width] pixels in each side.

flagNorway [height] [width]
	will generate a flag of Norway with [height] pixels in height, [width] pixels in width.

flagGreece [height] [width]
	will generate a flag of Greece with [height] pixels in height, [width] pixels in width.

save [filename]
	will save the rendered image to the [filename]. Please include the path to the file in the filename as well.

Multiple operations can perform to one loaded image. 
For example, 
"load res/manhattan-small.png
sharpen
save res/manhattan-small-sharpen.png
sharpen 2
save res/manhattan-small-sharpen-2.png

load res/manhattan-small.png
blur
dither
edgeDetection
save res/manhattan-small-blur-dither-edge-detection.png"

However, if you did not save an image before using generating functions, the image is gone.
For example, the following code will only save an flag of France named flag-france.png in res folder.
"load res/manhattan-small.png
sharpen
flagFrance 200 299
save res/flag-france.png"


## Updates:
I generated 2 interface classes to make the functions looks cleaner. 
Also, I made colors in the color class to be static so do not have to initilize a new color class in ImageGenerating class.

## Image Citation: 
manhattan-small.png from res folder is downloaded from https://northeastern.instructure.com/courses/653/assignments/13010
edge-example.png from res folder is downloaded from https://en.wikipedia.org/wiki/Sobel_operator

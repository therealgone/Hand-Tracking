import com.googlecode.javacpp.Loader;
import com.googlecode.javacv.*;
import com.googlecode.javacv.cpp.*;
import com.googlecode.javacv.cpp.opencv_core.CvPoint;
import com.googlecode.javacv.cpp.opencv_core.CvScalar;
import com.googlecode.javacv.cpp.opencv_core.CvSeq;
import com.googlecode.javacv.cpp.opencv_core.IplImage;
import com.googlecode.javacv.cpp.opencv_highgui.CvCapture;
import com.googlecode.javacv.cpp.opencv_imgproc.CvMoments;

import static com.googlecode.javacv.cpp.opencv_core.*;
import static com.googlecode.javacv.cpp.opencv_imgproc.*;
import static com.googlecode.javacv.cpp.opencv_calib3d.*;
import static com.googlecode.javacv.cpp.opencv_objdetect.*;
import static com.googlecode.javacv.cpp.opencv_highgui.*;
public class JavaCv4 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		IplImage img1, imghsv, imgbin;
		
		imghsv = cvCreateImage(cvSize(640,480),8,3);
		imgbin = cvCreateImage(cvSize(640,480),8,1);
		
		CvCapture capture1 = cvCreateCameraCapture(CV_CAP_ANY);
		
		int i=1;
		
		while(i==1)
		{
				
			img1 = cvQueryFrame(capture1);
			
			if(img1 == null) break;
					
			cvCvtColor(img1,imghsv,CV_BGR2HSV);
			CvScalar minc = cvScalar(95,150,75,0), maxc = cvScalar(145,255,255,0);
			cvInRangeS(imghsv,minc,maxc,imgbin);
		
			cvShowImage("color",img1);
			cvShowImage("Binary",imgbin);
			char c = (char)cvWaitKey(15);
			if(c == 'q') break; 
		
		}
		
		cvReleaseImage(imghsv);
		cvReleaseImage(imgbin);
		cvReleaseCapture(capture1);
	}

}

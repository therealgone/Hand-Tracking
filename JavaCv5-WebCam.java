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

public class JavaCv5 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		IplImage img1,imghsv,imgbin;
		CvScalar minc = cvScalar(95,150,75,0), maxc = cvScalar(145,255,255,0);
		CvSeq contour1 = new CvSeq(), contour2;
		CvMemStorage storage = CvMemStorage.create();
		double areaMax, areaC=0;
		
		CvCapture capture1 = cvCreateCameraCapture(CV_CAP_ANY);
		imghsv = cvCreateImage(cvSize(640,480),8,3);
		imgbin = cvCreateImage(cvSize(640,480),8,1);
		
		int i=1;
		while(i==1)
		{
			
			img1 = cvQueryFrame(capture1);
						
			if(img1 ==null) break;
		
			cvCvtColor(img1,imghsv,CV_BGR2HSV);
			cvInRangeS(imghsv,minc,maxc,imgbin);
		
			contour1 = new CvSeq();
			areaMax= 1000;
		
			cvFindContours(imgbin,storage,contour1,Loader.sizeof(CvContour.class),
							CV_RETR_LIST,CV_LINK_RUNS,cvPoint(0,0));
		
			contour2= contour1;
		
			while(contour1 != null && !contour1.isNull())
			{
				areaC = cvContourArea(contour1,CV_WHOLE_SEQ,1);
			
				if(areaC>areaMax)
					areaMax = areaC;
			
				contour1 = contour1.h_next();
			
			}
		
			while(contour2 !=null && !contour2.isNull())
			{
				areaC= cvContourArea(contour2,CV_WHOLE_SEQ,1);
			
				if(areaC<areaMax)
				{
					cvDrawContours(imgbin,contour2,CV_RGB(0,0,0),CV_RGB(0,0,0),
							0,CV_FILLED,8,cvPoint(0,0));
				}
			
				contour2=contour2.h_next();
			}
		
			cvShowImage("Color",img1);
			cvShowImage("CF",imgbin);
			char c = (char)cvWaitKey(15);
			if(c=='q') break;
			
			
			
			
		}
		
		
		
		cvReleaseImage(imghsv);
		cvReleaseImage(imgbin);
		cvReleaseMemStorage(storage);
		cvReleaseCapture(capture1);
		
	}

}

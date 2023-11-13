import com.googlecode.javacpp.Loader;
import com.googlecode.javacv.*;
import com.googlecode.javacv.cpp.*;
import com.googlecode.javacv.cpp.opencv_core.CvPoint;
import com.googlecode.javacv.cpp.opencv_core.CvScalar;
import com.googlecode.javacv.cpp.opencv_core.CvSeq;
import com.googlecode.javacv.cpp.opencv_core.IplImage;
import com.googlecode.javacv.cpp.opencv_highgui.CvCapture;



import static com.googlecode.javacv.cpp.opencv_core.*;
import static com.googlecode.javacv.cpp.opencv_imgproc.*;
import static com.googlecode.javacv.cpp.opencv_calib3d.*;
import static com.googlecode.javacv.cpp.opencv_objdetect.*;
import static com.googlecode.javacv.cpp.opencv_highgui.*;
public class JavaCv2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		CvCapture capture = cvCreateFileCapture("Vid.mp4"); //cvCreateCameraCapture(CV_CAP_ANY);  //
		IplImage frame;
		IplImage grayimg = cvCreateImage(cvSize(640,480),IPL_DEPTH_8U,1);
		
		cvNamedWindow("Video",CV_WINDOW_AUTOSIZE);
		
		for(;;)
		{
			frame = cvQueryFrame(capture);
			if(frame == null) 
				{
					System.out.println("ERROR: NO Video File");	
					break;
				}
			
			cvCvtColor(frame,grayimg,CV_BGR2GRAY);
			cvShowImage("Video",grayimg);
			char c = (char) cvWaitKey(30);
			
			if(c==27) break;
		}

		cvReleaseCapture(capture);
		cvDestroyWindow("Video");
	}

}
